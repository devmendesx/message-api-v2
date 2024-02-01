package br.com.mmtech.messageapiv2.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "free_shop")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FreeShop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String featured;
  @Column private String name;
  @Column private String description;
  @Column private String address;
  @Column private String whatsapp;
  @Column private String linkWpp;
  @Column private int departmentId;
  @Column private int flgProcessed;
  @Column private LocalDateTime createdAt;
  @Column private LocalDateTime updatedAt;

  @PrePersist
  private void createdAt() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public boolean isPaid() {
    return false;
  }
}
