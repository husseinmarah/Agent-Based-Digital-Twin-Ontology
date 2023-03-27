package warehouse.agent;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.lang.acl.ACLMessage;
import warehouse.ontology.*;
import warehouse.ontology.Robot;
import warehouse.ontology.messages.Start;

import java.awt.*;
import java.awt.geom.Point2D;


public class Sender extends Agent {
    // We handle contents
    private ContentManager manager = getContentManager();
    // This agent speaks the SL language
    private Codec codec = new SLCodec();
    // This agent complies with the People ontology
    private Ontology ontology = WarehouseOntology.getInstance();

    class SenderBehaviour extends TickerBehaviour {
        private boolean finished = false;

        public SenderBehaviour(Agent a, long period) {
            super(a, period);
        }

//        public SenderBehaviour(Agent a) {
//            super(a);
//        }
//
//        public boolean done() {
//            return finished;
//        }

        public void onTick() {
            try {
                // Preparing the message
                System.out.println("[" + getLocalName() + "] Creating inform message");

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                AID receiver = new AID("DigitalAgent_1", false);

                msg.setSender(getAID());
                msg.addReceiver(receiver);
                msg.setLanguage(codec.getName());
                msg.setOntology(ontology.getName());

                // Physical agent information
                PhysicalAgent physicalAgent = new PhysicalAgent();
                physicalAgent.setName("PA1");
                physicalAgent.setAid(myAgent.getAID());

                // Digital agent information
                DigitalAgent digitalAgent = new DigitalAgent();
                digitalAgent.setName("DA1");

                // Physical system info
                Robot robot = new Robot();
                robot.setUwbID("2365");
                robot.setIpAddress("192.168.0.123");
                robot.setName("RobotNo_1");
                robot.setLocation(new Point2D.Float(6578, 5730).toString());

                IsTwin isTwin = new IsTwin();
                isTwin.setDigitalTwin(digitalAgent);
                isTwin.setPhysicalTwin(physicalAgent);
                isTwin.setPhysicalComponent(robot);
                // Twinning information
//                manager.fillContent(msg, isTwin);


                Start start = new Start();
                start.setSender(physicalAgent);
                start.setReceiver(digitalAgent);


//                Stop stop = new Stop();
//                stop.setSender(digitalAgent);
//                stop.setReceiver(physicalAgent);

                Action action = new Action();
                action.setActor(((Sender) myAgent).digitalAgentAID);
                action.setAction(start);
                manager.fillContent(msg, action);

                // Send the message
                System.out.println("[" + getLocalName() + "] Sending the message...");
                send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }

            finished = true;
        }
    }


    AID digitalAgentAID;
    protected void setup() {

        String name = "DigitalAgent_1";
        digitalAgentAID = new AID(name, AID.ISLOCALNAME);
        manager.registerLanguage(codec);
        manager.registerOntology(ontology);

        //Register the SL content language
        manager.registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL);

        //Register the mobility ontology
        manager.registerOntology(JADEManagementOntology.getInstance());

        addBehaviour(new SenderBehaviour(this, 3000));
    }
}
