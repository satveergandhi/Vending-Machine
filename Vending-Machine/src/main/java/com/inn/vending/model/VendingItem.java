package com.inn.vending.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class VendingItem {

	 	private int id;
	 	
	    private String name;
	    
	    private double price;
	    
	    @ManyToOne
	    @JoinColumn
	    private VendingRow row;
}
