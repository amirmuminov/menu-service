package kz.muminov.menuservice.util;

public enum MessageCode {

    MEAL_DOES_NOT_EXIST(1, "Meal with this id does not exist"),
    MENU_DOES_NOT_EXIST(2, "Menu with this id does not exist");

    int errorCode;
    private String defaultMessage;

    MessageCode(int code, String defaultMessage){
        this.errorCode = code;
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
