package com.code.core.util;

import com.code.core.enums.ErrorEnum;
import com.code.core.exception.ParamInvalidException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtils {

    /**
     * 使用hibernate的注解来进行验证
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 功能描述: <br>
     * 〈注解验证参数〉
     *
     * @param obj
     */
    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
           String errorMessage = constraintViolations.iterator().next().getMessage();
            ParamInvalidException e = new ParamInvalidException(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(errorMessage));
            throw e;
        }
    }
}
