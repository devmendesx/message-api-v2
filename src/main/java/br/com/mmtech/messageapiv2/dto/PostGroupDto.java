package br.com.mmtech.messageapiv2.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PostGroupDto {
  Long id;
  String weekGroup;
}
