package com.pawelkucharski.controller;

import com.pawelkucharski.dataRetriever.DataRetriever;
import com.pawelkucharski.dataRetriever.ProductInfo;
import com.pawelkucharski.domain.Basket;
import com.pawelkucharski.domain.Product;
import com.pawelkucharski.repository.*;
import com.pawelkucharski.service.BasketService;
import com.pawelkucharski.service.ProductService;

public class MainController {

    private ProductRepository productRepository;
    private PriceRepository priceRepository;
    private QuantityRepository quantityRepository;
    private DataRetriever dataRetriever;
    private ProductService productService;
    private Basket pawelsBasket;
    private BasketService basketService;
    private ProductsInBasketRepository productsInBasketRepository;

    public MainController() {
        this.productRepository = new ProductRepository();
        this.priceRepository = new PriceRepository();
        this.quantityRepository = new QuantityRepositorySimple();
        this.productsInBasketRepository = new ProductsInBasketRepository(pawelsBasket);
        this.dataRetriever = new DataRetriever(productRepository);
        this.pawelsBasket = new Basket("Paul");
        this.productService = new ProductService(productRepository, priceRepository, quantityRepository);
        this.basketService = new BasketService(productRepository, priceRepository, quantityRepository, productsInBasketRepository, pawelsBasket);

    }

    public void run() {
        boolean quit = false;

        while (!quit) {
            printMenu();
            int action = dataRetriever.retrieveAction();
            MenuOption menuOption = MenuOption.createFromInt(action);
            switch (menuOption) {
                case EXIT:
                    quit = true;
                    break;
                case PRINT_PRODUCTS_LIST:
                    printListOfProducts();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case REMOVE_PRODUCT:
                    removeProduct();
                    break;
                case UPDATE_PRODUCT_NAME:
                    updateProductName();
                    break;
                case UPDATE_PRODUCT_ID:
                    updateProductId();
                    break;
                case UPDATE_PRODUCT_PRICE:
                    updatePrice();
                    break;
                case UPDATE_PRODUCT_QUANTITY:
                    updateQuantity();
                    break;
                case ADD_PRODUCT_TO_BASKET:
                    addToBasket();
                    break;
                case REMOVE_PRODUCT_FROM_BASKET:
                    removeFromBasket();
                    break;
                case PRINT_PRODUCTS_IN_BASKET:
                    printListOfProductsInBasket();
                    break;
                case CHECKOUT:
                    checkOut();
            }
        }
    }

    private void addProduct() {
        ProductInfo productInfo = dataRetriever.retrieveProductInfo();
        productService.add(productInfo);
    }

    public void removeProduct() {
        Product product = dataRetriever.findProductById();
        productService.remove(product);
    }

    public void updateProductName() {
        Product product = dataRetriever.findProductById();
        String name = dataRetriever.retrieveNewProductName();
        productService.editName(product, name);
    }

    public void updateProductId() {
        Product product = dataRetriever.findProductById();
        String id = dataRetriever.retrieveNewProductId();
        productService.editId(product, id);
    }


    public void updatePrice() {
        Product product = dataRetriever.findProductById();
        double price = dataRetriever.retrieveNewProductPrice();
        productService.updatePrice(product, price);
    }

    public void updateQuantity() {
        Product product = dataRetriever.findProductById();
        int quantity = dataRetriever.retrieveProductQuantity();
        productService.updateQuantity(product, quantity);
    }


    public void printListOfProducts() {
        productService.getListOfProducts();
    }

    public void addToBasket() {
        Product product = dataRetriever.findProductByName();
        int quantity = dataRetriever.retrieveProductQuantity();
        basketService.add(product, quantity);
    }

    public void removeFromBasket() {
        Product product = dataRetriever.findProductByName();
        int quantity = dataRetriever.retrieveProductQuantity();
        basketService.remove(product, quantity);
    }

    public void printListOfProductsInBasket() {
        basketService.getListOfProducts();
    }

    public void checkOut() {
        basketService.checkOut();
    }

    private void printMenu() {
        System.out.println("\nAvailable options");
        for (MenuOption o : MenuOption.values()) {
            System.out.println(o);
        }
    }

    private void turnOnNotifications() {
        quantityRepository = new QuantityRepositoryNotification(quantityRepository);
        productService = new ProductService(productRepository, priceRepository, quantityRepository);
        basketService = new BasketService(productRepository, priceRepository, quantityRepository, productsInBasketRepository, pawelsBasket);
    }
}
