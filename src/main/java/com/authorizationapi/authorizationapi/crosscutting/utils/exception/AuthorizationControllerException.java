package com.authorizationapi.authorizationapi.crosscutting.utils.exception;

public class AuthorizationControllerException extends AuthorizationException{
    protected AuthorizationControllerException(String technicalMessage, String userMessage, Throwable rootCause) {
        super(technicalMessage, userMessage, rootCause, ExceptionType.CONTROLLER);
    }
    private static final long serialVersionUID = -4636066115510646740L;

    public static AuthorizationControllerException create(final String technicalMessage, final String userMessage, final Throwable rootCause) {
        return new AuthorizationControllerException(technicalMessage, userMessage, rootCause);
    }

    public static AuthorizationControllerException create(final String userMessage) {
        return new AuthorizationControllerException(userMessage, userMessage, new Exception());
    }

    public static AuthorizationControllerException create(final String technicalMessage, final String userMessage) {
        return new AuthorizationControllerException(technicalMessage, userMessage, new Exception());
    }
}

