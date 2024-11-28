package com.stock.DowJones.controller;

import java.io.IOException;
import java.util.List;

import org.apache.el.parser.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.stock.DowJones.entity.DowJonesRecord;
import com.stock.DowJones.exception.DowJonesException;
import com.stock.DowJones.service.DowJonesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/dow-jones")
public class DowJonesController {

    private static final Logger logger = LogManager.getLogger(DowJonesController.class);

    @Autowired
    private DowJonesService service;
       
    @PostMapping(value ="/upload-multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files) throws IOException {
            for (MultipartFile file : files) {
                if (file.getSize() > 2) {
                    service.uploadDataset(file);  // Pass file to service
                }else {
                	logger.error(" Uploading file is empty , Please cross check with your file :"+file.getOriginalFilename());
                }
            }
            logger.info("Uploading dataset...");
            return ResponseEntity.ok("Files uploaded successfully");
    }
  
    
    @GetMapping("/query/{stock}")
    public ResponseEntity<List<DowJonesRecord>> getByStock(@PathVariable String stock) {
        List<DowJonesRecord> records = service.getByStockTicker(stock);
        if (records.isEmpty()) {
            throw new DowJonesException("No records found for stock: " + stock);
        }
        return ResponseEntity.ok(records);
    }
      

    @GetMapping("/query")
    public ResponseEntity<List<DowJonesRecord>> findAllStocks() {
        List<DowJonesRecord> records = service.findAllStocks();
        if (records.isEmpty()) {
            throw new DowJonesException("No stocks available " );
        }
        return ResponseEntity.ok(records);
    }

    @PostMapping("/add")
    public ResponseEntity<DowJonesRecord> addRecord(@RequestBody @Valid DowJonesRecord record) {
        DowJonesRecord savedRecord = service.addRecord(record);
        return ResponseEntity.ok(savedRecord);
    }
}
