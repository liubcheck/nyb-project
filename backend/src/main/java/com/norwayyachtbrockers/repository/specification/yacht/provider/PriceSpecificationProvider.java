package com.norwayyachtbrockers.repository.specification.yacht.provider;

import com.norwayyachtbrockers.model.Yacht;
import com.norwayyachtbrockers.repository.specification.SpecificationProvider;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Yacht> {
    @Override
    public String getKey() {
        return "price";
    }

    @Override
    public Specification<Yacht> getSpecification(Object param) {
        if (param instanceof BigDecimal[] range) {
            if (range[1] == null) {
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThanOrEqualTo(root.get("price"), range[0]);
            } else if (range[0] == null) {
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lessThanOrEqualTo(root.get("price"), range[1]);
            }
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("price"), range[0], range[1]);
        } else {
            throw new IllegalArgumentException("Invalid parameter type for price range");
        }
    }
}
