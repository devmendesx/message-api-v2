package br.com.mmtech.messageapiv2.controller;

import br.com.mmtech.messageapiv2.dto.UpdateFLGDto;
import br.com.mmtech.messageapiv2.enumerated.Department;
import br.com.mmtech.messageapiv2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

  private final PostService postService;

  @GetMapping("/{department}/{pageSize}")
  public ResponseEntity<?> all(
      @PathVariable("department") Department department, @PathVariable("pageSize") int pageSize) {
    return ResponseEntity.ok(
        this.postService.findPostsByDepartmentAndPageSize(department, pageSize));
  }

  @PutMapping
  public ResponseEntity<?> updateFlgProcessed(@RequestBody UpdateFLGDto updateFLGDto) {
    this.postService.updateFlgProcessed(updateFLGDto.getFreeIds(), updateFLGDto.getPaidIds());
    return ResponseEntity.ok().build();
  }

  @PutMapping("/reset")
  public ResponseEntity<?> resetFlgProcessed() {
    this.postService.resetFlgProcessed();
    return ResponseEntity.ok().build();
  }
}
