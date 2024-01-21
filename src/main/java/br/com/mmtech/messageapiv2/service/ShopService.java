package br.com.mmtech.messageapiv2.service;


import br.com.mmtech.messageapiv2.dto.ShopDto;
import br.com.mmtech.messageapiv2.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShopService {

    private final ShopRepository repository;

    public List<ShopDto> findAll() {
        var shops = this.repository.findAll();

        return shops.stream().map(shop -> ShopDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .address(shop.getAddress())
                .whatsapp(shop.getWhatsapp())
                .description(shop.getDescription())
                .status(shop.getStatus())
                .updatedAt(shop.getUpdatedAt())
                .createdAt(shop.getCreatedAt())
                .build()).collect(Collectors.toList());
    }
}
