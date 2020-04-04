package com.cricket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@SuppressWarnings({"PMD.UseUtilityClass"})
public class Starter  {

  @Bean
  public RestTemplate getRestTemplate(){
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(Starter.class, args);
  }
}
