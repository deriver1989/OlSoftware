package com.example.OlSoftwarePrueba.reporitories;

import com.example.OlSoftwarePrueba.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername (String username);

}
