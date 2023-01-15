package br.com.dicasdeumdev.ApiJUnit5.services.implementation;

import br.com.dicasdeumdev.ApiJUnit5.domain.User;
import br.com.dicasdeumdev.ApiJUnit5.domain.dto.UserDTO;
import br.com.dicasdeumdev.ApiJUnit5.repositories.UserRepository;
import br.com.dicasdeumdev.ApiJUnit5.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.ApiJUnit5.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Jose Cruz";
    public static final String EMAIL = "josecruz@gmail.com@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        User response = userServiceImpl.findById(ID);

        assertNotNull(response); //assegura que não será null
        assertEquals(User.class, response.getClass()); //assegure que ambos são iguais
        assertEquals(ID, response.getId()); //assegure que ambos são iguais
        assertEquals(NAME, response.getName()); //assegure que ambos são iguais
        assertEquals(EMAIL, response.getEmail());//assegure que ambos são iguais
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            userServiceImpl.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass()); //assegure que ambos são iguais
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage()); //assegure que as messagens de erro sejam iguais
        }
    }

    @Test
    void whenFindallThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userServiceImpl.findAll();
        assertNotNull((response)); //assegura que não será null
        assertEquals(1, response.size()); //assegure que o tamanho da lista seja 1
        assertEquals(User.class, response.get(INDEX).getClass()); //assegure que o objo 0 seja da classe user
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);
        User response = userServiceImpl.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }
    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(2);
            userServiceImpl.create(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("Email: "+ optionalUser.get().getEmail() +  " já cadastrado no Sistema", ex.getMessage());
        }

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);
        User response = userServiceImpl.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }
    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(2);
            userServiceImpl.create(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("Email: "+ optionalUser.get().getEmail() +  " já cadastrado no Sistema", ex.getMessage());
        }

    }
    @Test
    void deleteWhitSuccess() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(userRepository).deleteById(anyInt()); //não faça nada quando o metodo for chamado passando um int
        userServiceImpl.delete(ID);
        verify(userRepository, times(1)).deleteById(anyInt());
        //verifica quantas vezes o metodo foi chamado e espera que o metodo delete seja chamado uma vez
    }
    @Test
    void deleteWhitObjectNotFoundException(){
        when(userRepository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            userServiceImpl.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}