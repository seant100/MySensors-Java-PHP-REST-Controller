package ovh.cjk.homeautomation.controller.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import ovh.cjk.homeautomation.controller.config.RestConstants;
import ovh.cjk.homeautomation.controller.rest.exception.SensorAlreadyExistsException;

import java.util.ArrayList;

public class Node {

    private int id;
    private String name;
    private String sketchName;
    private String sketchVersion;
    private int batteryPercent;

    public Node(){}

    public Node(JSONObject json){
        this.id = json.getInt("id");
        this.name = json.getString("name");
        this.sketchName = json.getString("sketch_name");
        this.sketchVersion = json.getString("sketch_version");
        this.batteryPercent = json.getInt("battery_percent");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSketchName() {
        return sketchName;
    }

    public void setSketchName(String sketchName) {
        this.sketchName = sketchName;
    }

    public String getSketchVersion() {
        return sketchVersion;
    }

    public void setSketchVersion(String sketchVersion) {
        this.sketchVersion = sketchVersion;
    }

    public ArrayList<Sensor> getAllSensors(){
        ArrayList<Sensor> sensors = new ArrayList<Sensor>();

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(RestConstants.REST_API_NODE_URL + "/" + this.id + "/" + RestConstants.REST_API_NODE_SENSOR_ENDPOINT)
                    .asJson();

            if (jsonResponse.getStatus() == 200) {
                JSONArray sensorArray = jsonResponse.getBody().getArray();

                for (int i = 0; i < sensorArray.length(); i++) {
                    JSONObject sensorData = sensorArray.getJSONObject(i);
                    sensors.add(new Sensor(sensorData));
                }
            }
        } catch (UnirestException e){
            e.printStackTrace();
        }

        return sensors;
    }

    public Sensor getSensorById(int id){
        for(Sensor sensor : this.getAllSensors()){
            if(sensor.getId() == id){
                return sensor;
            }
        }
        return null;
    }

    public int getBatteryPercent() {
        return batteryPercent;
    }

    public void setBatteryPercent(int batteryPercent) {
        this.batteryPercent = batteryPercent;
    }

    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append("ID: ").append(id);
        string.append(", Name: ").append(name);
        string.append(", Sketch Name: ").append(sketchName);
        string.append(", Sketch Version: ").append(sketchVersion);

        return string.toString();
    }

    public Sensor addSensor(int sensorId) throws SensorAlreadyExistsException {
        Sensor newSensor = new Sensor(sensorId);
        return this.addSensor(newSensor);
    }

    public Sensor addSensor(Sensor sensor) throws SensorAlreadyExistsException{
        try{
            String endpoint = RestConstants.REST_API_NODE_URL + "/" + this.id + "/" + RestConstants.REST_API_NODE_SENSOR_ENDPOINT;
            HttpResponse<JsonNode> jsonResponse = Unirest.post(endpoint)
                    .field("sensor_id", sensor.getId())
                    .asJson();

            if(jsonResponse.getStatus() == 200){
                return new Sensor(jsonResponse.getBody().getObject());
            } else if (jsonResponse.getStatus() == 500){
                if(jsonResponse.getBody().getArray().get(0).equals(RestConstants.SENSOR_EXISTS_ERROR)){
                    throw new SensorAlreadyExistsException();
                }
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }

}
