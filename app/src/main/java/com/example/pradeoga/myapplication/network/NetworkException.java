package com.example.pradeoga.myapplication.network;


public class NetworkException extends Exception {

    private final ErrorCode mErrorCode;

    NetworkException(final ErrorCode errorCode) {
        mErrorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return mErrorCode;
    }

    @Override
    public String getMessage() {
        return ErrorCode.getErrorMessage(mErrorCode);
    }
}
