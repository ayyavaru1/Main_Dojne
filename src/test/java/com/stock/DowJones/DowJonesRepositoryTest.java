package com.stock.DowJones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.DowJones.entity.DowJonesPrimaryKey;
import com.stock.DowJones.entity.DowJonesRecord;
import com.stock.DowJones.repository.DowJonesRepository;

@SpringBootTest
public class DowJonesRepositoryTest {

    @Autowired
    private DowJonesRepository repository;

    @Test
    void testFindByStock() {
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");

    	DowJonesPrimaryKey key = null;
		try {
			key = new DowJonesPrimaryKey("AA", formatter.parse("1/02/2011"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DowJonesRecord record = new DowJonesRecord(1,key, 100.00, 120.00, 90.00, 110.00, 1000L, 5.0, 2.0, 950L, 105.00, 115.00, 10.0, 7, 1.5);
        repository.save(record);

        List<DowJonesRecord> records = repository.findById_Stock(key.getStock());
        Assertions.assertEquals(1, records.size());
        Assertions.assertEquals("AA", records.get(0).getCompoundKey().getStock());
    }
}
