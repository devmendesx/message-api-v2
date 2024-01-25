package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.domain.Shop;
import br.com.mmtech.messageapiv2.dto.ShopDto;
import br.com.mmtech.messageapiv2.repository.ShopRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShopService {

  private final ShopRepository repository;
  private final ExecutorService executorService = Executors.newFixedThreadPool(400);

  public List<ShopDto> findAll() throws ExecutionException, InterruptedException {
    var shops = CompletableFuture.supplyAsync(this.repository::findAll, executorService);
    return shops.get().stream()
        .map(
            shop ->
                ShopDto.builder()
                    .id(shop.getId())
                    .name(shop.getName())
                    .address(shop.getAddress())
                    .whatsapp(shop.getWhatsapp())
                    .description(shop.getDescription())
                    .status(shop.getStatus())
                    .updatedAt(shop.getUpdatedAt())
                    .createdAt(shop.getCreatedAt())
                    .build())
        .collect(Collectors.toList());
  }

  public List<Shop> findAllByIds(List<Long> ids) {
    return this.repository.findAllById(ids);
  }
}
