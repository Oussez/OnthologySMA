package com.sdia.AgentAction;

import com.sdia.concept.Product;
import jade.content.AgentAction;
import jade.core.AID;

public class Sell implements AgentAction {
    private AID customerAgent;
    private Product product;

    public AID getCustomerAgent() {
        return customerAgent;
    }

    public void setCustomerAgent(AID customerAgent) {
        this.customerAgent = customerAgent;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
