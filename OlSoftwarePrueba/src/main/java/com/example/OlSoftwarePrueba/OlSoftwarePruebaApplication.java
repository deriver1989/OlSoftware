package com.example.OlSoftwarePrueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OlSoftwarePruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlSoftwarePruebaApplication.class, args);
	}

	//@Autowired
	//private MunicipioRepository municipioRepository;


	/*@Bean
	CommandLineRunner init(){
		return args -> {

			municipioRepository.save(MunicipioEntity.builder().nombre("MARIA LA BAJA").build());
			municipioRepository.save(MunicipioEntity.builder().nombre("EL CARMEN DE BOLIVAR").build());

			departamentoRepository.save(DepartamentoEntity.builder().nombre("ATLANTICO").build());
			departamentoRepository.save(DepartamentoEntity.builder().nombre("BOLIVAR").build());
		};

	}*/

}
