package br.com.mmtech.messageapiv2.controller;

import br.com.mmtech.messageapiv2.service.PostGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/v1/postgroup")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostGroupController {
  private final PostGroupService postGroupService;

  @GetMapping
  @Cacheable(value = "#findAllPostGroup")
  public ResponseEntity<?> findAllPostGroup() {
    try {
      log.info("m=findAllPostGroup");
      return ResponseEntity.ok(this.postGroupService.findAll());
    } catch (Exception ex) {
      log.error("m=findAllPostGroup, ex={}", ex.getMessage());
      throw ex;
    }
  }
}
