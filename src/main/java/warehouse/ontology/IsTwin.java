package warehouse.ontology;

import jade.content.Predicate;
/**
 @author Hussein Marah
 */
public class IsTwin implements Predicate {
    private DigitalAgent digitalTwin = null;
    private PhysicalAgent physicalTwin = null;
    private MobileRobot physicalComponent = null;

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

    public MobileRobot getPhysicalComponent() {
        return physicalComponent;
    }

    public void setPhysicalComponent(MobileRobot physicalComponent) {
        this.physicalComponent = physicalComponent;
    }
}
