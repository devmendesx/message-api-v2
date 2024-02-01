package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
  //  @Modifying
  //  @Query("UPDATE Shop shop " + "SET shop.flgProcessed = 1 WHERE shop.id  = :shopIds")
  //  void updateFlgProcessedByIds(@Param("shopIds") List<Long> shopIds);
  //
  //  @Modifying
  //  @Query("UPDATE Shop shop " + "SET shop.flgProcessed = 0 WHERE 1=1")
  //  void resetFlgProcessed();
}
