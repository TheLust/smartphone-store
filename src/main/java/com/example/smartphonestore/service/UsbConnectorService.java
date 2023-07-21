package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.UsbConnectorDao;
import com.example.smartphonestore.entity.UsbConnector;
import com.example.smartphonestore.entity.dto.UsbConnectorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsbConnectorService {

    private final UsbConnectorDao usbConnectorDAO;

    public List<Object> getAll() {
        return List.of(usbConnectorDAO.findAll());
    }

    public void add(UsbConnectorDto usbConnectorDTO) {
        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName(usbConnectorDTO.getName());
        usbConnectorDAO.save(usbConnector);
    }

    public void update(long id, UsbConnectorDto usbConnectorDTO) {
        Optional<UsbConnector> usbConnector = usbConnectorDAO.findById(id);
        if (usbConnector.isPresent()) {
            usbConnector.get().setName(usbConnectorDTO.getName());
            usbConnectorDAO.save(usbConnector.get());
        }
    }

    public void delete(long id) {
        usbConnectorDAO.deleteById(id);
    }
}
