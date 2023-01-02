package br.com.pb.msproduct.framework.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException() {
        super(String.format("The requested id does not exist"));
    }
}
