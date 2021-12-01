package com.summerdev.travelstoragemanager.error;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 30.11.2021
 * Time: 22:57
 */
public class HotelExecuteException extends BusinessLogicException {
    public HotelExecuteException(HotelError error, String addText) {
        super(error.code, error.message + addText);
    }
    public HotelExecuteException(HotelError error) {
        super(error.code, error.message);
    }

    public HotelExecuteException(long code, String message) {
        super(code, message);
    }

    public HotelExecuteException(String message) {
        super(message);
    }

    public enum HotelError {
        UNKNOWN_ERROR(2000, ""),
        LOCATION_NOT_FOUND_ERROR(2002, "Location not found");

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
