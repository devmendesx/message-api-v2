package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.Shop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

  @Query(
      value =
          "(SELECT sp FROM shop sp WHERE department = 'FITNESS' LIMIT :limit) "
              + "UNION ALL "
              + "(SELECT sp FROM shop sp WHERE department = 'KIDS' LIMIT :limit)",
      nativeQuery = true)
  List<Shop> findMixedDepartments(@Param("limit") int limit);

  @Modifying
  @Query("UPDATE Shop shop " + "SET shop.flgProcessed = 1 WHERE shop.id  = IN(:shopIds)")
  void updateFlgProcessedByIds(@Param("shopIds") List<Long> shopIds);

  @Modifying
  @Query("UPDATE Shop shop " + "SET shop.flgProcessed = 0 WHERE 1=1")
  void resetFlgProcessed();
}
