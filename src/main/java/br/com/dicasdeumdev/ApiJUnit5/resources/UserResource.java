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

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @GetMapping(value = ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class)); //Fonte USER, Destino USERDTO
        //Pega os atributos do User e retorna ao cliente um USERDTO por questão de segurança
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
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
                ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(
                        userService.create(objDTO).getId()).toUri()).build();

    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO objDTO) {
        objDTO.setId(id);
        //User objUser = userService.update(objDTO);

        return ResponseEntity.ok().body(mapper.map(userService.update(objDTO), UserDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
