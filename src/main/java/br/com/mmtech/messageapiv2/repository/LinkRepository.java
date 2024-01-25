package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
  Link findByShopId(Long id);
}
