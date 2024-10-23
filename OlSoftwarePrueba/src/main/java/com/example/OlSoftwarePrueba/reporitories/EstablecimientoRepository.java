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

    @Query(value = "SELECT  \n" +
            "     nombre, \n" +
            "     departamento, \n" +
            "     municipio, \n" +
            "     telefono, \n" +
            "     email, \n" +
            "     fecha_registro, \n" +
            "     estado,\n" +
            "     (SELECT count(*)as  cantidad_establecimientos FROM establecimiento where comerciante_id = t.id) as cantidad_establecimientos,\n" +
            "     (SELECT sum(ingresos)as  ingresos FROM establecimiento where comerciante_id = t.id) as activos,\n" +
            "     (SELECT sum(num_empleados)as  n_empleados FROM establecimiento where comerciante_id = t.id) as num_empleados\n" +
            "FROM comerciante t where t.id = ?1", nativeQuery = true)
    List<Object[]> consultarEstablecimientos (Long id);

}
