package com.pawelkucharski.dataInitilization;

import com.pawelkucharski.domain.Product;

import java.util.*;

public class DataInitialization {

    public static final List<Product> productList;
    public static final Map<String,Double> priceList;
    public static final Map<String,Integer> quantityList;

    static{
        List<Product> initialProductList = new ArrayList<>();
        Map<String,Double> initialPriceList = new LinkedHashMap<>();
        Map<String,Integer> initialQuantityList = new LinkedHashMap<>();

        Product tempProduct = new Product("Bread","1A");
        initialProductList.add(tempProduct);
        tempProduct = new Product("Cake","1B");
        initialProductList.add(tempProduct);
        tempProduct = new Product("Butter","1C");
        initialProductList.add(tempProduct);
        tempProduct = new Product("Flour","1D");
        initialProductList.add(tempProduct);
        tempProduct = new Product("Chocolate","1E");
        initialProductList.add(tempProduct);
        tempProduct = new Product("Water","1F");
        initialProductList.add(tempProduct);

        initialPriceList.put(initialProductList.get(0).getId(),2.23);
        initialPriceList.put(initialProductList.get(1).getId(),3.74);
        initialPriceList.put(initialProductList.get(2).getId(),1.28);
        initialPriceList.put(initialProductList.get(3).getId(),5.25);
        initialPriceList.put(initialProductList.get(4).getId(),7.55);
        initialPriceList.put(initialProductList.get(5).getId(),1.45);

        initialQuantityList.put(initialProductList.get(0).getId(),8);
        initialQuantityList.put(initialProductList.get(1).getId(),12);
        initialQuantityList.put(initialProductList.get(2).getId(),20);
        initialQuantityList.put(initialProductList.get(3).getId(),35);
        initialQuantityList.put(initialProductList.get(4).getId(),4);
        initialQuantityList.put(initialProductList.get(5).getId(),7);
        
        productList = Collections.unmodifiableList(initialProductList);
        priceList = Collections.unmodifiableMap(initialPriceList);
        quantityList = Collections.unmodifiableMap(initialQuantityList);
        
    }

}
