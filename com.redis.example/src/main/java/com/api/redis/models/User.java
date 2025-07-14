package com.api.redis.models;

import lombok.*;

import java.io.Serializable;


//Serializable is a marker interface in Java (java.io.Serializable).
//It tells the JVM that this object can be converted into a byte stream, and then later reconstructed ("deserialized") back into an object.
//“This User object can be safely turned into bytes and restored later.”
//Use Case: Storing objects in Redis - Redis stores byte data, so the object must be serializable.
//bytes are not human-readable — they look like a mix of symbols, numbers, and binary patterns.
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private  String userId;
    private String name;
    private String phone;
    private String email;

}
