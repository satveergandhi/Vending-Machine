package com.inn.vending.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.vending.model.VendingItem;
import com.inn.vending.repository.VendingItemRepository;

@Service
public class VendingMachine {
    
    @Autowired
    private VendingItemRepository itemRepository;

    public void addItem(VendingItem item) {
    	itemRepository.save(item);
    }

    public List<VendingItem> getAllItems() {
        return itemRepository.findAll();
    }
    
    public VendingItem getItem(int itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public List<VendingItem> getItemsInRow(int row) {
        return itemRepository.findByRowNumber(row);
    }

    public void buyItem(int itemId) {
    	itemRepository.deleteById(itemId);
    }
}
