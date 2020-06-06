package br.com.bertino.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
@Data
public class Usuarios {

    @Id
    @GeneratedValue
    private Long id;
    private String usuario;
    private String senha;
}
