package com.example.grpc_demo.client;

import com.example.grpc_demo.UserProto;
import com.example.grpc_demo.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        UserProto.UserRequest request = UserProto.UserRequest.newBuilder().setId(1).build();
        UserProto.UserResponse response = stub.getUser(request);

        System.out.println("User Name: " + response.getName());
        System.out.println("User Email: " + response.getEmail());

        channel.shutdown();
    }
}
