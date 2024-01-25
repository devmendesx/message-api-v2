package br.com.mmtech.messageapiv2.repository;

import br.com.mmtech.messageapiv2.domain.PostGroup;
import br.com.mmtech.messageapiv2.enumerated.WeekGroup;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostGroupRepository extends JpaRepository<PostGroup, Long> {

  Optional<List<PostGroup>> findAllByPostGroupIn(List<WeekGroup> postGroup);
}
