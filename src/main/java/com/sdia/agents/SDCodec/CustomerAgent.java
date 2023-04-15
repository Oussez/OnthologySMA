package com.sdia.agents.SDCodec;

import com.sdia.onthology.CatalogOntology;
import com.sdia.predicate.Disponibility;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.mtp.MTPException;

public class CustomerAgent extends Agent {
    private Ontology catalogOntology= CatalogOntology.getCatalogOntology();
    private Codec codec=new SLCodec(); //langage d'ontology

    public CustomerAgent() throws MTPException {
    }

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                getContentManager().registerOntology(catalogOntology);
                getContentManager().registerLanguage(codec);
                //Pour verifier les messages reçus avant leur lecture : on utilise MessageTemplate pour verifier la structure de message reçu avant de le lire.
                MessageTemplate messageTemplate=MessageTemplate.and
                        (MessageTemplate.MatchOntology(CatalogOntology.ONTOLOGY_NAME),
                                MessageTemplate.MatchLanguage(codec.getName())
                        );

                ACLMessage receivedMessage = blockingReceive(messageTemplate); //retourner le message qui satisfait la structure dessus
                try {
                    System.out.println("\n>> i'm customer Agent  ");
                    Disponibility disponible = (Disponibility)getContentManager().extractContent(receivedMessage);
                    System.out.println("Product - Info >>    Name : "+disponible.getProduct().getName()+" |\tPrice : "+disponible.getProduct().getPrice());
                } catch (Codec.CodecException e) {
                    e.printStackTrace();
                } catch (OntologyException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
