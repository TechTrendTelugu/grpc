package com.examples.helloworld.server;

import java.io.IOException;

import com.examples.helloworld.GreeterGrpc;
import com.examples.helloworld.HelloWorld.HelloWorldRequest;
import com.examples.helloworld.HelloWorld.HelloworldResponse;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HelloWorldServer {

	
	public static void main(String[] args) {
		//Start the server
		try {
			Server server=ServerBuilder.forPort(9090)
							.addService(new GreeterImpl())
							.build()
							.start();
			System.out.println("Server Started");
			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}
	
	//Write the service class to implement business logic.
	static class GreeterImpl extends GreeterGrpc.GreeterImplBase{
		@Override
		public void sayHello(HelloWorldRequest  req, StreamObserver<HelloworldResponse> responseObserver) {
			HelloworldResponse reply = HelloworldResponse.newBuilder().setMessage("Hello " + req.getName()).build();
		    responseObserver.onNext(reply);
		    responseObserver.onCompleted();
		}
	}
}
