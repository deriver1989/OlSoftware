package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.entities.User;
import com.example.OlSoftwarePrueba.reporitories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean esAdministrador (Authentication authentication){
        String username = authentication.getName();
        User usuario = usuarioRepository.findByEmail(username).orElse(null);
        if(usuario != null){
            if(usuario.getRol().equals("ADMIN")){
                return Boolean.TRUE;
            }else{
                return Boolean.FALSE;
            }
        }else{
            return Boolean.FALSE;
        }
    }

}
