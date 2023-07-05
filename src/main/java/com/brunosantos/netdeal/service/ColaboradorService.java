package com.brunosantos.netdeal.service;

import com.brunosantos.netdeal.dto.ColaboradorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ColaboradorService {

    Page<ColaboradorDto> findAll(Pageable pageable);
    ColaboradorDto createColaborador(ColaboradorDto dto);
    ColaboradorDto updateColaborador(Long id, ColaboradorDto dto);
    void deleteColaborador(Long id);

}
