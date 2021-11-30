package com.summerdev.travelstoragemanager.error;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 01.12.2021
 * Time: 0:09
 */
public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(BusinessError error) {
        super(error.message);
        this.code = error.code;
    }

    public BusinessLogicException(String message, long code) {
        super(message);
        this.code = code;
    }

    public BusinessLogicException(String message) {
        super(message);
        this.code = BusinessError.EMPTY_ERROR_CODE.code;
    }

    private final long code;

    @Override
    public String toString() {
        return "BusinessLogicException{" +
                "code=" + code +
                "message=" + super.getMessage() +
                '}';
    }

    public enum BusinessError {
        EMPTY_ERROR_CODE(1000, ""),
        JSON_PARSE_ERROR_CODE(1001, "An error occurred while parsing text ")
        ;

        private final long code;
        private final String message;

        BusinessError(long code, String message) {
            this.code = code;
            this.message = message;
        }

        public long getCode() {
            return code;
        }
    }

}
