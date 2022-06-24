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

    public HotelExecuteException(BusinessError error) {
        super(error);
    }

    public HotelExecuteException(BusinessError error, String additionalText) {
        super(error, additionalText);
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
