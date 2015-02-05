package ovh.cjk.homeautomation.controller.gateway.message.util;

public enum InternalSubType implements MessageSubType {

    I_BATTERY_LEVEL(0), I_TIME(1), I_VERSION(2), I_ID_REQUEST(3), I_ID_RESPONSE(4), I_INCLUSION_MODE(5),
    I_CONFIG(6), I_FIND_PARENT(7), I_FIND_PARENT_RESPONSE(8), I_LOG_MESSAGE(9), I_CHILDREN(10), I_SKETCH_NAME(11),
    I_SKETCH_VERSION(12), I_REBOOT(13), I_GATEWAY_READY(14);

    private final int value;

    InternalSubType(int setValue){
        value = setValue;
    }

    public int getValue(){
        return this.value;
    }

    public static MessageSubType fromInt(int value){
        for (MessageSubType subType : InternalSubType.values()) {
            if (subType.getValue() == value) {
                return subType;
            }
        }
        return null;
    }

}
