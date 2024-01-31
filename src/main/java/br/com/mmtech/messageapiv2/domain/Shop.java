package br.com.mmtech.messageapiv2.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "shop")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@SQLRestriction("status = 1")
public class Shop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String logo;
  @Column private int departmentId;
  @Column private Long userId;
  @Column private String name;
  @Column private String address;
  @Column private String whatsapp;
  @Column private String description;
  @Column private int status;
  @Column private int flgProcessed;
  @Column private LocalDateTime createdAt;
  @Column private LocalDateTime updatedAt;

  @PrePersist
  private void createdAt() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public boolean isPaid() {
    return true;
  }
}
