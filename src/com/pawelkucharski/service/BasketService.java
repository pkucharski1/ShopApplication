package com.pawelkucharski.service;

import com.pawelkucharski.domain.Basket;
import com.pawelkucharski.domain.Product;
import com.pawelkucharski.repository.*;

public class BasketService {

    private ProductRepository productRepository;
    private PriceRepository priceRepository;
    private QuantityRepository quantityRepositorySimple;
    private ProductsInBasketRepository productsInBasketRepository;
    private Basket basket;

    public BasketService(ProductRepository productRepository, PriceRepository priceRepository, QuantityRepository quantityRepositorySimple, ProductsInBasketRepository productsInBasketRepository, Basket basket) {
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.quantityRepositorySimple = quantityRepositorySimple;
        this.productsInBasketRepository = productsInBasketRepository;
        this.basket = basket;
    }

    public void add(Product product, int quantity){
        if(productsInBasketRepository.add(product,quantity, quantityRepositorySimple)){
            System.out.println("Product successfully added to the basket");
        }else{
            System.out.println("Error while processing");
        }
    }

    public void remove(Product product, int quantity){
        if(productsInBasketRepository.remove(product,quantity)){
            System.out.println("Product successfully removed from the basket");
        }else{
            System.out.println("Error while processing");
        }
    }

    public void getListOfProducts(){
        productsInBasketRepository.getListOfProducts(basket,productRepository,priceRepository);
    }

    public void checkOut(){
        if(productsInBasketRepository.checkOut(productRepository, quantityRepositorySimple)){
            System.out.println("Checkout successful");
        }else{
            System.out.println("No products in the basket");
        }
    }

}
