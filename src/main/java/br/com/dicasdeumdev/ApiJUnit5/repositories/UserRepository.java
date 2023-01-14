package br.com.dicasdeumdev.ApiJUnit5.repositories;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
