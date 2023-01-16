package br.com.dicasdeumdev.ApiJUnit5.services.exceptions;

//classe criada para retornar uma exception ao invés de null
public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
