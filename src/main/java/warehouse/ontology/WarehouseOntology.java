package warehouse.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.ReflectiveIntrospector;
import jade.content.schema.*;
import warehouse.ontology.messages.Start;
import warehouse.ontology.messages.Stop;

import java.awt.*;

/**
 * @author Hussein Marah
 */

public class WarehouseOntology extends Ontology {
    // The name identifying this ontology
    public static final String ONTOLOGY_NAME = "Warehouse-Multi-Robot-Ontology";
    // VOCABULARY
    public static final String INTELLIGENT_AGENT = "IntelligentAgent";
    public static final String ROBOT = "ROBOT";
    public static final String PHYSICAL_AGENT = "physicalAgent";
    public static final String DIGITAL_AGENT = "digitalAgent";

    // Slots
    public static final String NAME = "name";
    public static final String IP_ADDRESS = "ipAddress";
    public static final String UWB_ID = "uwbID";
    public static final String LOCATION = "location";
    public static final String AID = "aid";
    public static final String POINT = "point";

    // Predicates
    public static final String IS_TWIN = "IS_TWIN";
    // Roles in predicates
    public static final String DIGITAL_TWIN = "digitalTwin"; // Digital Twin
    public static final String PHYSICAL_TWIN = "physicalTwin"; //Physical Twin
    public static final String PHYSICAL_COMPONENT = "physicalComponent"; // Physical System

    // Actions
    public static final String START = "START";
    public static final String STOP = "STOP";
    // Arguments in actions
    public static final String SENDER = "SENDER";
    public static final String RECEIVER = "RECEIVER";

    // The singleton instance of this ontology
    private static WarehouseOntology theInstance = new WarehouseOntology(BasicOntology.getInstance());

    // Retrieve the singleton WarehouseOntology ontology instance
    public static Ontology getInstance() {
        return theInstance;
    }

    // Private constructor
    public WarehouseOntology(Ontology base) {
        super(ONTOLOGY_NAME, base, new ReflectiveIntrospector());
        try {
            PrimitiveSchema stringSchema = (PrimitiveSchema) getSchema(BasicOntology.STRING);

            ConceptSchema robotSchema = new ConceptSchema(ROBOT);
            robotSchema.add(NAME, stringSchema);
            robotSchema.add(IP_ADDRESS, stringSchema, ObjectSchema.OPTIONAL);
            robotSchema.add(UWB_ID, stringSchema, ObjectSchema.OPTIONAL);
            robotSchema.add(LOCATION, stringSchema, ObjectSchema.OPTIONAL);
            // define intelligent agent schema
            ConceptSchema intelligentAgentSchema = new ConceptSchema(INTELLIGENT_AGENT);
            intelligentAgentSchema.add(NAME, stringSchema, ObjectSchema.OPTIONAL);
            intelligentAgentSchema.add(AID, (ConceptSchema) getSchema(BasicOntology.AID), ObjectSchema.OPTIONAL);
            // define physical agent schema
            ConceptSchema physicalAgentSchema = new ConceptSchema(PHYSICAL_AGENT);
            physicalAgentSchema.addSuperSchema(intelligentAgentSchema);
            // define digital agent schema
            ConceptSchema digitalAgentSchema = new ConceptSchema(DIGITAL_AGENT);
            digitalAgentSchema.addSuperSchema(intelligentAgentSchema);

            // define IsTwin predicate schema
            PredicateSchema istTwinSchema = new PredicateSchema(IS_TWIN);
            istTwinSchema.add(DIGITAL_TWIN, digitalAgentSchema);
            istTwinSchema.add(PHYSICAL_TWIN, physicalAgentSchema);
            istTwinSchema.add(PHYSICAL_COMPONENT, robotSchema);

            add(robotSchema, Robot.class);
            add(physicalAgentSchema, PhysicalAgent.class);
            add(digitalAgentSchema, DigitalAgent.class);
            add(intelligentAgentSchema, IntelligentAgent.class);
            add(istTwinSchema, IsTwin.class);

            // Start message action
            AgentActionSchema sendSchema = new AgentActionSchema(START);
            sendSchema.add(SENDER, physicalAgentSchema);
            sendSchema.add(RECEIVER, digitalAgentSchema);
            add(sendSchema, Start.class);

            // Stop message action
            AgentActionSchema stopSchema = new AgentActionSchema(STOP);
            stopSchema.add(SENDER, digitalAgentSchema);
            stopSchema.add(RECEIVER, physicalAgentSchema);
            add(stopSchema, Stop.class);

        } catch (OntologyException oe) {
            oe.printStackTrace();
        }
    }
}

