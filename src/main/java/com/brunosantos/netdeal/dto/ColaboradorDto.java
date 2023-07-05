package com.brunosantos.netdeal.dto;

import com.brunosantos.netdeal.model.Cargo;
import com.brunosantos.netdeal.model.ForcaSenha;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorDto {

    private Long id;
    private String nome;
    private String senha;
    private String cpf;
    private String telefone;
    private Cargo cargo;
    private Double score;
    private ForcaSenha forca;
}
