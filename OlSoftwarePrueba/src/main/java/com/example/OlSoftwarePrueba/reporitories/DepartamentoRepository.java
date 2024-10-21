package com.example.OlSoftwarePrueba.reporitories;

import com.example.OlSoftwarePrueba.entities.DepartamentoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends CrudRepository<DepartamentoEntity, Long> {

    Optional<DepartamentoEntity> findByNombre (String username);

}
