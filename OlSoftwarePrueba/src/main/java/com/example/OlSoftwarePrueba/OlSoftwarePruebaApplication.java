package com.example.OlSoftwarePrueba;

import com.example.OlSoftwarePrueba.entities.DepartamentoEntity;
import com.example.OlSoftwarePrueba.entities.MunicipioEntity;
import com.example.OlSoftwarePrueba.reporitories.DepartamentoRepository;
import com.example.OlSoftwarePrueba.reporitories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OlSoftwarePruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlSoftwarePruebaApplication.class, args);
	}

	@Autowired
	private MunicipioRepository municipioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Bean
	CommandLineRunner init(){
		return args -> {

			municipioRepository.save(MunicipioEntity.builder().nombre("MARIA LA BAJA").build());
			municipioRepository.save(MunicipioEntity.builder().nombre("EL CARMEN DE BOLIVAR").build());

			departamentoRepository.save(DepartamentoEntity.builder().nombre("ATLANTICO").build());
			departamentoRepository.save(DepartamentoEntity.builder().nombre("BOLIVAR").build());
		};

	}

}
