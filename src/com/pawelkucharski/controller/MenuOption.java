package com.pawelkucharski.controller;


public enum MenuOption {

    EXIT(0,"Exit program"),
    PRINT_PRODUCTS_LIST(1,"Print list of available products"),
    ADD_PRODUCT(2,"Add product to list"),
    REMOVE_PRODUCT(3,"Remove product from list"),
    UPDATE_PRODUCT_NAME(4,"Update product name"),
    UPDATE_PRODUCT_ID(5,"Update product id"),
    UPDATE_PRODUCT_PRICE(6,"Update product price"),
    UPDATE_PRODUCT_QUANTITY(7,"Update product quantity"),
    ADD_PRODUCT_TO_BASKET(8,"Add product to basket"),
    REMOVE_PRODUCT_FROM_BASKET(9,"Remove product from basket"),
    PRINT_PRODUCTS_IN_BASKET(10,"Print list of products in basket"),
    CHECKOUT(11,"Checkout");


    private int value;
    private String description;

    MenuOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    public static MenuOption createFromInt(int menuOption){
        return MenuOption.values()[menuOption];
    }

}
