package warehouse.robotbattery;

import java.io.Serializable;

public class MoveRobotMessage implements Serializable {
    private int distance;

    public MoveRobotMessage(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
}
