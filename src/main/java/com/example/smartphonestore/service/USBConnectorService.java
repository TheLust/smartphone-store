package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.USBConnectorDAO;
import com.example.smartphonestore.entity.USBConnectorEntity;
import com.example.smartphonestore.entity.dto.USBConnectorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class USBConnectorService {

    private final USBConnectorDAO usbConnectorDAO;

    public List<Object> getAll() {
        return List.of(usbConnectorDAO.findAll());
    }

    public void add(USBConnectorDTO usbConnectorDTO) {
        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName(usbConnectorDTO.getName());
        usbConnectorDAO.save(usbConnector);
    }

    public void update(long id, USBConnectorDTO usbConnectorDTO) {
        Optional<USBConnectorEntity> usbConnector = usbConnectorDAO.findById(id);
        if (usbConnector.isPresent()) {
            usbConnector.get().setName(usbConnectorDTO.getName());
            usbConnectorDAO.save(usbConnector.get());
        }
    }

    public void delete(long id) {
        usbConnectorDAO.deleteById(id);
    }
}
