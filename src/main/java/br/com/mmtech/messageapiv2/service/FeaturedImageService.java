package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.domain.FeaturedImage;
import br.com.mmtech.messageapiv2.repository.FeaturedImageRepository;
import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeaturedImageService {
  private final FeaturedImageRepository featuredImageRepository;

  public Optional<FeaturedImage> findByShopId(Long id) {
    try {
      return this.featuredImageRepository.findFeaturedImageByShopId(id);
    } catch (Exception exception) {
      throw exception;
    }
  }

  @Transactional(value = Transactional.TxType.REQUIRES_NEW)
  public void updateFeaturedImage(String filename, Long shopId) {
    try {
      this.featuredImageRepository.updateFeaturedImage(shopId, filename);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void save(String filename, Long shopId) {
    var featuredImage =
        FeaturedImage.builder().featuredImage(filename).shopId(shopId).status(1).build();
    this.featuredImageRepository.save(featuredImage);
  }
}
