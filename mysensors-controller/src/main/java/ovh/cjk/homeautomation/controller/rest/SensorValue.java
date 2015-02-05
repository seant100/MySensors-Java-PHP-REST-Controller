package ovh.cjk.homeautomation.controller.rest;

import org.json.JSONObject;
import ovh.cjk.homeautomation.controller.gateway.message.util.MessageSubType;
import ovh.cjk.homeautomation.controller.gateway.message.util.SensorSubType;

public class SensorValue {

    private int id;
    private MessageSubType valueType;

    public SensorValue(JSONObject sensorData){
        this.id = sensorData.getInt("id");
        this.valueType = SensorSubType.fromInt(sensorData.getInt("value_type"));
    }

}
