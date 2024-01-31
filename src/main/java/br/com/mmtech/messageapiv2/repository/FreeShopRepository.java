package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.FreeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeShopRepository extends JpaRepository<FreeShop, Long> {}
