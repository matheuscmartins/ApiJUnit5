package br.com.dicasdeumdev.ApiJUnit5.repositories;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
