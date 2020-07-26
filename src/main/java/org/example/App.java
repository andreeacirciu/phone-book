package org.example;

import org.example.domain.Contact;
import org.example.persistance.ContactRepository;
import org.example.transfer.CreateContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        CreateContactRequest request = new CreateContactRequest();
//        request.setFirstName("Ana");
//        request.setLastName("Pop");
//        request.setContactNumber(123456789);

        ContactRepository contactRepository = new ContactRepository();
//        contactRepository.createContact(request);

//        contactRepository.deleteContact(1);
//        contactRepository.deleteContact(2);


//        List<Contact> contacts = contactRepository.getContacts();
//        System.out.println(contacts);

    }
}
