package br.com.dicasdeumdev.ApiJUnit5.resources.exceptions;

import br.com.dicasdeumdev.ApiJUnit5.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    @InjectMocks
    private  ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(
                new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID),
                new MockHttpServletRequest());

        assertNotNull(response); //assegura que não será null
        assertNotNull(response.getBody()); //assegura que o body não é null
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void dataIntegratyViolationException() {
    }
}