package warehouse.agent;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.lang.acl.ACLMessage;
import warehouse.ontology.*;
import warehouse.ontology.behaviour.SenderBehaviour;

import java.awt.geom.Point2D;


public class SenderA extends Agent {

    AID actor;
    AID reasoningAgent;
    protected void setup() {

        String name = "DigitalAgent_1";
        actor = new AID(name, AID.ISLOCALNAME);
        reasoningAgent = new AID("ReasoningAgent", false);
        this.getContentManager().registerLanguage(new SLCodec());
        this.getContentManager().registerOntology(WarehouseOntology.getInstance());

        //Register the SL content language
        this.getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL);

        //Register the mobility ontology
        this.getContentManager().registerOntology(JADEManagementOntology.getInstance());

        addBehaviour(new SenderBehaviour(this, 3000));
    }
}
