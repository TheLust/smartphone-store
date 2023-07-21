package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.ManufacturerDao;
import com.example.smartphonestore.dao.ProcessorDao;
import com.example.smartphonestore.entity.Processor;
import com.example.smartphonestore.entity.dto.ProcessorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProcessorService {

    private final ProcessorDao processorDAO;
    private final ManufacturerDao manufacturerDAO;

    public List<Object> getAll() {
        return List.of(processorDAO.findAll());
    }

    public void add(ProcessorDto processorDTO) {
        Processor processor = new Processor();
        processor.setTechnology(processorDTO.getTechnology());
        processor.setModel(processorDTO.getModel());
        processor.setGpuModel(processorDTO.getGpuModel());
        processor.setMaxFrequency(processorDTO.getMaxFrequency());
        processor.setManufacturer(manufacturerDAO.findByName(processorDTO.getManufacturer()));
        processorDAO.save(processor);
    }

    public void update(long id, ProcessorDto processorDTO) {
        Optional<Processor> processor = processorDAO.findById(id);
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
