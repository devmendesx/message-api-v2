package br.com.mmtech.messageapiv2.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "user_app")
@SQLRestriction(value = "status = 1")
public class UserApp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private int levelAccessId;
  @Column private String fullName;
  @Column private String telephone;
  @Column private String email;
  @Column private String password;
  @Column private int terms;
  @Column private String document;
  @Column private String birthDate;
  @Column private int status;
  @Column private String token;
  @Column private LocalDateTime createdAt;
  @Column private LocalDateTime updatedAt;

  @PrePersist
  private void createdAt() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
