package warehouse.ontology;

import jade.content.Concept;

import java.awt.*;

/**
 @author Hussein Marah
 */
public class Robot implements Concept {
    private String name;
    private String ipAddress;
    private String uwbID;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUwbID() {
        return uwbID;
    }

    public void setUwbID(String uwbID) {
        this.uwbID = uwbID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
