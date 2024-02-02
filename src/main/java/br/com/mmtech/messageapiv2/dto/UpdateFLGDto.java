package br.com.mmtech.messageapiv2.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class UpdateFLGDto {
  List<Long> paidIds;
  List<Long> freeIds;
}
