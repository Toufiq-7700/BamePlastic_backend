package com.BamePlastic.backend.repository;

import com.BamePlastic.backend.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findByBusId(Long busId);

    List<Passenger> findByBusIdAndHasPaidFalse(Long busId);

    List<Passenger> findByBusIdAndIsDodgerTrue(Long busId);

    long countByBusId(Long busId);
}
