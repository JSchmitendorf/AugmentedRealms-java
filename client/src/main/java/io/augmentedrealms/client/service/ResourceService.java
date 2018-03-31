package io.augmentedrealms.client.service;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.exception.ApiExceptionResponse;
import io.augmentedrealms.common.database.model.Model3D;
import io.augmentedrealms.common.database.model.User;
import io.augmentedrealms.common.database.repository.Model3DRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private Model3DRepository model3DRepository;

    public ResourceService(Model3DRepository model3DRepository) {
        this.model3DRepository = model3DRepository;
    }

    public List<Model3D> getAvailableModel3D() {
        return model3DRepository.findAll();
    }

    public Model3D uploadModel3D(String fileName,String previewName,String name,User owner) throws ApiException {
        Model3D instance = new Model3D();
        instance.setModelName(name);
        instance.setPublisher(owner);
        instance.setExtension(getExtension(fileName));
        instance.setPreviewExtension(getExtension(previewName));
        return model3DRepository.save(instance);

    }

    private String getExtension(String fileName) throws ApiException {
        int startIndex = fileName.lastIndexOf('.');
        if (startIndex >= fileName.length()) {
            throw new ApiException(ApiExceptionResponse.UNSUPPORTED_FILE_FORMAT);
        }
        return fileName.substring(startIndex+1);
    }

}
