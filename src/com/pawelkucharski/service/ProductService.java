package com.pawelkucharski.service;


import com.pawelkucharski.dataRetriever.ProductInfo;
import com.pawelkucharski.domain.Product;
import com.pawelkucharski.repository.PriceRepository;
import com.pawelkucharski.repository.ProductRepository;
import com.pawelkucharski.repository.QuantityRepository;
import com.pawelkucharski.repository.QuantityRepositorySimple;

/**
 * Created by Paweł Kucharski on 17.05.2018.
 */

public class ProductService {

    private ProductRepository productRepository;
    private PriceRepository priceRepository;
    private QuantityRepository quantityRepositorySimple;

    public ProductService(ProductRepository productRepository, PriceRepository priceRepository, QuantityRepository quantityRepositorySimple) {
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.quantityRepositorySimple = quantityRepositorySimple;
    }

    public void add(ProductInfo productInfo){
        Product product = new Product(productInfo.getName(),productInfo.getId());
        if (productRepository.add(product)) {
            priceRepository.add(product, productInfo.getPrice());
            quantityRepositorySimple.add(product, productInfo.getQuantity());
            System.out.println("Item has been successfully added to the list of products");
        } else {
            System.out.println("Error while processing - item was not added to the list of products");
        }
    }

    public void remove(Product product) {
        if (productRepository.remove(product)) {
            priceRepository.remove(product);
            quantityRepositorySimple.remove(product);
            System.out.println("Item has been successfully removed from the list of products");
        } else {
            System.out.println("Error while processing - item not found");
        }
    }

    public void editName(Product product, String name){
        if(productRepository.updateName(product,name)){
            System.out.println("Item`s name has been successfully edited");
        }else{
            System.out.println("Error while processing - item not found");
        }
    }

    public void editId(Product product, String id){
        String oldId = product.getId();
        if(productRepository.updateId(product,id)){
            priceRepository.updateId(product,oldId);
            quantityRepositorySimple.updateId(product,oldId);
            System.out.println("Product`s id has been successfully updated");
        }else{
            System.out.println("Error while processing - item not found");
        }
    }

    public void updatePrice(Product product, double price){
        if(priceRepository.updatePrice(product,price)){
            System.out.println("Item`s price has been successfully updated");
        }else{
            System.out.println("Error while processing - item not found");
        }
    }

    public void updateQuantity(Product product, int quantity){
        if(quantityRepositorySimple.updateQuantity(product,quantity)){
            System.out.println("Item`s price has been successfully updated");
        }else{
            System.out.println("Error while processing - item not found");
        }
    }

    public void updateQuantityCheckout(Product product, int quantity){
        quantityRepositorySimple.updateQuantity(product,quantity);
    }

    public void getListOfProducts(){
        productRepository.getListOfProducts(priceRepository, quantityRepositorySimple);
    }
}
