package com.example.grpc_demo;

import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class testCreateUser {

    private UserServiceGrpc.UserServiceImplBase userService;

    @Mock
    private StreamObserver<UserProto.UserResponse> responseObserver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Implementasikan metode getUser
        userService = new UserServiceGrpc.UserServiceImplBase() {
            @Override
            public void getUser(UserProto.UserRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
                if (request.getId() == 1) {
                    // Mock data response
                    UserProto.UserResponse response = UserProto.UserResponse.newBuilder()
                            .setId(1)
                            .setName("John Doe")
                            .setEmail("johndoe@example.com")
                            .setUmur("890")
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                } else {
                    responseObserver.onError(new RuntimeException("User not found"));
                }
            }
        };
    }

    @Test
    public void testGetUser_withValidId() {
        // Arrange
        UserProto.UserRequest request = UserProto.UserRequest.newBuilder().setId(1).build();

        // Act
        userService.getUser(request, responseObserver);

        // Assert
        ArgumentCaptor<UserProto.UserResponse> responseCaptor = ArgumentCaptor.forClass(UserProto.UserResponse.class);
        verify(responseObserver).onNext(responseCaptor.capture());
        verify(responseObserver).onCompleted();

        UserProto.UserResponse capturedResponse = responseCaptor.getValue();
        assertEquals(1, capturedResponse.getId());
        assertEquals("John Doe", capturedResponse.getName());
        assertEquals("johndoe@example.com", capturedResponse.getEmail());
        assertEquals("890", capturedResponse.getUmur());
    }

    @Test
    public void testGetUser_withInvalidId() {
        // Arrange
        UserProto.UserRequest request = UserProto.UserRequest.newBuilder().setId(2).build();

        // Act
        userService.getUser(request, responseObserver);

        // Assert
        ArgumentCaptor<Throwable> errorCaptor = ArgumentCaptor.forClass(Throwable.class);
        verify(responseObserver).onError(errorCaptor.capture());

        Throwable capturedError = errorCaptor.getValue();
        assertEquals("User not found", capturedError.getMessage());
    }
}
