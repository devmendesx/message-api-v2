package br.com.mmtech.messageapiv2.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageDto {
  private final String message;
}
