package warehouse.ontology;

import jade.content.Predicate;
/**
 @author Hussein Marah
 */
public class DigitalTwinOf implements Predicate {
    private DigitalAgent digitalTwin = null;
    private PhysicalAgent physicalTwin = null;
    private Robot physicalSystem = null;

    public DigitalAgent getDigitalTwin() {
        return digitalTwin;
    }

    public void setDigitalTwin(DigitalAgent digitalTwin) {
        this.digitalTwin = digitalTwin;
    }

    public PhysicalAgent getPhysicalTwin() {
        return physicalTwin;
    }

    public void setPhysicalTwin(PhysicalAgent physicalTwin) {
        this.physicalTwin = physicalTwin;
    }

    public Robot getPhysicalSystem() {
        return physicalSystem;
    }

    public void setPhysicalSystem(Robot physicalSystem) {
        this.physicalSystem = physicalSystem;
    }
}
