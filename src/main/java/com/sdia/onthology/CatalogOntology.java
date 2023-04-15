package com.sdia.onthology;

import com.sdia.AgentAction.Sell;
import com.sdia.concept.Computer;
import com.sdia.concept.Product;
import com.sdia.concept.Usb;
import com.sdia.predicate.Disponibility;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.schema.*;

public class CatalogOntology extends Ontology implements CatalogVocabulary {
    //Le nom de l'ontologie
    public static final String ONTOLOGY_NAME="Catalog-Ontology";
    public static final Ontology CATALOG_ONTOLOGY=new CatalogOntology(); //Singleton

    public static Ontology getCatalogOntology() {
        return CATALOG_ONTOLOGY;
    }

    private CatalogOntology(){
        super(ONTOLOGY_NAME,BasicOntology.getInstance());
        try {
            add(new ConceptSchema(PRODUCT), Product.class);
            add(new ConceptSchema(COMPUTER), Computer.class);
            add(new ConceptSchema(USB), Usb.class);
            add(new AgentActionSchema(SELL), Sell.class);
            add(new PredicateSchema(DISPONIBLE), Disponibility.class);


            //Definir les attributs de chaque element de la schema
            ConceptSchema cs = (ConceptSchema)getSchema(PRODUCT);
            cs.add(PRODUCT_NAME,(PrimitiveSchema)getSchema(BasicOntology.STRING));
            cs.add(PRODUCT_PRICE,(PrimitiveSchema)getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);

            cs = (ConceptSchema) getSchema(COMPUTER);
            cs.addSuperSchema((ConceptSchema) getSchema(PRODUCT)); //Ajouter la classe mere de classe Computer
            cs.add(COMPUTER_RAM,(PrimitiveSchema)getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            cs.add(COMPUTER_DISK,(PrimitiveSchema)getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            cs.add(COMPUTER_PROCESSORS,(PrimitiveSchema)getSchema(BasicOntology.INTEGER),ObjectSchema.OPTIONAL);

            cs = (ConceptSchema) getSchema(USB);
            cs.addSuperSchema((ConceptSchema) getSchema(PRODUCT));
            cs.add(USB_CAPACITY,(PrimitiveSchema)getSchema(BasicOntology.FLOAT));

            PredicateSchema ds = (PredicateSchema)getSchema(DISPONIBLE);
            ds.add(DISPONIBLE_PRODUCT,getSchema(PRODUCT));
            ds.add(DISPONIBLE_SELLER,getSchema(BasicOntology.AID));

            AgentActionSchema ss = (AgentActionSchema)getSchema(SELL);
            ss.add(SELL_PRODUCT,(ConceptSchema)getSchema(PRODUCT));
            ss.add(SELL_CUSTOMER,(ConceptSchema)getSchema(BasicOntology.AID)); //Ajouter un agent de type AID

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
