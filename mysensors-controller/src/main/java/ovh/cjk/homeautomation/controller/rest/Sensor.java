package ovh.cjk.homeautomation.controller.rest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Sensor {

    private int id;
    private ArrayList<SensorValue> sensorValues;

    public Sensor(JSONObject sensorData){
        this.id = sensorData.getInt("sensor_id");

        this.sensorValues = new ArrayList<SensorValue>();
        JSONArray sensorValuesJSON = sensorData.getJSONArray("sensor_values");
        for(int i = 0; i < sensorValuesJSON.length(); i++){
            JSONObject sensorJSON = sensorValuesJSON.getJSONObject(i);
            this.sensorValues.add(new SensorValue(sensorJSON));
        }
    }

    public Sensor(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<SensorValue> getSensorValues() {
        return sensorValues;
    }

}
