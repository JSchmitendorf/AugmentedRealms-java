package io.augmentedrealms.client.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.util.IOUtils;
import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.exception.ApiExceptionResponse;
import io.augmentedrealms.client.model.Util;
import io.augmentedrealms.client.model.out.Model3D;
import io.augmentedrealms.client.service.ResourceService;
import io.augmentedrealms.client.service.UserService;
import io.augmentedrealms.common.aws.S3Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/resource")
public class ResourceController {

    private S3Service s3Service;

    private ResourceService resourceService;

    private UserService userService;

    public ResourceController(S3Service s3Service, ResourceService resourceService, UserService userService) {
        this.s3Service = s3Service;
        this.resourceService = resourceService;
        this.userService = userService;
    }

    @GetMapping(path = "/model/{fileName}")
    public @ResponseBody byte[] getModelFile(@PathVariable("fileName") String name)
            throws ApiException {
        return downloadFile(name, S3Service.ResourceType.MODEL);
    }

    @GetMapping(path = "/preview/{fileName}")
    public @ResponseBody byte[] getModelPreview(@PathVariable("fileName") String name) throws ApiException {
        return downloadFile(name, S3Service.ResourceType.PREVIEW);
    }

    @GetMapping(path = "/model")
    public List<Model3D> getAvailableMode3D() {
        return new ArrayList<>(Util.getInstanceMap(resourceService.getAvailableModel3D(),
                model -> getModel3DInstance(model)).values());
    }

    @PostMapping(path = "/model")
    public void uploadModel3D(@RequestParam("model")MultipartFile modelFile,
                              @RequestParam("preview") MultipartFile previewFile,
                              @RequestParam("name")String name) throws ApiException {

        try{
            // update the database
            io.augmentedrealms.common.database.model.Model3D instance = resourceService.uploadModel3D
                    (modelFile.getOriginalFilename(),previewFile.getOriginalFilename(),name,
                    userService.getCurrentUserDBModel());
            // upload files to s3
            File localFile = convert(modelFile,instance.getId()+"."+instance.getExtension());

            s3Service.uploadResource(S3Service.ResourceType.MODEL, localFile);
            // TO-DO error handle
            localFile.delete();
            localFile = convert(previewFile,instance.getId()+"."+instance.getPreviewExtension());
            s3Service.uploadResource(S3Service.ResourceType.PREVIEW,localFile);
            // TO-DO error handle
            localFile.delete();

        } catch (AmazonS3Exception | IOException e) {
            throw new ApiException(ApiExceptionResponse.CLOUD_STORAGE_ERROR.withOptionalMessage(e.toString()));
        }
    }

    private byte[] downloadFile(String name,S3Service.ResourceType type) throws ApiException {
        try{
            return IOUtils.toByteArray(s3Service.downloadResource(name,type));
        }  catch (AmazonS3Exception | IOException e ){
            throw new ApiException(ApiExceptionResponse.CLOUD_STORAGE_ERROR.withOptionalMessage(e.toString()));
        }
    }

    private Model3D getModel3DInstance(io.augmentedrealms.common.database.model.Model3D model) {
        Model3D model3D = Model3D.getInstance(model);
        String fileName = model.getId()+"."+model.getExtension();
        model3D.add(linkTo(ResourceController.class).slash("model")
                .slash(fileName).withRel("model"));
        String previewName = model.getId()+"."+model.getPreviewExtension();
        model3D.add(linkTo(ResourceController.class).slash("preview")
                .slash(previewName).withRel("preview"));
        return model3D;
    }

    private File convert(MultipartFile file,String fileName) throws IOException
    {
        File convFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }




}
