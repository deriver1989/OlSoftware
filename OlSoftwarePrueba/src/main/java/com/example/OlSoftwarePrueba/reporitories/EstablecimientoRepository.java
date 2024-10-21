package com.example.OlSoftwarePrueba.reporitories;

import com.example.OlSoftwarePrueba.entities.EstablecimientoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablecimientoRepository extends CrudRepository<EstablecimientoEntity, Long> {

    @Query("SELECT  u FROM EstablecimientoEntity u WHERE u.comerciante_id = ?1")
    List<EstablecimientoEntity> establecimeintosPorComerciante (Long id);
}
