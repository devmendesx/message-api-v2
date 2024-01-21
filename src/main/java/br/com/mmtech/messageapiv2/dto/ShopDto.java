package br.com.mmtech.messageapiv2.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ShopDto {
  private Long id;
  private String name;
  private String address;
  private String whatsapp;
  private String description;
  private int status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
