package com.example.neigh.doitmission05;

public enum RequestCodes {
    MenuActivity(101),
    CustomerManagementActivity(102);


    private int value;

    private RequestCodes(int value){
        this.value = value;
    }

    public  int getValue(){
        return  value;
    }
}
