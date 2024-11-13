package com.example.grpc_demo.service;

import com.example.grpc_demo.UserProto;
import com.example.grpc_demo.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    @PostConstruct
    public void init() {
        System.out.println("gRPC Server initialized...");
    }

    @Override
    public void getUser(UserProto.UserRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        // Mulai catat waktu respons
        long startTime = System.currentTimeMillis();
        System.out.println("Request received at: " + startTime + " ms");
        UserProto.UserResponse userResponse = null;

        if (request.getId() == 1){
        // Proses permintaan dan buat respons
            userResponse = UserProto.UserResponse.newBuilder()
                    .setId(request.getId())
                    .setName("John Doe")
                    .setEmail("johndoe@example.com")
                    .setUmur("890")
                    .build();
        }

        // Kirim respons ke klien
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();

        // Catat waktu respons selesai
        long endTime = System.currentTimeMillis();
        System.out.println("Response sent at: " + endTime + " ms");

        // Hitung waktu yang dibutuhkan untuk respons
        long responseTime = endTime - startTime;
        System.out.println("Response time: " + responseTime + " ms");
    }
}
