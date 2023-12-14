package main;
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;

import controllers.DepartementsController;
import models.Enseignant;
import services.DB;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;


import java.util.Scanner;

public class Main {

    public static boolean isNull(Object ob) {
        return ob == null;
    }

    public static int getIntInput(String... msg) {
        Scanner scan = new Scanner(System.in);
        String message = "Entrez un nombre entier : ";
        if (msg.length > 0)
            message = msg[0];
        System.out.print(message);


        // This method reads the number provided using keyboard
        int num = scan.nextInt();

        // Closing Scanner after the use
        //  scan.close();
        return num;
    }

    public static String getStringInput(String... msg) {
        Scanner scan = new Scanner(System.in);
        String message = "Entrez un texte : ";
        if (msg.length > 0)
            message = msg[0];
        System.out.print(message);

        // This method reads the number provided using keyboard
        String str = scan.nextLine();

        // Closing Scanner after the use
        //  scan.close();
        return str;
    }

    public static void showPrincipalMenu() {
        System.out.println("-------------------------[ Bienvenu ]---------------------------");


        System.out.println("1: Pour gérer les départements");
        System.out.println("2: Pour gérer les filières");
        System.out.println("3: Pour gérer les enseignants");
        System.out.println("4: Pour gérer les modules");
        System.out.println("5: Pour gérer les étudiants");
        System.out.println("0: Pour sortir");

        //"Veuillez sélectionner une option : ")
        int option = getIntInput("Veuillez sélectionner une option : ");
        switch (option) {
            case 1:
                DepartementsController.showMenu();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                // code block
        }
        switch (option) {
            case 1:

                DepartementsController.showMenu();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                // code block
        }


    }


    public static void main(String[] args) {
        Connection dbConnection = null;

        Statement statement = null;

        ResultSet resultSet = null;


        try {

            // Connect to database

            dbConnection = DriverManager.getConnection("jdbc1:mysql://localhost:3306/java", "root", "3vAFY6Q5c@Y.yf8");


            // Create a statement

            statement = dbConnection.createStatement();


            // Execute SQL query

            ResultSet rS = statement.executeQuery("SELECT * FROM enseignant");


            // Process result set
               rS.next();
              Enseignant ens1=new Enseignant(rS.getInt("id"),rS.getString("prenom"),
                rS.getString("nom"),rS.getString("email"),rS.getString("grade"));

ens1.getfullname();
   rS.next();
        Enseignant ens2=new Enseignant(rS.getInt("id"),rS.getString("prenom"),
                rS.getString("nom"),rS.getString("email"),rS.getString("grade"));

        ens2.getfullname();

            while (resultSet.next()) {

                System.out.println("Enseignant ID: " + resultSet.getInt("id"));

                System.out.println("Enseignant Name: " + resultSet.getString("name"));

                System.out.println("Enseignant Department: " + resultSet.getString("department"));

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            // Close result set, statement and connection in a finally block to make sure they are always closed, even if an exception is thrown

            try {

                if (resultSet != null) {

                    resultSet.close();

                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

            try {

                if (statement != null) {

                    statement.close();

                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

            try {

                if (dbConnection != null) {

                    dbConnection.close();

                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }

}
