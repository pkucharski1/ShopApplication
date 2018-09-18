package com.pawelkucharski.repository;

import com.pawelkucharski.domain.Product;

import java.util.Map;


public interface QuantityRepository {

    boolean add(Product product, int quantity);

    boolean updateQuantity(Product product, int quantity);

    boolean updateId(Product product, String oldId);

    boolean remove(Product product);

    Map<String, Integer> getList();
}
