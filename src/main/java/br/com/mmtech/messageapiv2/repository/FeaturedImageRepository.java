package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.FeaturedImage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturedImageRepository extends JpaRepository<FeaturedImage, Long> {

  Optional<FeaturedImage> findFeaturedImageByShopId(Long shopId);

  @Modifying
  @Query(
      "UPDATE FeaturedImage fi "
          + "SET fi.featuredImage = :featuredImage "
          + "WHERE fi.shopId = :shopId")
  void updateFeaturedImageByShopIdAndFeaturedImage(Long shopId, String featuredImage);
}
