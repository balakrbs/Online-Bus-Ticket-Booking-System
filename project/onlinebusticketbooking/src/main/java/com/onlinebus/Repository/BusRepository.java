package com.onlinebus.Repository;

import com.onlinebus.Model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends MongoRepository<Bus, String> {
    Optional<Bus> findByBusnumber(String busnumber);
    void deleteByBusnumber(String busnumber);
    
    List<Bus> findByArrivalAndDestination(String arrival, String destination);
}
