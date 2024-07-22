package br.com.douglas.Catalogo.services.services.exceptions;

// RunTimeException não precisa obrigatoriamente tratar o Exception sim
public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String msg){
        super(msg);
    }
}
