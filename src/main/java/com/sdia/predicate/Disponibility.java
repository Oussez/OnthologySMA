package com.sdia.predicate;

import com.sdia.concept.Product;
import jade.core.AID;

import jade.content.Predicate;

public class Disponibility implements Predicate {
    public AID seller;

    public Product product;

    public AID getSeller() {
        return seller;
    }

    public void setSeller(AID seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
