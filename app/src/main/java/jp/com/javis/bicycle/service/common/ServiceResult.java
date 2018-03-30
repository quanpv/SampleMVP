package jp.com.javis.bicycle.service.common;

/**
 * Created by QuanPV on 3/30/18.
 */

public enum  ServiceResult {
    RESULT_SUCCESS(200),
    RESULT_ERROR(1200);

    private int value;

    ServiceResult(int value) {
        this.value = value;
    }

    public static ServiceResult fromInteger(int x) {
        switch(x) {
            case 200:
                return RESULT_SUCCESS;
            case 1200:
                return RESULT_ERROR;
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
