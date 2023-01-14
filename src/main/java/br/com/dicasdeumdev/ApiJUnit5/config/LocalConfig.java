package br.com.dicasdeumdev.ApiJUnit5.config;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;
import br.com.dicasdeumdev.ApiJUnit5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB(){
        User user1 = new User(null, "Jose Cruz", "josecruz@gmail.com", "123");
        User user2 = new User(null, "Maria Cruz", "mariacruz@gmail.com", "123");
        userRepository.saveAll(List.of(user1, user2));
    }
}
