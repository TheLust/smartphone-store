package com.example.smartphonestore.validator;

import com.example.smartphonestore.entity.UsbConnector;
import com.example.smartphonestore.service.UsbConnectorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsbConnectorValidator implements Validator {

    private final UsbConnectorService usbConnectorService;

    @Autowired
    public UsbConnectorValidator(UsbConnectorService usbConnectorService) {
        this.usbConnectorService = usbConnectorService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return UsbConnector.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        UsbConnector usbConnector = (UsbConnector) target;

        if (usbConnectorService.getByName(usbConnector.getName()) != null) {
            errors.rejectValue("name", "Usb connector with this name already exists.");
        }
    }
}
