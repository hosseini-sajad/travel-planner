package com.xone.travelplanner.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    User(0, "کاربر"),
    Admin(1, "ادمین"),
    Backdoor(3, "بک دور");

    private final int value;
    private final String Key;

//    public static HashMap<Integer, String> getList() {
//        HashMap<Integer, String> list = new HashMap<>();
//        Arrays.stream(UserRole.values())
//    }
}
