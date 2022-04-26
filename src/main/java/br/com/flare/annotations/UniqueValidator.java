package br.com.flare.annotations;

import br.com.flare.exceptionHandler.ApiErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private String domainAttribute;
    private Class<?> klass;
    private String message;

    @Autowired
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(Unique params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
        message = params.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        if (list.size() >= 1)
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, message);

        return list.isEmpty();
    }

}
