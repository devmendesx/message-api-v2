package br.com.mmtech.messageapiv2.controller;

import br.com.mmtech.messageapiv2.service.StorageService;
import lombok.RequiredArgsConstructor;
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

  @PostMapping("/upload/{fileName}")
  @CacheEvict("#posts")
  public ResponseEntity<String> upload(
      @PathVariable(value = "fileName") String fileName,
      @RequestParam(value = "file") MultipartFile file) {
    try {
      return ResponseEntity.ok(this.storageService.upload(file, fileName));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/download/{fileName}")
  public ResponseEntity<ByteArrayResource> download(
      @PathVariable(value = "fileName") String fileName) {
    var file = this.storageService.download(fileName);
    var resource = new ByteArrayResource(file);
    return ResponseEntity.ok()
        .contentLength(file.length)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header("Content-Disposition", "attachment; filename=" + fileName)
        .body(resource);
  }

  @GetMapping("/visualize/{fileName}")
  public ResponseEntity<ByteArrayResource> preview(
      @PathVariable(value = "fileName") String fileName) {
    var file = this.storageService.visualize(fileName);
    String fileExtension = this.storageService.getFileExtension(fileName);
    MediaType mediaType = this.storageService.getMediaTypeForFileExtension(fileExtension);
    var resource = new ByteArrayResource(file);
    return ResponseEntity.ok().contentLength(file.length).contentType(mediaType).body(resource);
  }
}
