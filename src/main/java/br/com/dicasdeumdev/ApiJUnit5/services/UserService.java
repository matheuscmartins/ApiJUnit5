package br.com.dicasdeumdev.ApiJUnit5.services;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;

public interface UserService {

    User findById(Integer id);
}
