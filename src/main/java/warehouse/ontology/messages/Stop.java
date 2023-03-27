package warehouse.ontology.messages;

import jade.content.Concept;
import warehouse.ontology.DigitalAgent;
import warehouse.ontology.PhysicalAgent;

public class Stop implements Concept {
    private DigitalAgent sender;
    private PhysicalAgent receiver;
    private String message;

    public Stop() {
        this.message = Message.STOP.toString();
    }

    public DigitalAgent getSender() {
        return sender;
    }

    public void setSender(DigitalAgent sender) {
        this.sender = sender;
    }

    public PhysicalAgent getReceiver() {
        return receiver;
    }

    public void setReceiver(PhysicalAgent receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

}
