package com.summerdev.travelstoragemanager.error;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 30.11.2021
 * Time: 22:57
 */
public class HotelExecuteException extends BusinessLogicException {
    public HotelExecuteException(HotelError error) {
        super(error.message, error.code);
    }

    public HotelExecuteException(String message, long code) {
        super(message, code);
    }

    public HotelExecuteException(String message) {
        super(message);
    }

    public enum HotelError {
        ;

        private final long code;
        private final String message;

        HotelError(long code, String message) {
            this.code = code;
            this.message = message;
        }

        public long getCode() {
            return code;
        }
    }
}
