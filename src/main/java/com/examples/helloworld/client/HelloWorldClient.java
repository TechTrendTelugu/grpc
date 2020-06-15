package com.examples.helloworld.client;

import java.util.concurrent.TimeUnit;

import com.examples.helloworld.GreeterGrpc;
import com.examples.helloworld.GreeterGrpc.GreeterBlockingStub;
import com.examples.helloworld.HelloWorld.HelloWorldRequest;
import com.examples.helloworld.HelloWorld.HelloworldResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class HelloWorldClient {

	public static void greet(String name) {
	    
		// TODO Auto-generated method stub
		
		String target = "localhost:9090";  //Target server port
				 
		// Create a communication channel to the server, known as a Channel
		ManagedChannel channel=ManagedChannelBuilder.forTarget(target)
						 				.usePlaintext()
						 				.build();
		GreeterBlockingStub blockingStub=GreeterGrpc.newBlockingStub(channel);
	    
		//Prepare the request
		HelloWorldRequest request = HelloWorldRequest.newBuilder().setName(name).build();
	    
		HelloworldResponse response;
	    
		try {
			//Call the method	
			response = blockingStub.sayHello(request);
			//print the response
			System.out.println("Greeting: " + response.getMessage());
	    } catch (StatusRuntimeException e) {
	    	return;
	    } finally {
	        try {
				channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	      }
		
		
	  }
	
	
	public static void main(String[] args) {
		
		String user = "Srinivas";
		greet(user); 
		
	}

}
