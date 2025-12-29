package main.model;

import java.io.*;
import java.util.*;
import java.sql.*;

import main.db.ConnectionManager;


public class Portal {

    private static Writer writer;
    
    public static void main (String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Daily Sports Dashboard Portal");
            System.out.println();
            System.out.println("Choose an Option");
            System.out.println("Create New Writer");
            System.out.println("Login Existing Writer");
            System.out.println();
            System.out.print("Choice: ");
            String input = reader.readLine();

            if(input.equals( "Create New Writer")) {

                createNewWriter(reader);

            } else if (input.equals("Login Existing Writer")) {

                loginWriter(reader);

            } else {
                System.out.println("Invalid Choice. Try Again.");
            }

        } catch (IOException e) {
            System.out.println("Error. Try again later");
        } 
    }

    public static void createNewWriter(BufferedReader reader) throws IOException {
        System.out.println("Enter Writer Information Below");
        System.out.println();
        System.out.print("Username: ");
        String username = reader.readLine();
        System.out.print("Password: ");
        String password = reader.readLine();
        System.out.print("First Name: ");
        String firstName = reader.readLine();
        System.out.print("Last Name: ");
        String lastName = reader.readLine();
        System.out.print("Role: ");
        String role = reader.readLine();
        System.out.print("Standing: ");
        String standing = reader.readLine();
        System.out.print("Date of Hire (YYYY-MM-DD): ");
        String dateString = reader.readLine();
        java.sql.Date dateOfHire = java.sql.Date.valueOf(dateString);
        System.out.print("Email: ");
        String email = reader.readLine();
        System.out.print("Phone Number (XXX-XXX-XXXX): ");
        String phoneNumber = reader.readLine();


        String hashedPassword = Login.hashPassword(password);

        String dbUrl = System.getenv("DB_URL");

        //System.out.println("DB_URL = " + System.getenv("DB_URL"));

        ConnectionManager cm = new ConnectionManager(dbUrl);
        Connection conn = cm.createConnection();

        String insertWriterQuery = "INSERT INTO Writers (role, first_name, last_name, standing, hire_date, email, phone)" +
                                                " VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            int writerId = 6;

            PreparedStatement pstmt = conn.prepareStatement(insertWriterQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, role);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, standing);
            pstmt.setDate(5, dateOfHire);
            pstmt.setString(6, email);
            pstmt.setString(7, phoneNumber);

            pstmt.executeUpdate();

            System.out.println("Writer Created Successfully!");

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                writerId = generatedKeys.getInt(1);
            } else {
                System.out.println("No ID obtained.");
            }
            pstmt.close();

            writer = new Writer(writerId, role, firstName, lastName, standing, dateOfHire, email, phoneNumber);
 
            String insertLoginQuery = "INSERT INTO Logins VALUES (?, ?, ?);";
        
            PreparedStatement loginStatement = conn.prepareStatement(insertLoginQuery);
            loginStatement.setInt(1, writerId);
            loginStatement.setString(2, username);
            loginStatement.setString(3, hashedPassword);

            loginStatement.executeUpdate();

            System.out.println("Login Created Successfully!");
            loginStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cm.closeConnection();
        }
    }

    public static void loginWriter (BufferedReader reader) throws IOException {
        System.out.println();
        System.out.println("Please enter your login credentials below.");
        System.out.println();
        System.out.print("Username: ");
        String username = reader.readLine();
        System.out.print("Password: ");
        String password = reader.readLine();

        String dbUrl = System.getenv("DB_URL");
        ConnectionManager cm = new ConnectionManager(dbUrl);
        Connection conn = cm.createConnection();
        
        String loginQuery = "SELECT * FROM Logins WHERE username = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(loginQuery);
            pstmt.setString(1,  username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password_hash");
                if (Login.checkPassword(password, storedPassword)) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid password.");
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cm.closeConnection();
        }
    }


}
