package com.dubboss.sk.validator;

import com.dubboss.sk.util.ValidateUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName IsMobileValidate
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/12 20:42
 * @Vertion 1.0
 **/
public class IsMobileValidate implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidateUtil.isMobile(s);
        } else {
            if (StringUtils.isEmpty(s)) {
                return true;
            } else {
                return ValidateUtil.isMobile(s);
            }
        }
    }

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }
}
