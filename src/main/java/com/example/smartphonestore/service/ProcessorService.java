package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.ManufacturerDAO;
import com.example.smartphonestore.dao.ProcessorDAO;
import com.example.smartphonestore.entity.ProcessorEntity;
import com.example.smartphonestore.entity.dto.ProcessorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProcessorService {

    private final ProcessorDAO processorDAO;
    private final ManufacturerDAO manufacturerDAO;

    public List<Object> getAll() {
        return List.of(processorDAO.findAll());
    }

    public void add(ProcessorDTO processorDTO) {
        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(processorDTO.getTechnology());
        processor.setModel(processorDTO.getModel());
        processor.setGpuModel(processorDTO.getGpuModel());
        processor.setMaxFrequency(processorDTO.getMaxFrequency());
        processor.setManufacturer(manufacturerDAO.findByName(processorDTO.getManufacturer()));
        processorDAO.save(processor);
    }

    public void update(long id, ProcessorDTO processorDTO) {
        Optional<ProcessorEntity> processor = processorDAO.findById(id);
        if (processor.isPresent()) {
            processor.get().setTechnology(processorDTO.getTechnology());
            processor.get().setModel(processorDTO.getModel());
            processor.get().setGpuModel(processorDTO.getGpuModel());
            processor.get().setMaxFrequency(processorDTO.getMaxFrequency());
            processor.get().setManufacturer(manufacturerDAO.findByName(processorDTO.getManufacturer()));
            processorDAO.save(processor.get());
        }
    }

    public void delete(long id) {
        processorDAO.deleteById(id);
    }
}
