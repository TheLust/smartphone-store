package com.example.smartphonestore.validator;

import com.example.smartphonestore.entity.updateDto.UpdatedProcessorDto;
import com.example.smartphonestore.service.ProcessorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UpdatedProcessorValidator implements Validator {

    private final ProcessorService processorService;

    @Autowired
    public UpdatedProcessorValidator(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return UpdatedProcessorDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        UpdatedProcessorDto processor = (UpdatedProcessorDto) target;

        if (processorService.getByModel(processor.getModel()) != null) {
            errors.rejectValue("model", "Processor with this model name already exists.");
        }
    }
}
