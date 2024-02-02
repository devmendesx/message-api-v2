package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.FreeShop;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeShopRepository extends JpaRepository<FreeShop, Long> {

  @Query(
      "SELECT fs.id FROM FreeShop fs "
          + "WHERE fs.flgProcessed = 0 AND fs.departmentId = :department ORDER BY fs.id")
  List<FreeShop> findAllNoProcessed(int department, Pageable pageable);

  @Modifying
  @Query("UPDATE FreeShop shop " + "SET shop.flgProcessed = 1 WHERE shop.id  = :shopIds")
  void updateFlgProcessedByIds(@Param("shopIds") List<Long> shopIds);

  @Modifying
  @Query("UPDATE FreeShop shop " + "SET shop.flgProcessed = 0 WHERE 1=1")
  void resetFlgProcessed();
}
