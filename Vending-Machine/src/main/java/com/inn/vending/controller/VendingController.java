package com.inn.vending.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inn.vending.model.VendingItem;
import com.inn.vending.service.VendingMachine;

@Controller
@RequestMapping("/vending")
public class VendingController {

	@Autowired
    private VendingMachine vendingMachine;

    @GetMapping("/items")
    public String getAllItems(Model model) {
        List<VendingItem> items = vendingMachine.getAllItems();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/items/{row}")
    public String getItemsInRow(@PathVariable int row, Model model) {
        List<VendingItem> items = vendingMachine.getItemsInRow(row);
        model.addAttribute("items_in_row", items);
        return "items_in_row";
    }

    @GetMapping("/buy/{itemId}")
    public String showBuyItemForm(@PathVariable int itemId, Model model) {
        VendingItem item = vendingMachine.getItem(itemId);
        if (item != null) {
            model.addAttribute("item", item);
            return "buy_form";
        } else {
            return "redirect:/vending/items";
        }
    }

    @PostMapping("/buy/{itemId}")
    public String buyItem(
            @PathVariable int itemId,
            @RequestParam double amount,
            Model model
    ) {
        VendingItem item = vendingMachine.getItem(itemId);
        if (item != null) {
            if (amount >= item.getPrice()) {
                vendingMachine.buyItem(itemId);
                double change = amount - item.getPrice();
                model.addAttribute("message", "Item purchased: " + item.getName() + ". Change: " + change);
            } else {
                model.addAttribute("message", "Insufficient amount to buy " + item.getName());
            }
        } else {
            model.addAttribute("message", "Item not found.");
        }
        return "result";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute VendingItem item) {
        vendingMachine.addItem(item);
        return "redirect:/vending/items";
    }
}

