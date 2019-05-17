package com.donaldy.utils.constraints.validators;

import com.donaldy.utils.constraints.annotations.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

    private DateTime dateTime;

    /**
     * 主要用于初始化，它可以获得当前注解的所有属性
     */
    @Override
    public void initialize(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 进行约束验证的主体方法，
     * 其中 value 就是验证参数的具体实例，
     * context 代表约束执行的上下文环境
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果 value 为空则不进行格式验证，为空验证可以使用 @NotBlank @NotNull @NotEmpty 等注解来进行控制，职责分离
        if(value == null){
            return true;
        }

        String format = dateTime.format();
        if(value.length() != format.length()){
            return false;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try{
            simpleDateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }
}