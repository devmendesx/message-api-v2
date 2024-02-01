package br.com.mmtech.messageapiv2.controller;

import br.com.mmtech.messageapiv2.service.StorageService;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/storage")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StorageController {
  private final StorageService storageService;

  @PostMapping("/upload/{fileName}/{shopId}")
  public ResponseEntity<String> upload(
      @PathVariable(value = "fileName") String fileName,
      @PathVariable(value = "shopId") Long shopId,
      @RequestParam(value = "file") MultipartFile file) {
    try {
      return ResponseEntity.ok(this.storageService.upload(file, fileName, shopId));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/download/{fileName}")
  public ResponseEntity<ByteArrayResource> download(
      @PathVariable(value = "fileName") String fileName) {
    var file = this.storageService.getObject(fileName);
    var resource = new ByteArrayResource(file);
    return ResponseEntity.ok()
        .contentLength(file.length)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header("Content-Disposition", "attachment; filename=" + fileName)
        .body(resource);
  }

  @GetMapping("/download/base64/{fileName}")
  public ResponseEntity<String> downloadBase64(@PathVariable(value = "fileName") String fileName)
      throws UnsupportedEncodingException {
    var file = this.storageService.getObjectBase64(fileName);
    var encodedfile = new String(Base64.encodeBase64(file), "UTF-8");
    return ResponseEntity.ok()
        .contentLength(file.length)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header("Content-Disposition", "attachment; filename=" + fileName)
        .body(encodedfile);
  }

  @GetMapping("/visualize/{fileName}")
  public ResponseEntity<ByteArrayResource> preview(
      @PathVariable(value = "fileName") String fileName) {
    var file = this.storageService.getObject(fileName);
    String fileExtension = this.storageService.getFileExtension(fileName);
    MediaType mediaType = this.storageService.getMediaTypeForFileExtension(fileExtension);
    var resource = new ByteArrayResource(file);
    return ResponseEntity.ok().contentLength(file.length).contentType(mediaType).body(resource);
  }
}
