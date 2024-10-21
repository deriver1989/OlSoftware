package com.example.OlSoftwarePrueba.reporitories;

import com.example.OlSoftwarePrueba.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<RoleEntity, Long> {
}
