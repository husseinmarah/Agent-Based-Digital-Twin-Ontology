package warehouse.ontology.messages;
/**
 @author Hussein Marah
 */
public enum Message {
    START {
        public String toString() {
            return "start";
        }
    },
    LINESTRING {
        public String toString() {
            return "line_string";
        }
    },

    STOP {
        public String toString() {
            return "stop";
        }
    },

    ACK {
        public String toString() {
            return "ack";
        }
    },
    OBSTACLE {
        public String toString() {
            return "obstacle";
        }
    },

    NO_OBSTACLE {
        public String toString() {
            return "no_obstacle";
        }
    },

    COLLISION {
        public String toString() {
            return "collision";
        }
    },
    NO_COLLISION {
        public String toString() {
            return "no_collision";
        }
    },
    LOW_BATTERY {
        public String toString() {
            return "low_battery";
        }
    }
}