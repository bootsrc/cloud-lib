package io.github.bootsrc.cloud.api.auth.exception;

public class TokenAuthenticationException extends RuntimeException {

    public TokenAuthenticationException() {
        super();
    }

    public TokenAuthenticationException(int code, String message) {
        super(code + message);
    }
}

