package com.xone.travelplanner.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    User(0, "user"),
    Admin(1, "admin"),
    Backdoor(3, "backdoor");

    private final int value;
    private final String Key;

//    public static HashMap<Integer, String> getList() {
//        HashMap<Integer, String> list = new HashMap<>();
//        Arrays.stream(UserRole.values())
//    }
}
