package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {}
