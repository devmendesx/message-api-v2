package br.com.mmtech.messageapiv2.domain;

import br.com.mmtech.messageapiv2.enumerated.WeekGroup;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "post_group")
@Getter
public class PostGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private Long shopId;

  @Column private WeekGroup weekGroup;
}
