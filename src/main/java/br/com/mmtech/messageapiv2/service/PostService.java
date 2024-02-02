package br.com.mmtech.messageapiv2.service;

import br.com.mmtech.messageapiv2.domain.FreeShop;
import br.com.mmtech.messageapiv2.domain.Shop;
import br.com.mmtech.messageapiv2.dto.PostDto;
import br.com.mmtech.messageapiv2.enumerated.Department;
import br.com.mmtech.messageapiv2.enumerated.WeekGroup;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostService {

  private final ShopService shopService;
  private final PostGroupService postGroupService;
  private final FeaturedImageService featuredImageService;
  private final LinkService linkService;
  private final LinkGroupService linkGroupService;
  private final FreeShopService freeShopService;

  public List<PostDto> findPostsByDepartmentAndPageSize(Department department, int pageSize) {
    var postGroups = this.getWeekGroups();
    var paidShopIds =
        this.postGroupService.findShopIdByPostGroupsAndDepartment(
            postGroups, department, Pageable.ofSize(pageSize));
    var shops = this.shopService.findAllByIds(paidShopIds);
    var groups = this.linkGroupService.findAllGroups();
    if (shops.size() == pageSize) {
      return this.buildPostsDto(Collections.emptyList(), shops, groups);
    }
    var freeShops = this.freeShopService.findAllFreeShop(pageSize - shops.size());
    return this.buildPostsDto(freeShops, shops, groups);
  }

  private List<PostDto> buildPostsDto(
      List<FreeShop> freeShops, List<Shop> shops, Map<Long, List<String>> groups) {

    var allPosts = new ArrayList<PostDto>();

    var postsFree =
        freeShops.stream()
            .map(
                freeShop -> {
                  var department = Department.getDepartmentById(freeShop.getDepartmentId());
                  return PostDto.builder()
                      .shopId(freeShop.getId())
                      .name(freeShop.getName())
                      .whatsappId(freeShop.getLinkWpp())
                      .groups(groups.get((long) freeShop.getDepartmentId()))
                      .description(freeShop.getDescription())
                      .imageUrl(freeShop.getFeatured())
                      .address(freeShop.getAddress())
                      .department(department)
                      .isPaid(freeShop.isPaid())
                      .build();
                })
            .toList();

    var postsPaid =
        shops.stream()
            .map(
                shop -> {
                  var featuredImage =
                      this.featuredImageService.findByShopId(shop.getId()).orElse(null);
                  var link = this.linkService.findByShopId(shop.getId());
                  var department = Department.getDepartmentById(shop.getDepartmentId());
                  return PostDto.builder()
                      .shopId(shop.getId())
                      .name(shop.getName())
                      .whatsappId(link.getUniqueName())
                      .groups(groups.get((long) shop.getDepartmentId()))
                      .description(shop.getDescription())
                      .imageUrl(featuredImage != null ? featuredImage.getFeaturedImage() : null)
                      .address(shop.getAddress())
                      .department(department)
                      .isPaid(shop.isPaid())
                      .build();
                })
            .toList();

    allPosts.addAll(postsPaid);
    allPosts.addAll(postsFree);

    return allPosts.stream()
        .sorted(Comparator.comparing(PostDto::isPaid))
        .collect(Collectors.toList());
  }

  private List<String> getWeekGroups() {
    return WeekGroup.getPlansByDay(LocalDate.now().getDayOfWeek()).stream()
        .map(Objects::toString)
        .collect(Collectors.toList());
  }

  public void updateFlgProcessed(List<Long> freeIds, List<Long> paidIds) {
    try {
      this.shopService.updateFlgProcessed(paidIds);
      this.freeShopService.updateFlgProcessed(freeIds);
    } catch (Exception ex) {
      log.error("msg=Error on updating flgProcessed");
    }
  }

  public void resetFlgProcessed() {
    try {
      this.shopService.resetFlgProcessed();
      this.freeShopService.resetFlgProcessed();
    } catch (Exception ex) {
      log.error("msg=Error on updating flgProcessed.");
    }
  }
}
