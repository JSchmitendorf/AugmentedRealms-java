package io.augmentedrealms.client.controller;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.exception.ApiExceptionResponse;
import io.augmentedrealms.client.service.ResourceService;
import io.augmentedrealms.common.aws.S3Service;
import io.augmentedrealms.common.database.model.Token;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/resource")
public class ResourceController {

    private S3Service s3Service;

    private ResourceService resourceService;

    public ResourceController(S3Service s3Service, ResourceService resourceService) {
        this.s3Service = s3Service;
        this.resourceService = resourceService;
    }

    @GetMapping(path = "/token/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public void getTokenFile(@PathVariable("name") String name, HttpServletResponse response) throws ApiException, IOException {
        try {
            IOUtils.copy(s3Service.downloadToken(name), response.getOutputStream());
        } catch (AmazonS3Exception e){
            throw new ApiException(ApiExceptionResponse.CLOUD_STORAGE_ERROR.withOptionalMessage(e.toString()));
        }
    }

    @GetMapping(path = "/token")
    public @ResponseBody
    List<Token> getTokens() {
        return resourceService.getAvailableTokens();
    }
}
