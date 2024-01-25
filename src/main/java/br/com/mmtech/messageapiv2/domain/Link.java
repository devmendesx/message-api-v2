package br.com.mmtech.messageapiv2.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Table(name = "link")
@Getter
public class Link {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private Long shopId;
  @Column private String link;
  @Column private String uniqueName;
  @Column private Long linkTypeId;
  @Column private int status;
  @Column private LocalDateTime createdAt;
  @Column private LocalDateTime updatedAt;

  @PrePersist
  private void createdAt() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
