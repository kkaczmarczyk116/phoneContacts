package com.company;

import java.util.ArrayList;

public class Contact {

    private String name;
    private String number;
    private String email;
    private ArrayList<Message> messages;

    //Constructor for Contact that includes a message

    public Contact(String name, String number, String email, ArrayList<Message> messages) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.messages = messages;
    }

    //Constructor for new contact that has no messages

    public Contact(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.messages = new ArrayList<>();
    }

    //displays the Contact information
    public void getDetails(){
        System.out.println("Name : " + this.name + "" + "\nNumber: " + this.number + "" + "\nEmail: " + this.email);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
