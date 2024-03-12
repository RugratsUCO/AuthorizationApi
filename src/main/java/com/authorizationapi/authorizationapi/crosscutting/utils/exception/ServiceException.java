package com.authorizationapi.authorizationapi.crosscutting.utils.exception;

public class ServiceException extends ExceptionService {
    private static final long serialVersionUID = -4636066115510646740L;

    protected ServiceException(String technicalMessage, String userMessage, Throwable rootCause) {
        super(technicalMessage, userMessage, rootCause, ExceptionType.SERVICE);
        // TODO Auto-generated constructor stub
    }

    public static ServiceException create(final String technicalMessage, final String userMessage, final Throwable rootCause) {
        return new ServiceException(technicalMessage, userMessage, rootCause);
    }

    public static ServiceException create(final String userMessage) {
        return new ServiceException(userMessage, userMessage, new Exception());
    }

    public static ServiceException create(final String technicalMessage, final String userMessage) {
        return new ServiceException(technicalMessage, userMessage, new Exception());
    }

}
