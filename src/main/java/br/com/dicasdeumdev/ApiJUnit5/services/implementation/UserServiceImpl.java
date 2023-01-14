package br.com.dicasdeumdev.ApiJUnit5.services.implementation;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;
import br.com.dicasdeumdev.ApiJUnit5.repositories.UserRepository;
import br.com.dicasdeumdev.ApiJUnit5.services.UserService;
import br.com.dicasdeumdev.ApiJUnit5.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no id: " + id));
        //retorna exception quando null
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
