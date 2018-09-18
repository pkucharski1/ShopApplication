package com.pawelkucharski.dataRetriever;

import com.pawelkucharski.domain.Product;
import com.pawelkucharski.repository.ProductRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataRetriever {

    private ProductRepository productRepository;

    public DataRetriever(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductInfo retrieveProductInfo(){
        String id = getDataFromUser("Enter product id: ");
        String name = getDataFromUser("Enter product name: ");
        double price = getDouble("Enter product price: ");
        int quantity = getInt("Enter product quantity: ");
        ProductInfo product = new ProductInfo(id,name,price,quantity);
        return product;
    }

    public String retrieveNewProductName(){
        return getDataFromUser("Enter new product name: ");
    }

    public String retrieveNewProductId(){
        return getDataFromUser("Enter new product id: ");
    }

    public double retrieveNewProductPrice(){
        return getDouble("Enter new product price");
    }

    public int retrieveProductQuantity(){
        return getInt("Enter quantity of product");
    }

    public String retrieveExistingProductId(){
        return getDataFromUser("Enter product id");
    }

    public String retrieveExistingProductName(){
        return getDataFromUser("Enter product name");
    }


    public Product findProductById(){
        String id = retrieveExistingProductId();
        Product product = productRepository.findById(id);
        return product;
    }

    public Product findProductByName(){
        String name = retrieveExistingProductName();
        Product product = productRepository.findByName(name);
        return product;
    }

    public int retrieveAction(){
        int action = getInt("Enter action: ");
        return action;
    }

    private String getDataFromUser(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    private int getInt(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                return scanner.nextInt();
            } catch(InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Enter correct number");
            }
        }
    }

    private double getDouble(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                return scanner.nextDouble();
            } catch(InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Enter correct number");
            }
        }
    }
}
