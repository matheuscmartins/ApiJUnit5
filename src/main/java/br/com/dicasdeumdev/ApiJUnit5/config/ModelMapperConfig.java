package br.com.dicasdeumdev.ApiJUnit5.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper mapper(){ //usado para injeção ao invés de ficar instanciando todas as vezez que necessario
        return new ModelMapper();
    }
}
