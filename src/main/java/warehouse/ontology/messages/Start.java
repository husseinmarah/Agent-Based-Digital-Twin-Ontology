package warehouse.ontology.messages;

import jade.content.Concept;
import warehouse.ontology.DigitalAgent;
import warehouse.ontology.PhysicalAgent;

public class Start implements Concept {
    private DigitalAgent sender;
    private PhysicalAgent receiver;
    private String message;

    public Start() {
        this.message = Message.START.toString();
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

    public void setMessage(String message) {
        this.message = message;
    }
}
