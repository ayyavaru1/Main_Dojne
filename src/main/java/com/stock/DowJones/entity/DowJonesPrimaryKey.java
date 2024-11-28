package com.stock.DowJones.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Embeddable;

@Embeddable
public class DowJonesPrimaryKey implements Serializable {
    
	
    private String stock;
    private Date date;

    @Override
	public String toString() {
		return "DowJonesPrimaryKey [stock=" + stock + ", date=" + date + "]";
	}

	// Default constructor
    public DowJonesPrimaryKey() {}

    public DowJonesPrimaryKey(String stock, Date date) {
        this.stock = stock;
        this.date = date;
    }

    // Getters and Setters
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        DowJonesPrimaryKey that = (DowJonesPrimaryKey) o;
        return stock.equals(that.stock) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return 31 * stock.hashCode() + date.hashCode();
    }
}