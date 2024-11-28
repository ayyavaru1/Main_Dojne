package com.stock.DowJones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.stock.DowJones.entity.DowJonesPrimaryKey;
import com.stock.DowJones.entity.DowJonesRecord;
import com.stock.DowJones.repository.DowJonesRepository;
import com.stock.DowJones.service.DowJonesService;

@SpringBootTest
public class DowJonesServiceTest {

    @Mock
    private DowJonesRepository repository;

    @InjectMocks
    private DowJonesService service;

    @Mock
    private MultipartFile mockFile;
    
    @Test
    void testGetByStock() {
        String stock = "AA";
       
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");

        List<DowJonesRecord> mockRecords = null;
		try {
			mockRecords = List.of(new DowJonesRecord(1,new DowJonesPrimaryKey("AA", formatter.parse("1/02/2011")), 100.00, 120.00, 90.00, 110.00, 1000L, 5.00, 5.00, 5000L, 120.00, 125.00, 5.00, 10, 5.00));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Mockito.when(repository.findById_Stock(stock)).thenReturn(mockRecords);

        List<DowJonesRecord> records = service.getByStockTicker(stock);

        assertEquals(1, records.size());
        assertEquals("AA", records.get(0).getCompoundKey().getStock());
    }

    @Test
    void testAddRecord() {
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");

        DowJonesRecord record = null;
		try {
			record = new DowJonesRecord(1,new DowJonesPrimaryKey("AA", formatter.parse("1/02/2011")), 100.00, 120.00, 90.00, 110.00, 1000L, 5.00, 5.00, 5000L, 120.00, 125.00, 5.0, 10, 5.0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Mockito.when(repository.save(record)).thenReturn(record);

        DowJonesRecord savedRecord = service.addRecord(record);

        assertEquals("AA", savedRecord.getCompoundKey().getStock());
    }
}
