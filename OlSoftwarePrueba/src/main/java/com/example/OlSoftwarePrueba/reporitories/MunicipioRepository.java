package com.example.OlSoftwarePrueba.reporitories;

import com.example.OlSoftwarePrueba.entities.MunicipioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipioRepository extends CrudRepository<MunicipioEntity, Long> {

    Optional<MunicipioEntity> findByNombre (String username);

}
