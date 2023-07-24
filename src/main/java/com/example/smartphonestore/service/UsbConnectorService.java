package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.UsbConnectorDao;
import com.example.smartphonestore.entity.UsbConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsbConnectorService {

    private final UsbConnectorDao usbConnectorDao;

    public List<UsbConnector> getAll() {
        return usbConnectorDao.findAll();
    }

    public UsbConnector getByName(String name) {
        return usbConnectorDao.findByName(name);
    }

    public Optional<UsbConnector> getById(Long id) {
        return usbConnectorDao.findById(id);
    }

    public void add(UsbConnector usbConnector) {
        usbConnectorDao.save(usbConnector);
    }

    public void update(UsbConnector usbConnectorToUpdate, UsbConnector updatedUsbConnector) {
        usbConnectorToUpdate.setName(updatedUsbConnector.getName());
        usbConnectorDao.save(usbConnectorToUpdate);
    }

    public void delete(Long id) {
        usbConnectorDao.deleteById(id);
    }
}
