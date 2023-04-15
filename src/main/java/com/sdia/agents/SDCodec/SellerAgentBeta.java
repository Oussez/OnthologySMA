package com.sdia.agents.SDCodec;

import com.sdia.concept.Product;
import com.sdia.onthology.CatalogOntology;
import com.sdia.predicate.Disponibility;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.lang.xml.XMLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class SellerAgentBeta extends Agent {
    private Ontology catalogOntology= CatalogOntology.getCatalogOntology();
    private Codec codec=new XMLCodec(); //langage d'ontology

    @Override
    protected void setup() {

        addBehaviour(new Behaviour() {
            int i=1;
            AID sellerAgent = new AID("seller",false);
            @Override
            public void action() {
                getContentManager().registerOntology(catalogOntology);
                getContentManager().registerLanguage(codec);

                //Creation d'objet qui sera envoyé en tant que message
                Disponibility disponibility = new Disponibility();
                disponibility.setSeller(sellerAgent);
                float price =  (float)(10000*Math.random());
                disponibility.setProduct(new Product("PC GAMER v"+i,price));

                //Ajouter l'objet dans le msg
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID("customer",false));
                msg.setOntology(catalogOntology.getName());
                msg.setLanguage(codec.getName());
                try {
                    getContentManager().fillContent(msg,disponibility);
                    send(msg);
                    System.out.println(">> New msg has been sent to customer Agent: "+msg.getContent());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean done() {
                if(i==4)
                        return true;
                i++;
                return false;
            }
            });

    }
}
