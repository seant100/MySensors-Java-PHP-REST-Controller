package ovh.cjk.homeautomation.controller.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import ovh.cjk.homeautomation.controller.config.RestConstants;

import java.util.ArrayList;

public class NodeManager {

    public ArrayList<Node> getAll() throws UnirestException{
        ArrayList<Node> nodes = new ArrayList<Node>();

        HttpResponse<JsonNode> jsonResponse = Unirest.get(RestConstants.REST_API_NODE_URL)
                .asJson();

        if(jsonResponse.getStatus() == 200){
            JSONArray nodeArray = jsonResponse.getBody().getArray();

            for(int i = 0; i < nodeArray.length(); i++){
                JSONObject singleNode = nodeArray.getJSONObject(i);
                nodes.add(new Node(singleNode));
            }
        }

        return nodes;
    }

    public Node getById(int id){
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(RestConstants.REST_API_NODE_URL + "/{id}")
                    .routeParam("id", Integer.toString(id))
                    .asJson();

            if (jsonResponse.getStatus() == 200) {
                return new Node(jsonResponse.getBody().getObject());
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Node add(){
        Node node = new Node();
        return add(node);
    }

    public Node add(Node node){
        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.post(RestConstants.REST_API_NODE_URL)
                    .field("name", node.getName())
                    .field("sketchName", node.getSketchName())
                    .field("sketchVersion", node.getSketchVersion())
                    .field("batteryPercent", node.getBatteryPercent())
                    .asJson();
            if(jsonResponse.getStatus() == 200){
                return new Node(jsonResponse.getBody().getObject());
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Node update(Node node){
        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.put(RestConstants.REST_API_NODE_URL + "/{id}")
                    .routeParam("id", Integer.toString(node.getId()))
                    .field("name", node.getName())
                    .field("sketchName", node.getSketchName())
                    .field("sketchVersion", node.getSketchVersion())
                    .field("batteryPercent", node.getBatteryPercent())
                    .asJson();

            if(jsonResponse.getStatus() == 200){
                return new Node(jsonResponse.getBody().getObject());
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }

}
