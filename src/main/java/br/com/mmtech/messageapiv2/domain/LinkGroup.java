package br.com.mmtech.messageapiv2.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Table(name = "link_group")
@Getter
public class LinkGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;
  @Column private String link;
  @Column private Long participants;
  @Column private int departmentId;
  @Column private LocalDateTime createdAt;
  @Column private LocalDateTime updatedAt;

  @PrePersist
  private void createdAt() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
