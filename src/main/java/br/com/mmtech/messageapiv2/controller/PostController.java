package br.com.mmtech.messageapiv2.controller;

import br.com.mmtech.messageapiv2.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {
  private final PostService postService;

  @GetMapping
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.postService.allPosts().subList(0, 9));
  }

  @PutMapping
  public ResponseEntity<?> updateFlgProcessed(@RequestBody List<Long> shopIds) {
    this.postService.updateFlgProcessed(shopIds);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/reset")
  public ResponseEntity<?> resetFlgProcessed() {
    this.postService.resetFlgProcessed();
    return ResponseEntity.ok().build();
  }
}
