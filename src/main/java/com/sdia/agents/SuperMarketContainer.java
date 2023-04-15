package com.sdia.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class SuperMarketContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=instance.createAgentContainer(profile);

        AgentController agentSeller=agentContainer.createNewAgent("seller","com.sdia.agents.RDFCodec.SellerAgent",new Object[]{});
        AgentController agentCustomer=agentContainer.createNewAgent("customer","com.sdia.agents.RDFCodec.CustomerAgent",new Object[]{});

        agentCustomer.start();
        agentSeller.start();

    }
}
