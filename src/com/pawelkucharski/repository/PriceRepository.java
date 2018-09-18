package com.pawelkucharski.repository;

import com.pawelkucharski.dataInitilization.DataInitialization;
import com.pawelkucharski.domain.Product;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PriceRepository {

    private Map<String, Double> list;

    public PriceRepository() {
        this.list = new LinkedHashMap<>(DataInitialization.priceList);
    }

    public boolean add(Product product, double price){
        if(product != null) {
            String id = product.getId();
            if (!list.containsKey(id)) {
                list.put(id, price);
                return true;
            }
        }
        return false;
    }

    public boolean updatePrice(Product product, double price){
        if(product != null) {
            String id = product.getId();
            if (list.containsKey(id)) {
                list.put(id, price);
                return true;
            }
        }
        return false;
    }

    public boolean updateId(Product product, String oldId) {
        if (product != null) {
            if (list.containsKey(oldId)) {
                double price = list.get(oldId);
                list.remove(oldId);
                list.put(product.getId(), price);
                return true;
            }
        }
        return false;
    }

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

    public double getPrice(String id){
        if(list.containsKey(id)){
            return list.get(id);
        }
        return 0;
    }

    public Map<String, Double> getList() {
        return Collections.unmodifiableMap(list);
    }
}
