package com.stock.DowJones.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.el.parser.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stock.DowJones.controller.DowJonesController;
import com.stock.DowJones.entity.DowJonesPrimaryKey;
import com.stock.DowJones.entity.DowJonesRecord;
import com.stock.DowJones.exception.DowJonesException;
import com.stock.DowJones.repository.DowJonesRepository;

@Service
public class DowJonesService {

    private static final Logger logger = LogManager.getLogger(DowJonesController.class);

    @Autowired
    private DowJonesRepository repository;
 
    public void uploadDataset(MultipartFile file) {
    	
        List<DowJonesRecord> records = parseCSV(file);
        int UpdatedRecord = 0;
        int InsertRecord = 0;
        for (DowJonesRecord record : records) {
        	try {
        	
            // Check if record already exists with the same stock and date
            Optional<DowJonesRecord> existingRecord = repository.findById_StockAndId_Date(record.getCompoundKey().getStock(), record.getCompoundKey().getDate());
            if (existingRecord.isPresent()) {
                // If exists, update the record
                DowJonesRecord updatedRecord = existingRecord.get();
                updatedRecord.setOpen(record.getOpen());
                updatedRecord.setHigh(record.getHigh());
                updatedRecord.setLow(record.getLow());
                updatedRecord.setClose(record.getClose());
                updatedRecord.setVolume(record.getVolume());
                updatedRecord.setPERCENT_CHANGE_PRICE(record.getPERCENT_CHANGE_PRICE());
                updatedRecord.setPERCENT_CHANGE_VOLUME_OVER_LAST_WK(record.getPERCENT_CHANGE_VOLUME_OVER_LAST_WK());
                updatedRecord.setPREVIOUS_WEEKS_VOLUME(record.getPREVIOUS_WEEKS_VOLUME());
                updatedRecord.setNEXT_WEEKS_OPEN(record.getNEXT_WEEKS_OPEN());
                updatedRecord.setNEXT_WEEKS_CLOSE(record.getNEXT_WEEKS_CLOSE());
                updatedRecord.setPERCENT_CHANGE_NEXT_WEEKS_PRICE(record.getPERCENT_CHANGE_NEXT_WEEKS_PRICE());
                updatedRecord.setDAYS_TO_NEXT_DIVIDEND(record.getDAYS_TO_NEXT_DIVIDEND());
                updatedRecord.setPERCENT_RETURN_NEXT_DIVIDEND(record.getPERCENT_RETURN_NEXT_DIVIDEND());
                logger.info("Updated record: {}"+updatedRecord);
            	UpdatedRecord = UpdatedRecord+1;
                repository.save(updatedRecord);
            } else {
                // If not exists, save as new record
                logger.info("Inserting new record: {}"+record);
                InsertRecord = InsertRecord+1;
                repository.save(record);
            }
            }catch (Exception e) {
            	logger.error("Unexpected error while processing data: {}", e.getMessage());
			}
        }
        logger.info(" File Name:::"+ file.getOriginalFilename() +":Updated record:count::"+UpdatedRecord);
        logger.info(" File Name:::"+ file.getOriginalFilename() +":InsertRecord record:count::"+InsertRecord);
    }
   
    public List<DowJonesRecord> getByStockTicker(String stockTicker) {
        return repository.findById_Stock(stockTicker);
    }

    public List<DowJonesRecord> getByDate(Date date) {
        return repository.findById_Date(date);
    }

    public List<DowJonesRecord> findAllStocks() {
        return repository.findAll();
    }
    
    public DowJonesRecord addRecord(DowJonesRecord record) {
        return repository.save(record);
    }

    
    public List<DowJonesRecord> parseCSV(MultipartFile file) {
    	try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            String content = IOUtils.toString(reader);
            List<DowJonesRecord> records = new ArrayList<>();
            List<String> failedRecords = new ArrayList<>();

            // Remove dollar signs and commas
            content = content.replaceAll("[$]", "");

            // Now, parse the cleaned content back into a CSV reader
            StringReader cleanedReader = new java.io.StringReader(content);
            BufferedReader bufferedReader = new BufferedReader(cleanedReader);
            Date date = null; 
            	String line;
            	int i =0;
        		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
                while ((line = bufferedReader.readLine()) != null) {
                	
                	if (i ==0) {
                    	i++;
                		continue;
                	}
                	String [] lines = line.split(",");
                	
                	if (lines.length != 16) {
                		logger.error("Number of columns "+lines.length+" are not matching with actual dataset column size 16, Please check the file which you are uploading.");
                	}
                	
                    DowJonesRecord record = new DowJonesRecord();
                	try {
                    record.setQuarter(lines[0]=="" ? null : Integer.parseInt(lines[0]));
                        try{
                            	date = formatter.parse(lines[2]);
                        }catch (Exception e) {
                        	logger.error(e.getStackTrace());
                        	failedRecords.add(line);
                        	continue;
						}
                         
                    DowJonesPrimaryKey dowJonesPrimaryKey = new DowJonesPrimaryKey(lines[1], date);

                    record.setCompoundKey(dowJonesPrimaryKey);
                    record.setOpen(lines[3]=="" ? null : Double.parseDouble(lines[3]));
                    record.setHigh(lines[4]=="" ? null : Double.parseDouble(lines[4]));
                    record.setLow(lines[5]=="" ? null : Double.parseDouble(lines[5]));
                    record.setClose(lines[6]=="" ? null : Double.parseDouble(lines[6]));
                    record.setVolume(lines[7]=="" ? null : Long.parseLong(lines[7]));
                    record.setPERCENT_CHANGE_PRICE(lines[8]=="" ? null : Double.parseDouble(lines[8]));
                    record.setPERCENT_CHANGE_VOLUME_OVER_LAST_WK(lines[9]=="" ? null : Double.parseDouble(lines[9]));
                    record.setPREVIOUS_WEEKS_VOLUME(lines[10]=="" ? null : Long.parseLong(lines[10]));
                    record.setNEXT_WEEKS_OPEN(lines[11]=="" ? null : Double.parseDouble(lines[11]));
                    record.setNEXT_WEEKS_CLOSE(lines[12]=="" ? null : Double.parseDouble(lines[12]));
                    record.setPERCENT_CHANGE_NEXT_WEEKS_PRICE(lines[13]=="" ? null : Double.parseDouble(lines[13]));
                    record.setDAYS_TO_NEXT_DIVIDEND(lines[14]=="" ? null : Integer.parseInt(lines[14]));
                    record.setPERCENT_RETURN_NEXT_DIVIDEND(lines[15]=="" ? null : Double.parseDouble(lines[15]));
                    records.add(record);
                	}catch(NumberFormatException e) {
                   	 logger.error("Error parsing JSON data: {}", line, e);
                    failedRecords.add(line);
                    }catch(Exception e) {
                    	failedRecords.add(line);
                        logger.error("Unexpected error while processing data: {}", line, e);
                        throw new DowJonesException("Error reading CSV file: " + e.getStackTrace());
                    }
                }
                logger.warn("File Name:::"+ file.getOriginalFilename() +":Number of failed records:"+failedRecords.size());
               
            return records;
        }catch (IOException e) {
            throw new DowJonesException("Error reading the file: " + e.getMessage());
        }

    }
}
