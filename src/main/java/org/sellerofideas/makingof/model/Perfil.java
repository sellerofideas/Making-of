package org.sellerofideas.makingof.model;

import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "perfis")
@Getter @Setter
@Data
public class Perfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;
    @Column(name = "", length = 100,nullable = false)
    private String nome;
    @Column(name = "", length = 100,nullable = false)
    private String nomeArtistico;
    @Column(name = "", length = 100,nullable = false)
    private String email;
    @Column(name = "", length = 255,nullable = false)
    private String senha;
    @Column(name = "", length = 300,nullable = false)
    @Type(type = "text")
    private String bio;
    @Column(name = "", length = 50,nullable = false)
    private Date idade;

    
}
