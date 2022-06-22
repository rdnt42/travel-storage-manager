package com.summerdev.travelstoragemanager.error;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.06.2022
 * Time: 0:18
 */
public class UnExpectedException extends RuntimeException {
    public UnExpectedException(Exception e) {
        super(e);
    }
}
