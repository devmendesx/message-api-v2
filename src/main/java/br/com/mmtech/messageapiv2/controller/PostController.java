package br.com.mmtech.messageapiv2.controller;

import br.com.mmtech.messageapiv2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {
  private final PostService postService;

  @GetMapping
  @Cacheable("posts")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.postService.allPosts());
  }
}
