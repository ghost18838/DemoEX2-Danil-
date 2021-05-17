package ru.sapteh.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.model.Manufacturer;
import ru.sapteh.model.Product;
import ru.sapteh.service.ManufacturerService;
import ru.sapteh.service.ProductService;

import java.io.IOException;

public class VtoroeOknoController {
    private String strochka;
    private String strochka2;
    private String strochka3;
    private String strochka4;
    @FXML
    private TextField textFieldTitle;

    @FXML
    private TextField textFieldCost;

    @FXML
    private TextField textFieldMain;

    @FXML
    private TextField textFieldActive;

    @FXML
    private ComboBox<Manufacturer> comboBoxCreate;

    @FXML
    private Button buttonCreate;

    @FXML
    private Button buttonExit;

    @FXML
    private Label lableCreate;


    public void initialize(){
        buttonExit.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Great Britan");
            stage.setScene(new Scene(root));
            stage.show();
            buttonExit.getScene().getWindow().hide();
        });
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        ManufacturerService manufacturerService = new ManufacturerService(sessionFactory);
        ObservableList<Manufacturer> manufacturers = FXCollections.observableArrayList(manufacturerService.readByAll());
        comboBoxCreate.setItems(manufacturers);
        buttonCreate.setOnAction(actionEvent -> {
            strochka = textFieldTitle.getText();
            strochka2 = textFieldCost.getText();
            strochka3 = textFieldMain.getText();
            strochka4 = textFieldActive.getText();
            ProductService productService = new ProductService(sessionFactory);
            productService.create(new Product(strochka,Double.parseDouble(strochka2) ,strochka3,Integer.parseInt(strochka4),comboBoxCreate.getValue()));
            lableCreate.setText("Вы создали новую запись");



        });










    }
}
