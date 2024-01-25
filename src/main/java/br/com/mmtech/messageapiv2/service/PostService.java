package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.dto.PostDto;
import br.com.mmtech.messageapiv2.enumerated.Department;
import br.com.mmtech.messageapiv2.enumerated.WeekGroup;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostService {

  private final ShopService shopService;
  private final PostGroupService postGroupService;
  private final FeaturedImageService featuredImageService;
  private final LinkService linkService;

  public List<PostDto> allPosts() {
    try {
      var postGroups = WeekGroup.getPlansByDay(LocalDate.now().getDayOfWeek());
      var shopIds = this.postGroupService.findShopIdByPostGroups(postGroups);
      log.info("msg=Buscando novos fornecedores para enviar mensagem., workGroup={}", postGroups);
      var shops = this.shopService.findAllByIds(shopIds);
      var shopsAll = this.shopService.findAll();
      var postGroupsAll = this.postGroupService.findAll();
      log.info("shops={}, posts={}", shopsAll, postGroupsAll);

      return shops.stream()
          .map(
              shop -> {
                var featuredImage =
                    this.featuredImageService.findByShopId(shop.getId()).orElse(null);
                var link = this.linkService.findByShopId(shop.getId());
                var department = Department.getDepartmentById(shop.getDepartmentId());
                return PostDto.builder()
                    .name(shop.getName())
                    .whatsappUrl(link.getUniqueName())
                    .imageUrl(featuredImage != null ? featuredImage.getFeaturedImage() : null)
                    .address(shop.getAddress())
                    .department(department)
                    .build();
              })
          .collect(Collectors.toList());
    } catch (Exception e) {
      log.error("msg=Erro ao buscar fornecedores.");
      throw new RuntimeException(e);
    }
  }
}
