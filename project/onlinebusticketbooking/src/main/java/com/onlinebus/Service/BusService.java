package com.onlinebus.Service;

import com.onlinebus.Model.Bus;
import com.onlinebus.Repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    private final BusRepository busRepository;


    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Optional<Bus> getBusByBusNumber(String busNumber) {
        return busRepository.findByBusnumber(busNumber);
    }

    public void deleteBusByBusNumber(String busNumber) {
        busRepository.deleteByBusnumber(busNumber);
    }

    public void saveOrUpdateBus(Bus bus) {
        busRepository.save(bus);
    }
    
    public List<Bus> findByArrivalAndDestination(String arrival, String destination) {
        return busRepository.findByArrivalAndDestination(arrival, destination);
    }
}
