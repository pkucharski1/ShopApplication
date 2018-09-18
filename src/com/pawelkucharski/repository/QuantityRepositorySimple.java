package com.pawelkucharski.repository;

import com.pawelkucharski.dataInitilization.DataInitialization;
import com.pawelkucharski.domain.Product;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


public class QuantityRepositorySimple implements QuantityRepository {

    public Map<String,Integer> list;

    public QuantityRepositorySimple() {
        this.list = new LinkedHashMap<>(DataInitialization.quantityList);
    }

    @Override
    public boolean add(Product product, int quantity){
        if(product != null) {
            String id = product.getId();
            if (!list.containsKey(id) && quantity > 0) {
                list.put(id, quantity);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateQuantity(Product product, int quantity){
        if(product != null) {
            String id = product.getId();
            int currentQuantity = list.get(id);
            int newQuantity = currentQuantity + quantity;
            if (list.containsKey(id) && newQuantity >= 0) {
                list.put(id, newQuantity);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateId(Product product, String oldId){
        if(product != null) {
            if (list.containsKey(oldId)) {
                int quantity = list.get(oldId);
                list.remove(oldId);
                list.put(product.getId(), quantity);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Product product){
        if(product != null) {
            String id = product.getId();
            if (list.containsKey(id)) {
                list.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Integer> getList() {
        return Collections.unmodifiableMap(list);
    }
}
