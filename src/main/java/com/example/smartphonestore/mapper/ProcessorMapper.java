package com.example.smartphonestore.mapper;

import com.example.smartphonestore.entity.Processor;
import com.example.smartphonestore.entity.dto.ProcessorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessorMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ProcessorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Processor convertToProcessor(ProcessorDto processorDto) {
        return modelMapper.map(processorDto, Processor.class);
    }

    public ProcessorDto convertToProcessorDto(Processor processor) {
        return modelMapper.map(processor, ProcessorDto.class);
    }
}
