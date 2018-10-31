package com.jr.rest.jersey.tutorialsPoint._2_CRUD;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class WebServiceTester  {

   private Client client;
   private String REST_SERVICE_URL = "http://localhost:8080/com.jr.rest/jersey/tPoint/2/UserService/users";
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String PASS = "pass";
   private static final String FAIL = "fail";

   private void init(){
      this.client = ClientBuilder.newClient();
   }

   public static void main(String[] args){
      WebServiceTester tester = new WebServiceTester();
      tester.init();

      tester.testGetAllUsers();
      tester.testGetUser();
      tester.testUpdateUser();
      tester.testAddUser();
      tester.testDeleteUser();
   }

   private void testGetAllUsers(){
      GenericType<List<User>> list = new GenericType<List<User>>() {};
      List<User> users = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .get(list);
      String result = PASS;

      if(users.isEmpty()){
         result = FAIL;
      }
      System.out.println("Test case name: testGetAllUsers, Result: " + result );
   }

   private void testGetUser(){
      User sampleUser = new User();
      sampleUser.setId(1);

      User user = client
         .target(REST_SERVICE_URL)
         .path("/{userid}")
         .resolveTemplate("userid", 1)
         .request(MediaType.APPLICATION_XML)
         .get(User.class);
      String result = FAIL;

      if(sampleUser != null && sampleUser.getId() == user.getId()){
         result = PASS;
      }
      System.out.println("Test case name: testGetUser, Result: " + result );
   }

   private void testUpdateUser(){
      Form form = new Form();
      form.param("id", "1");
      form.param("name", "suresh");
      form.param("profession", "clerk");

      String callResult = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .put(Entity.entity(form,
                         MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
      String result = PASS;

      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testUpdateUser, Result: " + result );
   }

   private void testAddUser(){
      Form form = new Form();
      form.param("id", "2");
      form.param("name", "naresh");
      form.param("profession", "clerk");

      String callResult = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .post(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
   
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testAddUser, Result: " + result );
   }

   private void testDeleteUser(){
      String callResult = client
         .target(REST_SERVICE_URL)
         .path("/{userid}")
         .resolveTemplate("userid", 2)
         .request(MediaType.APPLICATION_XML)
         .delete(String.class);

      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testDeleteUser, Result: " + result );
   }
}