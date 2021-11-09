package com.example.javahiltdemo.navigator;

enum Screens{
    BUTTONS,
    LOGS
}

interface AppNavigator{
    public void navigateTo(Screens screen);
}