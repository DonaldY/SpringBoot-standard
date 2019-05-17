package com.donaldy.utils.constraints.annotations;

import com.donaldy.utils.constraints.validators.DateTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER}) // 约束注解应用的目标元素类型
@Retention(RetentionPolicy.RUNTIME) // 约束注解应用的时机
@Constraint(validatedBy = DateTimeValidator.class)  // 与约束注解关联的验证器
public @interface DateTime {

    /**
     * 约束注解验证时的输出消息 - 关键字段
     */
    String message() default "格式错误";

    /**
     * 约束注解验证时的格式
     */
    String format() default "yyyy-MM-dd";

    /**
     * 约束注解在验证时所属的组别 - 关键字段
     */
    Class<?>[] groups() default {};

    /**
     * 约束注解的有效负载 - 关键字段
     */
    Class<? extends Payload>[] payload() default {};

}