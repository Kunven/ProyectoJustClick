package com.example.demo.configuration;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.*;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.client.RestClients;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.demo.repository" )
@ComponentScan(basePackages = {"com.example.demo"})
public class Config extends 
         AbstractElasticsearchConfiguration {
  @Override
  @Bean
  public RestHighLevelClient elasticsearchClient() {

  final ClientConfiguration clientConfiguration = 
    ClientConfiguration
      .builder()
      .connectedTo("localhost:9200")
      .build();

  return RestClients.create(clientConfiguration).rest();
  }
}