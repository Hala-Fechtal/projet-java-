package models;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;


public class Enseignant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String grade;
    private Departement dept;
    String fullname = nom + " " + prenom;
    ArrayList<Module> modules = new ArrayList<Module>();


    public Enseignant() {
    }

    public Enseignant(String nom, String prenom, String email, String grade, Departement dept) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.grade = grade;
        this.dept = dept;
    }

    public Enseignant(int id, String prenom, String nom, String email, String grade) {

        this.id=id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.grade = grade;
    }


    public void setFullname(String fullname){

        this.fullname=fullname;

    }
    public String  getFullname(String fullname){
        return fullname;

    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Departement getDept() {
        return dept;
    }

    public void setDept(Departement dept) {
        this.dept = dept;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean equals(Enseignant obj) {
        if (obj == null) {
            return false;
        }



        if (obj.getId() != this.id) {
            return false;
        }

        return true;
    }
    public Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "3vAFY6Q5c@Y.yf8");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return connection;

    }


    public int ajouter(Enseignant enseignant) {

        int id = 0;

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO enseignants(nom, prenom, email, grade, dept_id) VALUES(?, ?, ?, ?, ?)");

            preparedStatement.setString(1, enseignant.getNom());

            preparedStatement.setString(2, enseignant.getPrenom());

            preparedStatement.setString(3, enseignant.getEmail());

            preparedStatement.setString(4, enseignant.getGrade());

            preparedStatement.setInt(5, enseignant.getDept().getId());

            preparedStatement.executeUpdate();


            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {

                id = resultSet.getInt(1);

            }


            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return id;

    }


    public boolean supprimer(int id) {

        boolean status = false;

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM enseignants WHERE id = ?");

            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();

            if (result == 1) {

                status = true;

            }


            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return status;

    }


    public boolean modifier(Enseignant enseignant) {

        boolean status = false;

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE enseignants SET nom = ?, prenom = ?, email = ?, grade = ?, dept_id = ? WHERE id = ?");

            preparedStatement.setString(1, enseignant.getNom());

            preparedStatement.setString(2, enseignant.getPrenom());

            preparedStatement.setString(3, enseignant.getEmail());

            preparedStatement.setString(4, enseignant.getGrade());

            preparedStatement.setInt(5, enseignant.getDept().getId());

            preparedStatement.setInt(6, enseignant.getId());

            int result = preparedStatement.executeUpdate();

            if (result == 1) {

                status = true;

            }


            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return status;

    }


    public Enseignant getEnseignant(int id) {

        Enseignant enseignant = null;

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM enseignants WHERE id = ?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                enseignant = new Enseignant();

                enseignant.setId(resultSet.getInt("id"));

                enseignant.setNom(resultSet.getString("nom"));

                enseignant.setPrenom(resultSet.getString("prenom"));

                enseignant.setEmail(resultSet.getString("email"));

                enseignant.setGrade(resultSet.getString("grade"));


                Departement departementDao = new Departement();

                enseignant.setDept(departementDao.getDepartement(resultSet.getInt("dept_id")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enseignant;
    }


    public List<Enseignant> getAllEnseignants() {
        return null;
    }

    public void getfullname() {
        this.fullname=fullname;
    }
}

