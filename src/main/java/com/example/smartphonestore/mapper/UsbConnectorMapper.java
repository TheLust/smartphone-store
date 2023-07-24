package com.example.smartphonestore.mapper;

import com.example.smartphonestore.entity.UsbConnector;
import com.example.smartphonestore.entity.dto.UsbConnectorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsbConnectorMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UsbConnectorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UsbConnector convertToUsbConnector(UsbConnectorDto usbConnectorDto) {
        return modelMapper.map(usbConnectorDto, UsbConnector.class);
    }

    public UsbConnectorDto convertToUsbConnectorDto(UsbConnector usbConnector) {
        return modelMapper.map(usbConnector, UsbConnectorDto.class);
    }
}
