package com.inn.vending.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inn.vending.model.VendingItem;

public interface VendingItemRepository extends JpaRepository<VendingItem, Integer> {
	List<VendingItem> findByRow(int row);
}

