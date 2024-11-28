package com.stock.DowJones.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.DowJones.entity.DowJonesPrimaryKey;
import com.stock.DowJones.entity.DowJonesRecord;

public interface DowJonesRepository extends JpaRepository<DowJonesRecord, DowJonesPrimaryKey> {
	List<DowJonesRecord> findById_Stock(String stock);
    List<DowJonesRecord> findById_Date(Date date);
    Optional<DowJonesRecord> findById_StockAndId_Date(String stock, Date date);

    }

 