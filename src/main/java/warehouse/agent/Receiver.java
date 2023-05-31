package warehouse.agent;

import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.leap.LEAPCodec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.lang.acl.ACLMessage;
import warehouse.ontology.IsTwin;
import warehouse.ontology.messages.Start;
import warehouse.ontology.WarehouseOntology;

public class Receiver extends Agent {
    private ContentManager manager = getContentManager();
    private Codec codec = new LEAPCodec();
    private Ontology ontology = WarehouseOntology.getInstance();
    private IsTwin isTwin = null;
    private Start start = null;
//    private Start send = null;
    class ReceiverBehaviour extends SimpleBehaviour {
        private boolean finished = false;
        public ReceiverBehaviour(Agent a) {
            super(a);
        }

        public boolean done() {
            return finished;
        }

        public void action() {
            try {
                System.out.println("[" + getLocalName() + "] Waiting for a message...");
                ACLMessage msg = blockingReceive();
                if (msg != null) {
                    System.out.println("msg = " + msg);
                    switch (msg.getPerformative()) {
                        case ACLMessage.INFORM:
                            ContentElement contentElement = manager.extractContent(msg);
                            if (contentElement instanceof IsTwin) {
                                isTwin = (IsTwin) contentElement;
                                System.out.println("digitalTwinOf.getDigitalTwin().getName() = " + isTwin.getDigitalTwin().getName());
                                System.out.println("digitalTwinOf.getPhysicalTwin().getName() = " + isTwin.getPhysicalTwin().getName());
                                System.out.println("digitalTwinOf.getPhysicalSystem().getName() = " + isTwin.getPhysicalComponent().getName());
                                System.out.println("[" + getLocalName() + "] information stored.");
                                break;
                            }
                            else if(contentElement instanceof Action){
                                // Get the requested action
                                Action action = (Action) contentElement;
                                start = (Start) action.getAction();
                                break;
                            }
                        default:
                            System.out.println("[" + getLocalName() + "] Malformed message.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    protected void setup() {
        manager.registerLanguage(codec);
        manager.registerOntology(ontology);
        //Register the SL content language
        manager.registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL);

//Register the mobility ontology
        manager.registerOntology(JADEManagementOntology.getInstance());
        addBehaviour(new ReceiverBehaviour(this));
    }
}
