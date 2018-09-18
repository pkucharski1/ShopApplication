package com.pawelkucharski.repository;

import com.pawelkucharski.domain.Product;

import java.util.Map;

public class QuantityRepositoryNotification implements QuantityRepository{

    private QuantityRepository quantityRepository;

    public QuantityRepositoryNotification(QuantityRepository quantityRepository) {
        this.quantityRepository = quantityRepository;
    }

    @Override
    public boolean add(Product product, int quantity) {
        boolean result = quantityRepository.add(product, quantity);
        if(result) {
            System.out.println("New product added ");
        }else {
            System.out.println("Failed to add product");
        }
        return result;
    }

    @Override
    public boolean updateQuantity(Product product, int quantity) {
        return quantityRepository.updateQuantity(product,quantity);
    }

    @Override
    public boolean updateId(Product product, String oldId) {
        return quantityRepository.updateId(product,oldId);
    }

    @Override
    public boolean remove(Product product) {
        return quantityRepository.remove(product);
    }

    @Override
    public Map<String, Integer> getList() {
        return quantityRepository.getList();
    }
}
