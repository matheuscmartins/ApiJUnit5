package br.com.dicasdeumdev.ApiJUnit5.services;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
