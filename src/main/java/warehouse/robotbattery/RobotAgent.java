package warehouse.robotbattery;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;

public class RobotAgent extends Agent {
    private int distanceToMove = 50;

    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 3000) {
            public void onTick() {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID("BatteryAgent", AID.ISLOCALNAME));
                msg.setLanguage("Java");
                msg.setOntology("BatteryOntology");
                try {
                    msg.setContentObject(new MoveRobotMessage(distanceToMove));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                send(msg);

                ACLMessage reply = blockingReceive();
                if (reply != null) {
                    try {
                        Object content = reply.getContentObject();
                        if (content instanceof BatteryLevelMessage) {
                            int batteryLevel = ((BatteryLevelMessage) content).getBatteryLevel();
                            System.out.println("Battery level: " + batteryLevel);
                        }
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }
}
