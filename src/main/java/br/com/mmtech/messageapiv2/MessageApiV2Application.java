package br.com.mmtech.messageapiv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class MessageApiV2Application {
  public static void main(String[] args) {
    SpringApplication.run(MessageApiV2Application.class, args);
  }
}
