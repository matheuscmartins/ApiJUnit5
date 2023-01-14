package br.com.dicasdeumdev.ApiJUnit5.resources;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;
import br.com.dicasdeumdev.ApiJUnit5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<User> fingById(@PathVariable Integer id){
        return ResponseEntity.ok().body(userService.findById(id));
    }
}
