package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.domain.LinkGroup;
import br.com.mmtech.messageapiv2.repository.LinkGroupRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LinkGroupService {

  private final LinkGroupRepository repository;

  public Map<Long, List<String>> findAllGroups() {
    var groups = this.repository.findAll();
    Map<Long, List<String>> departmentGroupsMap = new HashMap<>();
    for (LinkGroup group : groups) {
      Long departmentId = (long) group.getDepartmentId();
      departmentGroupsMap
          .computeIfAbsent(departmentId, k -> new ArrayList<>())
          .add(group.getName());
    }
    return departmentGroupsMap;
  }
}
