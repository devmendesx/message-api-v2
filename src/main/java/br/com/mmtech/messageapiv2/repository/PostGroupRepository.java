package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.PostGroup;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostGroupRepository extends JpaRepository<PostGroup, Long> {

  Optional<List<PostGroup>> findAllByPostGroupIn(List<String> postGroup);

  @Query(
      "SELECT shop.id FROM Shop shop "
          + "INNER JOIN PostGroup post ON post.shopId = shop.id "
          + "WHERE post.postGroup IN (:postGroup)")
  List<Long> findShopIdByPostGroups(List<String> postGroup);
}
