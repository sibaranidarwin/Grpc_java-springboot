package com.example.grpc_demo.service;

import com.example.grpc_demo.UserProto;
import com.example.grpc_demo.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUser(UserProto.UserRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        // Implementasikan logika untuk mencari atau mengembalikan data pengguna
        if (request.getId() == 1) {
            UserProto.UserResponse response = UserProto.UserResponse.newBuilder()
                    .setId(1)
                    .setName("John Doe")
                    .setEmail("johndoe@example.com")
                    .setUmur("30")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("User not found"));
        }
    }

    @Override
    public void createUser(UserProto.CreateUserRequest request, StreamObserver<UserProto.CreateUserResponse> responseObserver) {

        int generateduserid = generateuserid();

        UserProto.CreateUserResponse response = UserProto.CreateUserResponse.newBuilder()
                .setId(generateduserid)
                .setMessage("Info Success")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



    private int generateuserid(){
        return (int) (Math.random() * 1000);
    }
}
