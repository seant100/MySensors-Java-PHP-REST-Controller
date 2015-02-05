package ovh.cjk.homeautomation.controller.gateway.message.util;

public enum MessageType {

    PRESENTATION(0), SET(1), REQUEST(2), INTERNAL(3), STREAM(4);

    private int value;

    MessageType(int setValue){
        this.value = setValue;
    }

    public int getValue() {
        return this.value;
    }

    public static MessageType fromInt(int value){
        for (MessageType b : MessageType.values()) {
            if (b.getValue() == value) {
                return b;
            }
        }
        return null;
    }
}
