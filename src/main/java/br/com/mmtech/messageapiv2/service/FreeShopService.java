package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.domain.FreeShop;
import br.com.mmtech.messageapiv2.repository.FreeShopRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FreeShopService {
  private final FreeShopRepository repository;

  public List<FreeShop> findAllFreeShop() {
    return this.repository.findAll();
  }
}
