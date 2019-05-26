package com.example.pradeoga.myapplication.network;


public enum ErrorCode {
    OTHER(0),
    IO_EXCEPTION(1),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    NO_SERVICE(500),
    LOGIN_EXISTS(113);

    private interface ErrorMessage {
        String FORMAT = "%d : %s";
        String IO_EXCEPTION = "Request failed";
        String UNAUTHORIZED = "Unauthorized access";
        String NOT_FOUND = "Resource not found";
        String OTHER = "Error occurred";
        String LOGIN_EXISTS = "The login is already used by a subscriber in this business unit.";
    }

    private final int mErrorCode;

    ErrorCode(final int errorCode) {
        mErrorCode = errorCode;
    }

    public static ErrorCode mapErrorCode(final int errorCodeValue) {
        ErrorCode errorCode = OTHER;
        for (final ErrorCode code : ErrorCode.values()) {
            if (code.mErrorCode == errorCodeValue) {
                errorCode = code;
            }
        }
        return errorCode;
    }

    public static String getErrorMessage(final ErrorCode errorCode) {
        final int code = errorCode.mErrorCode;
        switch (errorCode) {
            case IO_EXCEPTION: {
                return String.format(ErrorMessage.FORMAT, code, ErrorMessage.IO_EXCEPTION);
            }
            case UNAUTHORIZED: {
                return String.format(ErrorMessage.FORMAT, code, ErrorMessage.UNAUTHORIZED);
            }
            case NOT_FOUND: {
                return String.format(ErrorMessage.FORMAT, code, ErrorMessage.NOT_FOUND);
            }
            case LOGIN_EXISTS: {
                return String.format(ErrorMessage.FORMAT, code, ErrorMessage.LOGIN_EXISTS);
            }
            default: {
                return String.format(ErrorMessage.FORMAT, code, ErrorMessage.OTHER);
            }
        }
    }
}
