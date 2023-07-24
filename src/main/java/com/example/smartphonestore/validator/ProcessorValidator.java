package com.example.smartphonestore.validator;

import com.example.smartphonestore.entity.Processor;
import com.example.smartphonestore.service.ProcessorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProcessorValidator implements Validator {

    private final ProcessorService processorService;

    @Autowired
    public ProcessorValidator(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return Processor.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Processor processor = (Processor) target;

        if (processorService.getByModel(processor.getModel()) != null) {
            errors.rejectValue("model", "Processor with this model name already exists.");
        }
    }
}
