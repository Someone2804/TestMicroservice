package com.example.microservice.api.model;

import com.example.microservice.domain.model.utils.SpecificationUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public class BaseDto {

    private List<Long> id;

    public <T> Specification<T> getBaseSpecification(SingularAttribute<T, Long> attribute){
        return SpecificationUtils.in(attribute, id, true);
    }

    public List<Long> getId() {
        return id;
    }

    public void setId(List<Long> id) {
        this.id = id;
    }
}
