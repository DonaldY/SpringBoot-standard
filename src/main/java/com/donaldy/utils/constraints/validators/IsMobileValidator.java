package com.donaldy.utils.constraints.validators;

import com.donaldy.utils.constraints.annotations.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private static final String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$";

    public boolean isValid(String value, ConstraintValidatorContext context) {

        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(value);

        return m.matches();

    }

}
