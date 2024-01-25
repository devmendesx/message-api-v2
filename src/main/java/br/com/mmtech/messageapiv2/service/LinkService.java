package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.domain.Link;
import br.com.mmtech.messageapiv2.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LinkService {
  private final LinkRepository linkRepository;

  public Link findByShopId(Long id) {
    try {
      return this.linkRepository.findByShopIdAndLinkTypeId(id, 1L);
    } catch (Exception exception) {
      throw exception;
    }
  }
}
