package com.pawelkucharski.repository;


import com.pawelkucharski.dataInitilization.DataInitialization;
import com.pawelkucharski.domain.Product;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ProductRepository {
    private List<Product> list;

    public ProductRepository() {
        this.list = new LinkedList<>(DataInitialization.productList);
    }

    public boolean add(Product product){
        if(!list.contains(product)){
            list.add(product);
            return true;
        }
        return false;
    }

    public boolean remove(Product product){
        if(list.contains(product)){
            list.remove(product);
            return true;
        }
        return false;
    }

    public boolean updateName(Product product, String name){
        if(list.contains(product)){
            product.setName(name);
            int index = list.indexOf(product);
            list.set(index,product);
            return true;
        }
        return false;
    }

    public boolean updateId(Product product, String newId){
        if(list.contains(product)){
            product.setId(newId);
            int index = list.indexOf(product);
            list.set(index,product);
            return true;
        }
        return false;
    }

    public Product findById(String id){
        Product foundProduct;
        for(Product product: list){
            if(product.getId().equals(id)){
                foundProduct = product;
                return foundProduct;
            }
        }
        return null;
    }

    public Product findByName(String name){
        Product foundProduct;
        for(Product product: list){
            if(product.getName().equals(name)){
                foundProduct = product;
                return foundProduct;
            }
        }
        return null;
    }

    public void getListOfProducts(PriceRepository priceRepository, QuantityRepository quantityRepositorySimple) {
        String listOfItems = "\n List of items: \n";
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Product currentProduct = list.get(i);
            listOfItems += currentProduct + ", price = " + priceRepository.getList().get(currentProduct.getId()) + ", available quantity = " + quantityRepositorySimple.getList().get(currentProduct.getId()) + "\n";
        }
        System.out.println(listOfItems);
    }


    public List<Product> getList() {
        return Collections.unmodifiableList(list);
    }
}
