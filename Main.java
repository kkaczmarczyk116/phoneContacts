package com.company;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    //id to identify each message, will simply add 1 for each message
    private static int id = 0;

    public static void main(String[] args) {
	// write your code here
        //Initialize ArrayList
        contacts = new ArrayList<>();
        showInitialOptions();

    }

    /*
    Method to show the first initial screen and show 3 options
    Will be calling 3 other methods
     */
    private static void showInitialOptions(){
        scanner = new Scanner(System.in);
        System.out.println(" Greetings ");
        System.out.println("\t1. Manage contacts\n" + "\t2. Messages\n" + "\t3. Quit\n");
        System.out.println("Select Option ");
        int sel = scanner.nextInt();
        switch(sel){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
        }


    }

    // This method will be able to call on 4 new methods + showInitialOptions()

    private static void manageContacts(){
        System.out.println("Select One: " + "\n\t1. Show all contacts"
                                            +"\n\t2. Add a new contact"
                                            +"\n\t3. Search for a contact"
                                            +"\n\t4. Delete a contact"
                                            +"\n\t5. Go back");
        int sel2 = scanner.nextInt();
        switch (sel2){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void showAllContacts(){
        /*
        If there is a contact then contacts
        then show "main menu"
        else shoe "main menu"
         */
        if(contacts.size()>0){
            for(Contact c: contacts){
                c.getDetails();
                System.out.println("---------------------------------");
        }
        showInitialOptions();
        }else{
            System.out.println("No contacts");
        }
        showInitialOptions();
    }

    /*
    Check for blank entry,if so, recursively call method for user to try again
    else loop through each Contact in contacts and if name is in contacts change boolean(to deal with "already in contacts"
    If it does exist then recursively call the method to try again
    else add new contact(create new object and add to Arraylist
    Lastly call "main menu"
     */
    private static void addNewContact(){
        System.out.println("Enter the name ");
        String name = scanner.next();
        System.out.println("Enter the number ");
        String number = scanner.next();
        System.out.println("Enter the email ");
        String email = scanner.next();

        if ( name.equals("") || number.equals("") || email.equals("")){
            System.out.println("Please enter the correct information");
            addNewContact();
        }else{
            boolean doesExist = false;
            for(Contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if(doesExist){
                System.out.println("Contact is already added with this name");
                addNewContact();
            }else{
                Contact contact = new Contact(name,number,email);
                contacts.add(contact);
                System.out.println("Contact added successfully");
            }

        }

        showInitialOptions();

    }

    /*
    If blank then run call again
    else loop through contacts and if name matches then show details, change boolean
    then run "main menu"
     */
    public static void searchForContact(){
        System.out.println("Enter Contact name");
        String name = scanner.next();
        if( name.equals("")){
            System.out.println("Please enter a Name");
            searchForContact();
        }else{
            boolean doesExist = false;
            for(Contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    c.getDetails();
                }
            }
            if (!doesExist){
                System.out.println("there is no such contact in your phone");
            }
        }
        showInitialOptions();
    }


    /*
    Had error related to Iterating through Iterating, so I created another ArrayList to remove so it wouldn't
    reference the object in the same loop.
     */
    public static void deleteContact(){
        ArrayList<Contact> toRemove = new ArrayList<>();
        System.out.println("Please enter Name");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter name");
            deleteContact();
        }else{
            boolean doesExist = false;
            for(Contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    toRemove.add(c);
                    //contacts.remove(c);
                }
            }
            contacts.removeAll(toRemove);
            if (!doesExist){
                System.out.print("No such contact Exists");
            }
        }
        showInitialOptions();
    }


    //Create a sub menu and call on 2 new methods + showInitialOptions()

    private static void manageMessages(){
        System.out.println("Select One: "
                + "\n\t1. Show all messages"
                +"\n\t2. Send a new message"
                +"\n\t3. Go Back");
        int sel3 = scanner.nextInt();
        switch (sel3){
            case 1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    /*
    Check for black entry, else then loop contacts and if the contact exists change boolean
    if exist ask for message, check for blank
    else id++ and create new message object
    create new "newMessages" and then add the previous message to all the messages
    then setMessages to "newMessages"
     */
    private static void sendNewMessage() {
        System.out.println("Who are you sending it to?");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Enter name of Contact");
            sendNewMessage();
        }else{
            boolean doesExist = false;
            for(Contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if(doesExist){
                System.out.println("Enter message");
                String text = scanner.next();
                if(text.equals("")){
                    System.out.println("Enter a massage");
                    sendNewMessage();
                }else{
                    id++;
                    Message newMessage = new Message(text,name,id);
                    for(Contact c: contacts){
                        if(c.getName().equals(name)){
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                            c.setMessages(newMessages);
                        }
                    }
                }

            }else{
                System.out.println("No such contact");
            }
        }
        showInitialOptions();
    }

    /*
    Create new Arraylist with all the messages from different contacts
    check if there is no messages
    then run the "main menu"
     */
    private static void showAllMessages() {
        ArrayList<Message> allMessages= new ArrayList<>();
        for(Contact c: contacts){
            allMessages.addAll(c.getMessages());
        }

        if(allMessages.size()>0){
            for(Message m: allMessages) {
                m.getDetails();
                System.out.println("---------------");
            }
            }else {
            System.out.println("No messages");
        }
        showInitialOptions();
        }

}
