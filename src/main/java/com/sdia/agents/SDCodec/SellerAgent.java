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
import jade.lang.acl.ACLMessage;

public class SellerAgent extends Agent {
    private Ontology catalogOntology= CatalogOntology.getCatalogOntology();
    private Codec codec=new SLCodec(); //langage d'ontology

    @Override
    protected void setup() {

        getContentManager().registerOntology(catalogOntology);
        getContentManager().registerLanguage(codec);

        //Creation d'objet qui sera envoyé en tant que message
        Disponibility disponibility = new Disponibility();
        disponibility.setSeller(new AID("seller",false));
        disponibility.setProduct(new Product("PC GAMER 22",15000));
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
}
