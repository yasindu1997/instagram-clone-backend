package lk.ijse.instagramclone.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping(path = "/api/instagram/upload")
public class FileUploadController {

    @PostMapping
    @ResponseBody
    public String uploadData(@RequestParam("file") MultipartFile file) {


        File uploadedFile = new File(file.getOriginalFilename());

        try (OutputStream os = new FileOutputStream(uploadedFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String bucketName = "demo-ijse";
        String objectKey = uploadedFile.getName();


        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        PutObjectResponse response = s3.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(objectKey)
                        .build(),
                RequestBody.fromFile(uploadedFile)
        );
        
        return "DONE";
    }
}
