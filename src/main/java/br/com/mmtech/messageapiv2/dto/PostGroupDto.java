package br.com.mmtech.messageapiv2.dto;

import br.com.mmtech.messageapiv2.enumerated.WeekGroup;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PostGroupDto {
  Long id;
  WeekGroup weekGroup;
}
