package io.augmentedrealms.common.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    @Value("${s3.path.model}")
    private String modelPath;

    @Value("${s3.path.preview}")
    private String previewPath;


    public InputStream downloadResource(String name,ResourceType type) {

        return amazonS3Client.getObject(bucketName,getPath(type).concat(name)).getObjectContent();
    }

    public void uploadResource(ResourceType type,File file) {
        amazonS3Client.putObject(new PutObjectRequest(bucketName,getPath(type).concat(file.getName()),file));
    }

    private String getPath(ResourceType type) {
        switch (type) {
            case MODEL:
                return modelPath;
            case PREVIEW:
                return previewPath;
             default:
                 return null;
        }
    }


    public enum ResourceType {
        MODEL, PREVIEW;

    }
}
