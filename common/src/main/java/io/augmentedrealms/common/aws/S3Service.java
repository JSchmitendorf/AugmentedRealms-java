package io.augmentedrealms.common.aws;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    @Value("${s3.path.token}")
    private String tokenPath;

    public InputStream downloadToken(String tokenName) {
        return amazonS3Client.getObject(bucketName, tokenPath.concat(tokenName)).getObjectContent();

    }
}
