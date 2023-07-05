package com.brunosantos.netdeal.controller;

import com.brunosantos.netdeal.dto.ColaboradorDto;
import com.brunosantos.netdeal.model.ForcaSenha;
import com.brunosantos.netdeal.service.ColaboradorService;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin("*")
@RequestMapping("/colaboradores")
@Api(value = "Colaboradores")
public class ColaboradorController {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private ColaboradorService colaboradorService;

    public ColaboradorController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @ApiOperation(value = "Mostra lista de colaboradores")
    public Page<ColaboradorDto> listColaborador(@PageableDefault(size = 10)Pageable pageable) {
        return colaboradorService.findAll(pageable);
    }
    public ColaboradorDto validarSenha(ColaboradorDto colaboradorDto){
        Integer numero = 0;
        Integer letra = 0;
        Integer caracterEspecial = 0;
        Double score = 0.0;
        String senha = colaboradorDto.getSenha();
        for(int i = 0; i < senha.length(); i++){
            char s = senha.charAt(i);
            if (Character.isDigit(s)) {
                numero++;
            } else if (Character.isLetter(s)) {
                letra++;
            } else {
                caracterEspecial++;
            }
        }
        score = (double) ((senha.length() * 4) + (numero * 4) + (letra * 2) + (caracterEspecial * 6));
        if(score >=0 && score<=30){
            colaboradorDto.setForca(ForcaSenha.MUITO_FRACO);
            colaboradorDto.setScore(score = score);
        } else if(score >=31 && score<=60){
            colaboradorDto.setForca(ForcaSenha.FRACO);
            colaboradorDto.setScore(score = score);
        }else if(score >=61 && score<=80){
            colaboradorDto.setForca(ForcaSenha.BOM);
            colaboradorDto.setScore(score = score);
        }else if(score >=81 && score<=90){
            colaboradorDto.setForca(ForcaSenha.FORTE);
            colaboradorDto.setScore(score = score);
        } else {
            colaboradorDto.setForca(ForcaSenha.MUITO_FORTE);
            colaboradorDto.setScore(score = 100.0);
        }
        colaboradorDto.setSenha(passwordEncoder.encode(colaboradorDto.getSenha()));
        return colaboradorDto;
    }
    @PostMapping
    @ApiOperation(value = "Cadastra um colaborador")
    public ResponseEntity<ColaboradorDto> registerColaborador(@ApiParam(name = "colaborador", value = "Colaborador a ser cadastrado") @RequestBody @Valid ColaboradorDto colaboradorDto, UriComponentsBuilder uriComponentsBuilder){
        colaboradorDto = validarSenha(colaboradorDto);
        ColaboradorDto colaboradorDtoRegister = colaboradorService.createColaborador(colaboradorDto);
        URI cargo = uriComponentsBuilder.path("/colaboradores/{id}").buildAndExpand(colaboradorDtoRegister.getId()).toUri();
        return ResponseEntity.created(cargo).body(colaboradorDtoRegister);
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um colaborador")
    public ResponseEntity<ColaboradorDto> updateColaborador(@ApiParam(name = "id", value = "id do colaborador a ser atualizado") @PathVariable @NotNull Long id, @RequestBody @Valid @ApiParam(name = "colaborador", value = "Colaborador a ser alualizado") ColaboradorDto colaboradorDto){
        colaboradorDto = validarSenha(colaboradorDto);
        ColaboradorDto colaboradorDtoUpdate = colaboradorService.updateColaborador(id, colaboradorDto);
        return ResponseEntity.ok(colaboradorDtoUpdate);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um colaborador")
    public ResponseEntity<ColaboradorDto> deleteColaborador(@ApiParam(name = "id", value = "id do colaborador a ser deletado") @PathVariable@NotNull Long id){
        colaboradorService.deleteColaborador(id);
        return ResponseEntity.noContent().build();
    }
}
