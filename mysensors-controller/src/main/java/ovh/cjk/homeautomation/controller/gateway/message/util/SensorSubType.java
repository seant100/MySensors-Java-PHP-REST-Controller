package ovh.cjk.homeautomation.controller.gateway.message.util;

public enum SensorSubType implements MessageSubType {

    V_TEMP(0), V_HUM(1), V_LIGHT(2), V_DIMMER(3), V_PRESSURE(4), V_FORECAST(5), V_RAIN(6), V_RAINRATE(7), V_WIND(8),
    V_GUST(9), V_DIRECTION(10), V_UV(11), V_WEIGHT(12), V_DISTANCE(13), V_IMPEDANCE(14), V_ARMED(15), V_TRIPPED(16),
    V_WATT(17), V_KWH(18), V_SCENE_ON(19), V_SCENE_OFF(20), V_HEATER(21), V_HEATER_SW(22), V_LIGHT_LEVEL(23),
    V_VAR1(24), V_VAR2(25), V_VAR3(26), V_VAR4(27), V_VAR5(28), V_UP(29), V_DOWN(30), V_STOP(31), V_IR_SEND(32),
    V_IR_RECEIVE(33), V_FLOW(34), V_VOLUME(35), V_LOCK_STATUS(36), V_DUST_LEVEL(37), V_VOLTAGE(38), V_CURRENT(39);

    private int value;

    SensorSubType(int setValue){
        this.value = setValue;
    }

    public int getValue(){
        return this.value;
    }

    public static MessageSubType fromInt(int value){
        for (MessageSubType subType : SensorSubType.values()) {
            if (subType.getValue() == value) {
                return subType;
            }
        }
        return null;
    }

}
