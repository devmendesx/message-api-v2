package br.com.mmtech.messageapiv2.domain;

import br.com.mmtech.messageapiv2.enumerated.WeekGroup;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Table(name = "post_group")
@Getter
public class PostGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private Long shopId;
  @Column private WeekGroup postGroup;
  @Column private LocalDateTime createdAt;
  @Column private LocalDateTime updatedAt;

  @PrePersist
  private void createdAt() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
