package web.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class RestClient {
    public void call(){
        Client client = ClientBuilder.newClient();
        SimpleObject simpleObject = client.target("http://localhost:8080/resources/MyWebService/object").request().get(SimpleObject.class);
        
        System.out.println(simpleObject);
    }
    
    public static void main(String[] args) {
        new RestClient().call();
    }
}