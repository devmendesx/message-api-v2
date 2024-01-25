package br.com.mmtech.messageapiv2.dto;

import br.com.mmtech.messageapiv2.enumerated.Department;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDto {
  private String name;
  private String whatsappUrl;
  private String address;
  private String imageUrl;
  private Department department;
}
