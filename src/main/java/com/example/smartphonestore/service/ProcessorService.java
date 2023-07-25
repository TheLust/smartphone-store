package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.ProcessorDao;
import com.example.smartphonestore.entity.Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProcessorService {

    private final ProcessorDao processorDao;

    public List<Processor> getAll() {
        return processorDao.findAll();
    }

    public Processor getByModel(String model) {
        return processorDao.findByModel(model);
    }

    public Optional<Processor> getById(Long id) {
        return processorDao.findById(id);
    }

    public void add(Processor processor) {
        processorDao.save(processor);
    }

    public void update(Processor processorToUpdate, Processor updatedProcessor) {
        if (updatedProcessor.getTechnology() != null) {
            processorToUpdate.setTechnology(updatedProcessor.getTechnology());
        }

        if (updatedProcessor.getModel() != null) {
            processorToUpdate.setModel(updatedProcessor.getModel());
        }

        if (updatedProcessor.getGpuModel() != null) {
            processorToUpdate.setGpuModel(updatedProcessor.getGpuModel());
        }

        if (updatedProcessor.getMaxFrequency() != null) {
            processorToUpdate.setMaxFrequency(updatedProcessor.getMaxFrequency());
        }

        if (updatedProcessor.getManufacturer() != null) {
            processorToUpdate.setManufacturer(updatedProcessor.getManufacturer());
        }

        processorDao.save(processorToUpdate);
    }

    public void delete(Long id) {
        processorDao.deleteById(id);
    }
}
