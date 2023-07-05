package com.brunosantos.netdeal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "colaboradores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    @Size(min = 8)
    private String senha;

    @Column(length = 50, nullable = false)
    private String cpf;

    @Column(length = 50, nullable = false)
    private String telefone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Column(nullable = false)
    private Double score;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ForcaSenha forca;
}
