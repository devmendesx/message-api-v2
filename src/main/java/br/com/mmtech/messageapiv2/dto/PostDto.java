package br.com.mmtech.messageapiv2.dto;

import br.com.mmtech.messageapiv2.enumerated.Department;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDto {
  private String name;
  private String whatsappId;
  private String address;
  private String imageUrl;
  private Department department;
  private String description;
  private List<String> groups;
}
