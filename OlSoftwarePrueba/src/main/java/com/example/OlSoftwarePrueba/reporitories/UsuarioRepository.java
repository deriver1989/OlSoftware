package com.example.OlSoftwarePrueba.reporitories;

import com.example.OlSoftwarePrueba.entities.User;
import com.example.OlSoftwarePrueba.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail (String username);

}
