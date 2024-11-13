//package com.example.grpc_demo.controller;
//
//import com.example.grpc_demo.UserProto;
//
//@Restcontroller
//public class UserController {
//
//    @GetMapping("/users/{id}")
//    public UserProto.UserResponse getUser(@PathVariable("id") int id) {
//         return UserProto.UserResponse.newBuilder()
//                .setId(id)
//                .setName("John Doe")
//                .setEmail("johndoe@example.com")
//                .build();
//    }
//}
//
