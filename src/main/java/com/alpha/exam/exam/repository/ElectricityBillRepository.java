package com.alpha.exam.exam.repository;

import com.alpha.exam.exam.model.ElectricityBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ElectricityBillRepository extends JpaRepository<ElectricityBill, UUID> {

   public List<ElectricityBill> findByCountryOrderByUnitDesc(String country);
   List<ElectricityBill> findByCountryAndBilledAtBetweenOrderByUnitDesc(
            String country, LocalDateTime startDate, LocalDateTime endDate);

}
