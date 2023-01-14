package br.com.dicasdeumdev.ApiJUnit5.resources;

import br.com.dicasdeumdev.ApiJUnit5.domain.dto.UserDTO;
import br.com.dicasdeumdev.ApiJUnit5.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> fingById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class)); //Fonte USER, Destino USERDTO
        //Pega os atributos do User e retorna ao cliente um USERDTO por questão de segurança
    }

    @GetMapping
    private ResponseEntity<List<UserDTO>> findAll() {
        /*
        List<User> list = userService.findAll(); //retorna uma lista de USER
        List<UserDTO> listUserDTO = list.stream().map( //Atribui todos os elementos da listUSER para listUserDTO
            x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
         */

        //metodo resumido
        return ResponseEntity.ok().body(userService.findAll().stream().map(
                x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
        //retorna a lisUserDTO por questão de segurança
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO objDTO) {
        /*
        User objUser = userService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                objUser.getId()).toUri();
                */

        //metodo acima resumido
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                        userService.create(objDTO).getId()).toUri()).build();

    }
}
