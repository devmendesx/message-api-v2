package br.com.mmtech.messageapiv2.dto;

import br.com.mmtech.messageapiv2.enumerated.WeekGroup;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PostGroupDto {
  Long id;
  WeekGroup weekGroup;
}
