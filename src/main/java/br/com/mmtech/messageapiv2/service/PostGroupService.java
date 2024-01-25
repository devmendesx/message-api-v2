package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.dto.PostGroupDto;
import br.com.mmtech.messageapiv2.enumerated.WeekGroup;
import br.com.mmtech.messageapiv2.repository.PostGroupRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostGroupService {

  private final PostGroupRepository postGroupRepository;

  public List<PostGroupDto> findAllPostGroups() {
    List<WeekGroup> weekGroups = WeekGroup.getPlansByDay(LocalDate.now().getDayOfWeek());

    var postGroups =
        this.postGroupRepository.findAllByPostGroupIn(weekGroups).orElse(Collections.emptyList());
    return postGroups.stream()
        .map(post -> PostGroupDto.builder().id(post.getId()).weekGroup(post.getPostGroup()).build())
        .collect(Collectors.toList());
  }
}
