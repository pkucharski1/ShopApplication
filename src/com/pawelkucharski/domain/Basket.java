package com.pawelkucharski.domain;

import com.pawelkucharski.domain.Product;
import com.pawelkucharski.repository.ProductRepository;
import com.pawelkucharski.service.BasketService;

import java.util.Map;
import java.util.TreeMap;


public class Basket {

    private String name;

    public Basket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;

    }
}
