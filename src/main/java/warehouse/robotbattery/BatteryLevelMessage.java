package warehouse.robotbattery;

import java.io.Serializable;

public class BatteryLevelMessage implements Serializable {
    private int batteryLevel;

    public BatteryLevelMessage(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}

