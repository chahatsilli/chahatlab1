package com.example.lab1chahat;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.*;


public class HelloController implements Initializable {
    @FXML
    private TableView<dental_appoint> tableView;
    @FXML
    private TableColumn<dental_appoint,Integer > id;
    @FXML
    private TableColumn<dental_appoint, String> paintentName;
    @FXML
    private TableColumn<dental_appoint,Integer> phone;
    @FXML
    private TableColumn<dental_appoint,Integer> payment;
    ObservableList<dental_appoint> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<dental_appoint,Integer>("id"));
        paintentName.setCellValueFactory(new
                PropertyValueFactory<dental_appoint,String>("paintentName"));
        phone.setCellValueFactory(new
                PropertyValueFactory<dental_appoint,Integer>("phone"));
        payment.setCellValueFactory(new
                PropertyValueFactory<dental_appoint,Integer>("payment"));
        tableView.setItems(list);
    }
    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }
    public void populateTable() {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab1chahat";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM dental_appoint";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String paintentName = resultSet.getString("paintentName");
                int phone = resultSet.getInt("phone");
                int payment= resultSet.getInt("payment");
                tableView.getItems().add(new dental_appoint(id, paintentName, phone,
                        payment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}