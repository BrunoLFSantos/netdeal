package com.brunosantos.netdeal.service.serviceImpl;

import com.brunosantos.netdeal.dto.ColaboradorDto;
import com.brunosantos.netdeal.model.Colaborador;
import com.brunosantos.netdeal.repository.ColaboradorRepository;
import com.brunosantos.netdeal.service.ColaboradorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Page<ColaboradorDto> findAll(Pageable pageable) {
        return colaboradorRepository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, ColaboradorDto.class));
    }
    @Override
    public ColaboradorDto createColaborador(ColaboradorDto dto) {
        Colaborador colaborador = modelMapper.map(dto, Colaborador.class);
        colaboradorRepository.save(colaborador);
        return modelMapper.map(colaborador, ColaboradorDto.class);
    }

    @Override
    public ColaboradorDto updateColaborador(Long id, ColaboradorDto dto) {
        Colaborador colaborador = modelMapper.map(dto, Colaborador.class);
        colaborador.setId(id);
        colaborador = colaboradorRepository.save(colaborador);
        return modelMapper.map(colaborador, ColaboradorDto.class);
    }

    @Override
    public void deleteColaborador(Long id) {
        colaboradorRepository.deleteById(id);
    }


}
