package br.com.dicasdeumdev.ApiJUnit5.resources;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;
import br.com.dicasdeumdev.ApiJUnit5.domain.dto.UserDTO;
import br.com.dicasdeumdev.ApiJUnit5.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Jose Cruz";
    public static final String EMAIL = "josecruz@gmail.com@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final Integer INDEX = 0;

    @InjectMocks
    private UserResource userResource;
    @Mock
    private ModelMapper mapper;
    @Mock
    private UserService userService;
    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(userService.findById(anyInt())).thenReturn(user); //verifica se irá trazer um obj User
        when(mapper.map(any(), any())).thenReturn(userDTO); // verifica se a conversão retornara um objUserDTO

        ResponseEntity<UserDTO> response = userResource.findById(ID);
        assertNotNull(response); //assegura que a resposta não será nula
        assertNotNull(response.getBody()); //assegura que o corpo da resposta não é null
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que srão o mesmo tipo de class

        assertEquals(UserDTO.class, response.getBody().getClass());
        //assegura que o body de retorno é o mesmo da Classe UserDTO

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }
    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        when(userService.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO); //assegura que a conversão trará obj UserDTO
        ResponseEntity<List<UserDTO>> response = userResource.findAll();

        assertNotNull(response); //assegura que não será null
        assertNotNull(response.getBody()); //assegura que o body não sera null
        assertEquals(HttpStatus.OK, response.getStatusCode());//assegura que o Status do request seja OK
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que o retorno será da classe ResponseEntity
        assertEquals(ArrayList.class, response.getBody().getClass()); //assegura que o body vira um arrayList

        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());
        //assegura que o INDEX da lista será um objo UserDTO

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
        //assegura que os dados de retorno são iguais aos pasados por parametro
    }
    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}