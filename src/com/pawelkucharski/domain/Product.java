package com.pawelkucharski.domain;

public class Product implements Comparable<Product> {
    private String name;
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return id != null ? id.equals(product.id) : product.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    public Product(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }


    @Override
    public int compareTo(Product o) {
        if(this == o){
            return 0;
        }

        if(o != null){
            return this.name.compareTo(o.getName());
        }

        throw new NullPointerException();
        //return 0;
    }

    @Override
    public String toString() {
        return "Product: " +
                "name = " + name +
                ", id = " + id;
    }
}
