package br.com.douglas.Catalogo.services.services.exceptions;

// RunTimeException não precisa obrigatoriamente tratar o Exception sim
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
