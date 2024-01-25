package br.com.mmtech.messageapiv2.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StorageService {

  @Value("${cloud.aws.bucket}")
  private String bucket;

  private final AmazonS3 storageClient;

  public String upload(MultipartFile content, String fileName) {
    try {
      var file = this.convertMultipartToFile(content);
      this.storageClient.putObject(new PutObjectRequest(bucket, fileName, file));
      file.delete();
      return "File uploaded: " + fileName;
    } catch (SdkClientException e) {
      throw new RuntimeException(e);
    }
  }

  public byte[] download(String fileName) {
    try {
      S3Object file = this.storageClient.getObject(this.bucket, fileName);
      S3ObjectInputStream inputStream = file.getObjectContent();
      return IOUtils.toByteArray(inputStream);
    } catch (IOException e) {
      log.error("m=download, message={}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public byte[] visualize(String fileName) {
    try {
      S3Object s3Object = storageClient.getObject(this.bucket, fileName);
      S3ObjectInputStream inputStream = s3Object.getObjectContent();
      return IOUtils.toByteArray(inputStream);
    } catch (IOException e) {
      log.error("m=download, message={}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  private File convertMultipartToFile(MultipartFile file) {
    File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
    try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
      fos.write(file.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return convertedFile;
  }

  public MediaType getMediaTypeForFileExtension(String extension) {
    Map<String, MediaType> extensionMap = new HashMap<>();
    extensionMap.put("jpeg", MediaType.IMAGE_JPEG);
    extensionMap.put("jpg", MediaType.IMAGE_JPEG);
    extensionMap.put("png", MediaType.IMAGE_PNG);
    extensionMap.put("gif", MediaType.IMAGE_GIF);
    extensionMap.put("webp", new MediaType("image", "webp"));
    return extensionMap.getOrDefault(extension.toLowerCase(), MediaType.APPLICATION_OCTET_STREAM);
  }

  public String getFileExtension(String fileName) {
    if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
      return fileName.substring(fileName.lastIndexOf(".") + 1);
    else return "";
  }
}
