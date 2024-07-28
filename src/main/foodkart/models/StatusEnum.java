package main.foodkart.models;

public enum StatusEnum {

    DELIVERED(1, "delivered"),
    NOT_DELIVERED(0, "notDelivered");

    private int statusCode;
    private String statusName;

    StatusEnum(int statusCode, String statusName){
        this.statusCode = statusCode;
        this.statusName = statusName;
    }


}
