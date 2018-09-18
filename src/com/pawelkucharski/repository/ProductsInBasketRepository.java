package com.pawelkucharski.repository;

import com.pawelkucharski.domain.Basket;
import com.pawelkucharski.domain.Product;

import java.util.Map;
import java.util.TreeMap;

public class ProductsInBasketRepository {

    private Map<String,Integer> list;
    private Basket basket;

    public ProductsInBasketRepository(Basket basket) {
        this.list = new TreeMap<>();
        this.basket = basket;
    }

    public boolean add(Product product, int quantity, QuantityRepository quantityRepositorySimple) {
        if (product != null && quantity > 0) {
            String id = product.getId();
            int quantityInBasket = list.getOrDefault(id, 0);
            int resultQuantity = quantityInBasket + quantity;
            int availableQuantity = quantityRepositorySimple.getList().getOrDefault(id, 0);
            if (resultQuantity <= availableQuantity) {
                list.put(id, resultQuantity);
                return true;
            }
        }
        return false;
    }

    public boolean remove(Product product, int quantity){
        if(product != null && quantity > 0){
            String id = product.getId();
            int quantityInBasket = list.getOrDefault(id,0);
            int resultQuantity = quantityInBasket - quantity;
            if(resultQuantity > 0){
                list.put(id,resultQuantity);
                return true;
            }else if(resultQuantity == 0){
                list.remove(id);
                return true;
            }
        }
        return false;
    }

    public void getListOfProducts(Basket basket, ProductRepository productRepository, PriceRepository priceRepository) {
        if (list.size() > 0) {
            String description = "Basket " + basket.getName() + " contains:\n";
            double totalCost = 0.0;
            for(Map.Entry<String,Integer> entry : list.entrySet()){
                String id = entry.getKey();
                int quantity = entry.getValue();
                Product product = productRepository.findById(id);
                double priceOfCurrentProduct = priceRepository.getPrice(id);
                description = description + product.getName() + ", quantity = " + quantity + ", price of product = " + priceOfCurrentProduct + "\n";
                totalCost += priceOfCurrentProduct * quantity;
            }
            System.out.println(description + "Total cost of products in basket = " + totalCost);
        }else{
            System.out.println("No items in the basket");
        }
    }

    private void clearBasket(){
        list.clear();
    }


    public boolean checkOut(ProductRepository productRepository, QuantityRepository quantityRepositorySimple){
        if(list.size() > 0) {
            for (Map.Entry<String, Integer> entry : list.entrySet()) {
                String id = entry.getKey();
                int quantity = entry.getValue();
                Product product = productRepository.findById(id);
                quantityRepositorySimple.updateQuantity(product,-quantity);
            }
            clearBasket();
            return true;
        }
        return false;
    }

}
