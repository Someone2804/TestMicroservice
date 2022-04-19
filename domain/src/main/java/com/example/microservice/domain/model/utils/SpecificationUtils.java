package com.example.microservice.domain.model.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;

public class SpecificationUtils {

    private SpecificationUtils(){
    }

    public static <T, V> Specification<T> is(String field, V value, boolean isNeedNullValueCheck) {
        return nullValueCheck(value, isNeedNullValueCheck, (root, query, builder) -> {
            query.distinct(true);
            return builder.and(builder.equal(createPath(field, root), value));
        });
    }

    public static <T> Specification<T> like(String field, String value, boolean isNeedNullValueCheck) {
        return nullValueCheck(value, isNeedNullValueCheck, (root, query, builder) -> {
            query.distinct(true);
            return builder.and(builder.like(createPath(field, root), "%" + value + "%"));
        });
    }

    public static <T, V> Specification<T> in(SingularAttribute<T, V> field, Collection<V> value, boolean isNeedNullValueCheck) {
        return nullValueCheck(value, isNeedNullValueCheck, (root, query, builder) -> {
            query.distinct(true);
            return root.get(field).in(value);
        });
    }

    private static <T, V> Specification<T> nullValueCheck(V value, boolean isNeedNullValueCheck, Specification<T> specification) {
        if (isNeedNullValueCheck && value == null) {
            return emptySpecification();
        }
        return specification;
    }

    private static <T, V> Path<V> createPath(String fieldPath, Root<T> root) {
        if (fieldPath == null || fieldPath.equals("")) {
            throw new IllegalArgumentException("Field path can't be null");
        }

        ArrayDeque<String> fields = new ArrayDeque<>(Arrays.asList(fieldPath.split("[.]")));
        if (fields.size() == 1) {
            return root.get(fields.poll());
        }

        String lastField = fields.pollLast();
        Join<T, V> join = root.join(String.valueOf(fields.pollFirst()), JoinType.INNER);
        for (String field : fields) {
            join = join.join(field, JoinType.INNER);
        }
        return join.get(lastField);
    }

    private static <T> Specification<T> emptySpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

}
