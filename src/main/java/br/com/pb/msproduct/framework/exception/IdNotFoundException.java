package br.com.pb.msproduct.framework.exception;

public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException(Long id){
        super(String.format("Payment id %s does not exist", id));
    }
}
