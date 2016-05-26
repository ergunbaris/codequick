package com.bergun.challenge;

public class HeapSizeNotSupportedException extends RuntimeException {

    private static final long serialVersionUID = -5526577615966690038L;

    public static final String ERROR_MSG = "Min supported heap size is %d "
	    + "your environment available heap size is %d";

    public HeapSizeNotSupportedException(String message) {
	super(message);
    }

}
