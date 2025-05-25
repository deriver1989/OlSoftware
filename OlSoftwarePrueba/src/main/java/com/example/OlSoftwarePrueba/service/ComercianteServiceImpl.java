package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.entities.ComercianteEntity;
import com.example.OlSoftwarePrueba.reporitories.ComercianteRepository;
import com.example.OlSoftwarePrueba.request.ComercianteConsultaIdDTO;
import com.example.OlSoftwarePrueba.request.ComercianteDTO;
import com.example.OlSoftwarePrueba.request.ComercianteFiltro;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComercianteServiceImpl {

    @Autowired
    private ComercianteRepository comercianteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Boolean guardarComerciante(ComercianteDTO request){
        ComercianteEntity c = ((request.getId() == null)
                ? new ComercianteEntity()
                : comercianteRepository.findById(request.getId()).orElse(null));
        if(c != null){
            c.setNombre(request.getNombre());
            c.setMunicipio(request.getMunicipio());
            c.setTelefono(request.getTelefono());
            c.setCorreo_electronico(request.getEmail());
            c.setFecha_registro(request.getFecha_registro());
            c.setEstado(request.getEstado());
            c.setFecha_actualizacion(LocalDateTime.now());
            c.setUsuario(request.getUsuario());
            comercianteRepository.save(c);
            return true;
        }else {
            return false;
        }

    }



    public Boolean actualizarComerciante(ComercianteDTO request){

        ComercianteEntity c =  comercianteRepository.findById(request.getId()).orElse(null);
        if(c != null){
            c.setNombre(request.getNombre());
            c.setMunicipio(request.getMunicipio());
            c.setTelefono(request.getTelefono());
            c.setCorreo_electronico(request.getEmail());
            c.setFecha_registro(request.getFecha_registro());
            c.setEstado(request.getEstado());
            c.setFecha_actualizacion(LocalDateTime.now());
            c.setUsuario(request.getUsuario());
            comercianteRepository.save(c);
            return true;
        }else {
            return false;
        }

    }

    public List<ComercianteConsultaIdDTO>  consultarComerciante() throws Exception {

        ArrayList<Exception> excepciones=new ArrayList<>();
        List<Object[]> resultado = new ArrayList<>();
        ArrayList<ResultSet> resultadoConsulta = new ArrayList<>();;
        ((org.hibernate.internal.SessionImpl)entityManager.getDelegate()).doWork(x->{
            try{
                java.sql.CallableStatement s=x.prepareCall("BEGIN ?:=PKS_COMERCIANTE.consultar_comerciantes_cursor; END;");
                s.registerOutParameter(1, OracleTypes.CURSOR);
                //s.setInt(2,id);
                s.executeUpdate();
                resultadoConsulta.add ((ResultSet) s.getObject(1));
            }catch(Exception ex){
                excepciones.add(ex);
            }
        });
        if(excepciones.size()>0){
            throw excepciones.get(0);
        }
        List<ComercianteConsultaIdDTO> listado =  new ArrayList<>();
        if(!resultadoConsulta.isEmpty()) {
            ResultSet resulset = resultadoConsulta.get(0);
            while (resulset.next()) {
                ComercianteConsultaIdDTO dato = ComercianteConsultaIdDTO
                        .builder()
                        .nombre(resulset.getString("nombre"))
                        .municipio(resulset.getString("municipio"))
                        .telefono(resulset.getString("telefono"))
                        .email(resulset.getString("correo_electronico"))
                        .fecha_registro(resulset.getDate("fecha_registro"))
                        .estado(resulset.getString("estado"))
                        .activos(resulset.getDouble("ingresos"))
                        .empleados(resulset.getLong("num_empleados"))
                        .build();
                listado.add(dato);
            }
        }

        return listado;
    }


    @Transactional
    public Boolean eliminarComerciante(Long request){
        ComercianteEntity c =  comercianteRepository.findById(request).orElse(null);
        if(c != null){
            comercianteRepository.delete(c);
            return true;
        }else {
            return false;
        }
    }

    public ComercianteEntity consultarPorId(Long id) throws Exception {
        return  comercianteRepository.findById(id).orElse(null);
    }

    public Page<ComercianteEntity> consultarComerciantePaginado (ComercianteFiltro filtro, Pageable pageable) {
            Specification<ComercianteEntity> spec = ComercianteSpecification.filtro(filtro);
            return comercianteRepository.findAll(spec, pageable);
    }

    public Boolean cambiarEstado(Long id, String estado){
        ComercianteEntity c =  comercianteRepository.findById(id).orElse(null);
        if(c != null){
            c.setEstado(estado);
            comercianteRepository.save(c);
            return true;
        }else {
            return false;
        }
    }

}
