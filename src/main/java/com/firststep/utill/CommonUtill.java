package com.firststep.utill;

/**
 * Common utill functions.
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */
public interface CommonUtill {

    public interface Errors {
        public static final String OBJECT_NOT_FOUND= "Object not found";
    }
    public interface Messages {
        public static final String SUCCESS= "Success";
        public static final String ERROR= "Error";
    }

    public interface CouseStatus {
        public static final String CREATED= "CREATED";
        public static final String START= "START";
        public static final String CANCEL= "CANCEL";
    }
}
