package com.example.OlSoftwarePrueba.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, name = "NOMBRE")
  private String name;

  @Column(name = "CORREO_ELECTRONICO" ,nullable = false, unique = true)
  private String email;

  @Column(name = "PASS", nullable = false)
  private String password;

  @Column( name = "ROL")
  private String rol;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;


}
