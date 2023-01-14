package br.com.dicasdeumdev.ApiJUnit5.services.implementation;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;
import br.com.dicasdeumdev.ApiJUnit5.domain.dto.UserDTO;
import br.com.dicasdeumdev.ApiJUnit5.repositories.UserRepository;
import br.com.dicasdeumdev.ApiJUnit5.services.UserService;
import br.com.dicasdeumdev.ApiJUnit5.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.ApiJUnit5.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no id: " + id));
        //retorna exception quando null
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class)); //atribui um userDTO para user e salva
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class)); //atribui um userDTO para user e salva
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDTO obj){
        Optional<User> user = userRepository.findByEmail(obj.getEmail());
        if (user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegratyViolationException("Email: "+ obj.getEmail() +  " já cadastrado no Sistema");
        }
    }
}
