package br.com.mmtech.messageapiv2.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "featured_image")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeaturedImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String featuredImage;
  @Column private Long shopId;
  @Column private int status;
  @Column private LocalDateTime createdAt;
  @Column private LocalDateTime updatedAt;

  @PrePersist
  private void createdAt() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
