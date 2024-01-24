package br.com.mmtech.messageapiv2.controller;

import br.com.mmtech.messageapiv2.dto.ShopDto;
import br.com.mmtech.messageapiv2.service.ShopService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/v1/shop")
@Slf4j
public class ShopController {

  private final ShopService shopService;

  @GetMapping
  @Cacheable(value = "#allShops")
  public ResponseEntity<List<ShopDto>> findAll() throws ExecutionException, InterruptedException {
    try {
      return ResponseEntity.ok(this.shopService.findAll());
    } catch (Exception exception) {
      log.error("message={}", exception.getMessage());
      throw exception;
    }
  }
}
