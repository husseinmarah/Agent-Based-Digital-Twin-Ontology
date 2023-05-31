package warehouse.robotbattery;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;

public class BatteryAgent extends Agent {
    private int batteryLevel = 100;

    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    try {
                        Object content = msg.getContentObject();
                        if (content instanceof MoveRobotMessage) {
                            int distance = ((MoveRobotMessage) content).getDistance();
                            batteryLevel -= distance; // consume battery
                            ACLMessage reply = msg.createReply();
                            reply.setPerformative(ACLMessage.INFORM);
                            reply.setContentObject(new BatteryLevelMessage(batteryLevel));
                            send(reply);
                        }
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }
}
