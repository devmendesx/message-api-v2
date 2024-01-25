package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.FeaturedImage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturedImageRepository extends JpaRepository<FeaturedImage, Long> {

  Optional<FeaturedImage> findByShopId(Long shopId);
}
