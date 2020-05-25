package com.softwaretest.Exceptions;

import com.softwaretest.Models.Product;
import static com.softwaretest.Exceptions.Constants.*;

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

    public static Long idCheck(Long id)
    {
        if (id == null)
        {
            throw PersonalException.exceptionOf(ErrorEnum.MISSING_ARGUMENT, USER_DOES_NOT_EXIST);
        }
        return id;
    }

    public static String usernameCheck(String field)
    {

        if (field.length() > 20)
        {
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_LONG_ARGUMENT, USERNAME_TOO_LONG);
        }

        if (field.length() < 5 && field.length() > 0) {
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_SHORT_ARGUMENT, USERNAME_TOO_SHORT);
        }

        if (field.equals("")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_EQUALS_ZERO_ARGUMENT, FIELD_REQUIRED);
        }

        if(field.contains(" ")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_CONTAINS_SPACES_ARGUMENT, USERNAME_INVALID);
        }

        if (!field.matches("[a-zA-Z0-9.]*")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_CONTAINS_SYMBOLS_ARGUMENT, USERNAME_INVALID);
        }

        return field;
    }

    public static String passwordCheck(String field){

        if (field.length() > 16){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_LONG_ARGUMENT, PASSWORD_TOO_LONG);
        }

        if (field.length() < 8 && field.length() > 0){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_SHORT_ARGUMENT, PASSWORD_TOO_SHORT);
        }

        if (field.equals("")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_EQUALS_ZERO_ARGUMENT, FIELD_REQUIRED);
        }

        return field;
    }

    public static String emailCheck(String field){

        if (field.length() > 200){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_LONG_ARGUMENT, EMAIL_TOO_LONG);
        }

        if(field.equals("")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_EQUALS_ZERO_ARGUMENT, FIELD_REQUIRED);
        }

        if (!field.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_INVALID_FORMAT_ARGUMENT, EMAIL_INVALID);
        }

        return field;
    }


    public static String productNameLength(String field)
    {
        if(field.length() == 0)
        {
            throw PersonalException.exceptionOf(ErrorEnum.MISSING_ARGUMENT, FIELD_REQUIRED);
        }

        if(field.length() > 255)
        {
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_LONG_ARGUMENT, PRODUCT_NAME_TOO_LONG);
        }

        return field;
    }

    public static String productDescriptionLength(String field)
    {

        if(field.length() == 0)
        {
            throw PersonalException.exceptionOf(ErrorEnum.MISSING_ARGUMENT, FIELD_REQUIRED);
        }
        if(field.length() > 255)
        {
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_LONG_ARGUMENT, PRODUCT_DESCRIPTION_TOO_LONG);
        }

        return field;

    }
}
