package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.domain.FeaturedImage;
import br.com.mmtech.messageapiv2.repository.FeaturedImageRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeaturedImageService {
  private final FeaturedImageRepository featuredImageRepository;

  public Optional<FeaturedImage> findByShopId(Long id) {
    try {
      return this.featuredImageRepository.findByShopId(id);
    } catch (Exception exception) {
      throw exception;
    }
  }
}
