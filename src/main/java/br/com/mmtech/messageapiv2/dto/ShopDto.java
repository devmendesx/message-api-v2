package br.com.mmtech.messageapiv2.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
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
