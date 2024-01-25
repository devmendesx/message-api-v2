package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.dto.PostDto;
import br.com.mmtech.messageapiv2.enumerated.Department;
import br.com.mmtech.messageapiv2.enumerated.WeekGroup;
import br.com.mmtech.messageapiv2.repository.ShopRepository;
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

  private final ShopRepository shopRepository;
  private final PostGroupService postGroupService;
  private final FeaturedImageService featuredImageService;
  private final LinkService linkService;

  public List<PostDto> allPosts() {
    try {
      log.info("[msg=Buscando novos fornecedores para enviar mensagem.]");
      var postGroups = WeekGroup.getPlansByDay(LocalDate.now().getDayOfWeek());
      var shopIds = this.postGroupService.findShopIdByPostGroups(postGroups);
      var shops = this.shopRepository.findAllById(shopIds);

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
