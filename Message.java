package com.company;

public class Message {

    private String text;
    private String recipient;
    private int id;

    //Constructor for message

    public Message(String text, String recipient, int id) {
        this.text = text;
        this.recipient = recipient;
        this.id = id;
    }

    //get contents of message
    public void getDetails(){
        System.out.println("Contact Name: " + recipient + "\nMessage: " + text + " " +"\nId " + id);
    }


}
