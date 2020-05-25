package com.softwaretest.Exceptions;

public class ErrorPrerequisites
{
    public static <T> T notNull(T object, String message)
    {
        if (object == null)
        {
            throw PersonalException.exceptionOf(ErrorEnum.MISSING_ARGUMENT, message);
        }

        return object;
    }

    public static String usernameCheck(String field)
    {

        if (field.length() > 20)
        {
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_LONG_ARGUMENT, "Username too long");
        }

        if (field.length() < 5 && field.length() > 0) {
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_SHORT_ARGUMENT, "Username too short");
        }

        if (field.equals("")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_EQUALS_ZERO_ARGUMENT, "Required field");
        }

        if(field.contains(" ")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_CONTAINS_SPACES_ARGUMENT, "Invalid username");
        }

        if (!field.matches("[a-zA-Z0-9.]*")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_CONTAINS_SYMBOLS_ARGUMENT, "Invalid username");
        }

        return field;
    }

    public static String passwordCheck(String field){

        if (field.length() > 16){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_LONG_ARGUMENT, "Password too long");
        }

        if (field.length() < 8 && field.length() > 0){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_SHORT_ARGUMENT, "Password too short");
        }

        if (field.equals("")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_EQUALS_ZERO_ARGUMENT, "Required field");
        }

        return field;
    }

    public static String emailCheck(String field){

        if (field.length() > 200){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_LONG_ARGUMENT, "Email too long");
        }

        if(field.equals("")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_EQUALS_ZERO_ARGUMENT, "Required field");
        }

        if (!field.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_INVALID_FORMAT_ARGUMENT, "Invalid email");
        }

        return field;
    }


    public static String productNameLength(String field)
    {
        if(field.length() == 0)
        {
            throw PersonalException.exceptionOf(ErrorEnum.MISSING_ARGUMENT, "Required field");
        }

        return field;

    }
}
