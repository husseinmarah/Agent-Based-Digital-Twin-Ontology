package warehouse.ontology.behaviour;

import jade.content.lang.sl.SLCodec;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import warehouse.ontology.*;

import java.awt.geom.Point2D;

public class SenderBehaviour extends TickerBehaviour {
    private boolean finished = false;

    Agent agent;
    AID actor;
    AID reasoningAgent;

    public SenderBehaviour(Agent a, long period) {
        super(a, period);
        this.agent=a;
        String name = "DigitalAgent_1";
        actor = new AID(name, AID.ISLOCALNAME);
        reasoningAgent = new AID("ReasoningAgent", false);
    }

    @Override
    protected void onTick() {
            try {
                System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh = " + finished);

                System.out.println("myAgent.getAID().getName() = " + myAgent.getAID().getName());
                // Preparing the message
                System.out.println("[" + agent.getLocalName() + "] Creating inform message");

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                AID receiver = reasoningAgent;

                msg.setSender(agent.getAID());
                msg.addReceiver(receiver);
                msg.setLanguage(new SLCodec().getName());
                msg.setOntology(WarehouseOntology.getInstance().getName());

                // Physical agent information
                PhysicalAgent physicalAgent = new PhysicalAgent();
                physicalAgent.setName("PA1");
                physicalAgent.setAid(myAgent.getAID());

                // Digital agent information
                DigitalAgent digitalAgent = new DigitalAgent();
                digitalAgent.setName("DA1");

                // Physical system info
                MobileRobot robot = new MobileRobot();
                robot.setUwbID("2365");
                robot.setIpAddress("192.168.0.123");
                robot.setName("RobotNo_1");

                robot.setLocation(new Point2D.Float(6578, 5730).toString());
                robot.setBatteryLevel(10.5);

                IsTwin isTwin = new IsTwin();
                isTwin.setDigitalTwin(digitalAgent);
                isTwin.setPhysicalTwin(physicalAgent);
                isTwin.setPhysicalComponent(robot);
                // Twinning information
                agent.getContentManager().fillContent(msg, isTwin);

//                Start start = new Start();
//                start.setSender(digitalAgent);
//                start.setReceiver(physicalAgent);

//                Stop stop = new Stop();
//                stop.setSender(digitalAgent);
//                stop.setReceiver(physicalAgent);

//                Action action = new Action();
//                action.setActor(((Sender) myAgent).actor);
//                action.setAction(start);
//                manager.fillContent(msg, action);

                // Send the message
                System.out.println("[" + agent.getLocalName() + "] Sending the message...");
                agent.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }

            finished = true;

    }
}
