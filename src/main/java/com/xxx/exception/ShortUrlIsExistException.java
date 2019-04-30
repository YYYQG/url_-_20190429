package com.xxx.exception;

public class ShortUrlIsExistException extends RuntimeException{

    public ShortUrlIsExistException(String msg){
        super(msg);
    }

}
