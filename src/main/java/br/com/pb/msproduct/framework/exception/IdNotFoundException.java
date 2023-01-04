
package br.com.pb.msproduct.framework.exception;
public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(Long id) {
        super(String.format("O Payment de id %s nao existe!", id));


    }
}


