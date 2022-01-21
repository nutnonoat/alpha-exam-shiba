package com.alpha.exam.exam.repository;

import com.alpha.exam.exam.model.CostPerUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CostPerUnitRepository extends JpaRepository<CostPerUnit, UUID> {

    public CostPerUnit findByCountry(String name);
}
