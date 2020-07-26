package org.example.persistance;

import org.example.domain.Contact;
import org.example.transfer.CreateContactRequest;
import org.example.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    //1
    public void createContact(CreateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO contact (firstName, lastName, phoneNumber) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, String.valueOf(request.getPhoneNumber()));

            preparedStatement.executeUpdate();
        }
    }

    //2
    public List<Contact> getContacts() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT idContact, firstName, lastName, phoneNumber FROM contact";

        List<Contact> contacts = new ArrayList<>();

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setIdContact(resultSet.getLong("idContact"));
                contact.setFirstName(resultSet.getString("firstName"));
                contact.setLastName(resultSet.getString("lastName"));
                contact.setPhoneNumber(resultSet.getLong("phoneNumber"));

                contacts.add(contact);
            }
        }
        return contacts;
    }

    //4
    public void updateContact(long idContact, UpdateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE contact SET firstName=?, lastName=?, phoneNumber=? WHERE idContact=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, String.valueOf(request.getPhoneNumber()));
            preparedStatement.setLong(4, idContact);

            preparedStatement.executeUpdate();
        }
    }

    //5
    public void deleteContact(long idContact) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM contact WHERE idContact=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, idContact);
            preparedStatement.executeUpdate();
        }
    }

    //3
    //aici
    public List<Contact> getContacts(String firstName, UpdateContactRequest request) throws SQLException, IOException, ClassNotFoundException {

        String sql = "SELECT idContact, firstName, lastName, phoneNumber FROM contact WHERE firstName= ?";

        List<Contact> contacts = new ArrayList<>();


        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());

            preparedStatement.executeUpdate();
        }

        return contacts;
    }
}
