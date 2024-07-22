package br.com.douglas.Catalogo.services.services.exceptions;

// RunTimeException não precisa obrigatoriamente tratar o Exception sim
public class DataBaseException extends RuntimeException{

    public DataBaseException(String msg){
        super(msg);
    }
}
