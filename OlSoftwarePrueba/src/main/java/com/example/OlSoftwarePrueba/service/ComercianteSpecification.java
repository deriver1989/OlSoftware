package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.entities.ComercianteEntity;
import com.example.OlSoftwarePrueba.request.ComercianteFiltro;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

// ComercianteSpecification.java
public class ComercianteSpecification {
    public static Specification<ComercianteEntity> filtro(ComercianteFiltro f) {
        return (root, query, cb) -> {
            List<Predicate> preds = new ArrayList<>();
            if (f.getNombre() != null) {
                preds.add(cb.like(cb.lower(root.get("nombre")), "%" + f.getNombre().toLowerCase() + "%"));
            }
            if (f.getFechaRegistroDesde() != null) {
                preds.add(cb.greaterThanOrEqualTo(root.get("fecha_registro"), f.getFechaRegistroDesde()));
            }
            if (f.getFechaRegistroHasta() != null) {
                preds.add(cb.lessThanOrEqualTo(root.get("fecha_registro"), f.getFechaRegistroHasta()));
            }
            if (f.getEstado() != null) {
                preds.add(cb.equal(root.get("estado"), f.getEstado()));
            }
            return cb.and(preds.toArray(new Predicate[0]));
        };
    }
}