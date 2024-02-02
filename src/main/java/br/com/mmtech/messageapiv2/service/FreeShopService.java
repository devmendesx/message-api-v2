package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.domain.FreeShop;
import br.com.mmtech.messageapiv2.enumerated.Department;
import br.com.mmtech.messageapiv2.repository.FreeShopRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FreeShopService {
  private final FreeShopRepository repository;

  public List<FreeShop> findAllFreeShop(Department department, int pageSize) {
    return this.repository.findAllNoProcessed(department.getId(), Pageable.ofSize(pageSize));
  }

  public void updateFlgProcessed(List<Long> shopIds) {
    try {
      this.repository.updateFlgProcessedByIds(shopIds);
    } catch (Exception ex) {
      log.error("msg=Error on updating flgProcessed., ids={}, ex={}", shopIds, ex.getMessage());
    }
  }

  public void resetFlgProcessed() {
    try {
      this.repository.resetFlgProcessed();
    } catch (Exception ex) {
      log.error("msg=Error on updating flgProcessed.");
    }
  }
}
