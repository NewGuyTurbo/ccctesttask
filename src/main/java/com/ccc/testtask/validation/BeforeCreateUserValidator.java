package com.ccc.testtask.validation;

/**
 * @author Kirill Milinevskiy
 */

import com.ccc.testtask.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(value = "beforeCreateUserValidator")
public class BeforeCreateUserValidator implements Validator {
    private static String NAME_PATTERN = "[a-zA-Zа-яА-Я]+";
    private static String ADDRESS_PATTERN = "[a-zA-Zа-яА-Я0-9\\s]+";
    private static String PASSWORD_PATTERN = "^(?=.*\\d).{4,8}$";


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (checkEmpty(user.getName()))
            errors.reject("name.empty", "Field 'name' is empty");
        if (!checkByPattern(user.getName(), NAME_PATTERN))
            errors.reject("name.incorrect", "Name can only consist of letters");

        if (checkEmpty(user.getPassword()))
            errors.reject("password.empty", "Field 'password' is empty");
        if (!checkByPattern(user.getPassword(), PASSWORD_PATTERN))
            errors.reject("password.incorrect", "Password must be between 4 and 8 digits long and include at least one numeric digit");

        if (checkEmpty(user.getAddress()))
            errors.reject("address.empty", "Field 'address' is empty");
        if (!checkByPattern(user.getAddress(), ADDRESS_PATTERN))
            errors.reject("address.incorrect", "Address can only consist of letters and numbers");
    }

    private boolean checkEmpty(String input) {
        return input == null || input.trim().length() == 0;
    }

    private boolean checkByPattern(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
