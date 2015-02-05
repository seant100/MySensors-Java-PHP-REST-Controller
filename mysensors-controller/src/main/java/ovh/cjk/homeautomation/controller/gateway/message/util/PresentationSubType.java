package ovh.cjk.homeautomation.controller.gateway.message.util;

public enum PresentationSubType implements MessageSubType {

    S_DOOR(0), S_MOTION(1), S_SMOKE(2), S_LIGHT(3), S_DIMMER(4), S_COVER(5), S_TEMP(6), S_HUM(7), S_BARO(8), S_WIND(9),
    S_RAIN(10), S_UV(11), S_WEIGHT(12), S_POWER(13), S_HEATER(14), S_DISTANCE(15), S_LIGHT_LEVEL(16), S_ARDUINO_NODE(17),
    S_ARDUINO_RELAY(18), S_LOCK(19), S_IR(20), S_WATER(21), S_AIR_QUALITY(22), S_CUSTOM(23), S_DUST(24),
    S_SCENE_CONTROLLER(25);

    private final int value;

    PresentationSubType(int setValue){
        this.value = setValue;
    }

    public int getValue(){
        return this.value;
    }

    public static MessageSubType fromInt(int value){
        for (MessageSubType subType : PresentationSubType.values()) {
            if (subType.getValue() == value) {
                return subType;
            }
        }
        return null;
    }
}
