package com.example.mobilelab2;

public class Contact {
    private String first_name;
    private String second_name;
    private String phoneNumber;

    public Contact(String first_name, String second_name, String phoneNumber, String email) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
