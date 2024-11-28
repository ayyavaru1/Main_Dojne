package com.stock.DowJones.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class DowJonesRecord {
	 public DowJonesRecord() {
	 }
    public DowJonesPrimaryKey getCompoundKey() {
		return id;
	}
	public DowJonesRecord(Integer quarter, DowJonesPrimaryKey compoundKey, Double open, Double high,
			Double low, Double close, Long volume, Double pERCENT_CHANGE_PRICE,
			Double pERCENT_CHANGE_VOLUME_OVER_LAST_WK, Long pREVIOUS_WEEKS_VOLUME, Double nEXT_WEEKS_OPEN,
			Double nEXT_WEEKS_CLOSE, Double pERCENT_CHANGE_NEXT_WEEKS_PRICE, Integer dAYS_TO_NEXT_DIVIDEND,
			Double pERCENT_RETURN_NEXT_DIVIDEND) {
		super();
		this.quarter = quarter;
		this.id = compoundKey;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		PERCENT_CHANGE_PRICE = pERCENT_CHANGE_PRICE;
		PERCENT_CHANGE_VOLUME_OVER_LAST_WK = pERCENT_CHANGE_VOLUME_OVER_LAST_WK;
		PREVIOUS_WEEKS_VOLUME = pREVIOUS_WEEKS_VOLUME;
		NEXT_WEEKS_OPEN = nEXT_WEEKS_OPEN;
		NEXT_WEEKS_CLOSE = nEXT_WEEKS_CLOSE;
		PERCENT_CHANGE_NEXT_WEEKS_PRICE = pERCENT_CHANGE_NEXT_WEEKS_PRICE;
		DAYS_TO_NEXT_DIVIDEND = dAYS_TO_NEXT_DIVIDEND;
		PERCENT_RETURN_NEXT_DIVIDEND = pERCENT_RETURN_NEXT_DIVIDEND;
	}
	public void setCompoundKey(DowJonesPrimaryKey compoundKey) {
		this.id = compoundKey;
	}
	
    
    private Integer quarter;

    @EmbeddedId
    private DowJonesPrimaryKey id; 
    
    @NotNull
    private Double open;
    private Double high;
    @Override
	public String toString() {
		return "DowJonesRecord [quarter=" + quarter + ", id=" + id + ", open=" + open + ", high=" + high + ", low="
				+ low + ", close=" + close + ", volume=" + volume + ", PERCENT_CHANGE_PRICE=" + PERCENT_CHANGE_PRICE
				+ ", PERCENT_CHANGE_VOLUME_OVER_LAST_WK=" + PERCENT_CHANGE_VOLUME_OVER_LAST_WK
				+ ", PREVIOUS_WEEKS_VOLUME=" + PREVIOUS_WEEKS_VOLUME + ", NEXT_WEEKS_OPEN=" + NEXT_WEEKS_OPEN
				+ ", NEXT_WEEKS_CLOSE=" + NEXT_WEEKS_CLOSE + ", PERCENT_CHANGE_NEXT_WEEKS_PRICE="
				+ PERCENT_CHANGE_NEXT_WEEKS_PRICE + ", DAYS_TO_NEXT_DIVIDEND=" + DAYS_TO_NEXT_DIVIDEND
				+ ", PERCENT_RETURN_NEXT_DIVIDEND=" + PERCENT_RETURN_NEXT_DIVIDEND + "]";
	}


	private Double low;
    private Double close;
    
    private Long volume;
    private Double PERCENT_CHANGE_PRICE;
    private Double PERCENT_CHANGE_VOLUME_OVER_LAST_WK;
    private Long PREVIOUS_WEEKS_VOLUME;
    
    private Double NEXT_WEEKS_OPEN;
    private Double NEXT_WEEKS_CLOSE;
    private Double PERCENT_CHANGE_NEXT_WEEKS_PRICE;
    private Integer DAYS_TO_NEXT_DIVIDEND;
    private Double PERCENT_RETURN_NEXT_DIVIDEND;

	// Getters and Setters
   
	public Integer getQuarter() {
		return quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	
	public Double getPERCENT_CHANGE_PRICE() {
		return PERCENT_CHANGE_PRICE;
	}
	public void setPERCENT_CHANGE_PRICE(Double pERCENT_CHANGE_PRICE) {
		PERCENT_CHANGE_PRICE = pERCENT_CHANGE_PRICE;
	}
	public Double getPERCENT_CHANGE_VOLUME_OVER_LAST_WK() {
		return PERCENT_CHANGE_VOLUME_OVER_LAST_WK;
	}
	public void setPERCENT_CHANGE_VOLUME_OVER_LAST_WK(Double pERCENT_CHANGE_VOLUME_OVER_LAST_WK) {
		PERCENT_CHANGE_VOLUME_OVER_LAST_WK = pERCENT_CHANGE_VOLUME_OVER_LAST_WK;
	}
	public Long getPREVIOUS_WEEKS_VOLUME() {
		return PREVIOUS_WEEKS_VOLUME;
	}
	public void setPREVIOUS_WEEKS_VOLUME(Long pREVIOUS_WEEKS_VOLUME) {
		PREVIOUS_WEEKS_VOLUME = pREVIOUS_WEEKS_VOLUME;
	}
	public Double getNEXT_WEEKS_OPEN() {
		return NEXT_WEEKS_OPEN;
	}
	public void setNEXT_WEEKS_OPEN(Double nEXT_WEEKS_OPEN) {
		NEXT_WEEKS_OPEN = nEXT_WEEKS_OPEN;
	}
	public Double getNEXT_WEEKS_CLOSE() {
		return NEXT_WEEKS_CLOSE;
	}
	public void setNEXT_WEEKS_CLOSE(Double nEXT_WEEKS_CLOSE) {
		NEXT_WEEKS_CLOSE = nEXT_WEEKS_CLOSE;
	}
	public Double getPERCENT_CHANGE_NEXT_WEEKS_PRICE() {
		return PERCENT_CHANGE_NEXT_WEEKS_PRICE;
	}
	public void setPERCENT_CHANGE_NEXT_WEEKS_PRICE(Double pERCENT_CHANGE_NEXT_WEEKS_PRICE) {
		PERCENT_CHANGE_NEXT_WEEKS_PRICE = pERCENT_CHANGE_NEXT_WEEKS_PRICE;
	}
	public Integer getDAYS_TO_NEXT_DIVIDEND() {
		return DAYS_TO_NEXT_DIVIDEND;
	}
	public void setDAYS_TO_NEXT_DIVIDEND(Integer dAYS_TO_NEXT_DIVIDEND) {
		DAYS_TO_NEXT_DIVIDEND = dAYS_TO_NEXT_DIVIDEND;
	}
	public Double getPERCENT_RETURN_NEXT_DIVIDEND() {
		return PERCENT_RETURN_NEXT_DIVIDEND;
	}
	public void setPERCENT_RETURN_NEXT_DIVIDEND(Double pERCENT_RETURN_NEXT_DIVIDEND) {
		PERCENT_RETURN_NEXT_DIVIDEND = pERCENT_RETURN_NEXT_DIVIDEND;
	}
	
	
}
