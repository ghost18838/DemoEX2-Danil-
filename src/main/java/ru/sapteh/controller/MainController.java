package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.ordering.antlr.Factory;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Product;
import ru.sapteh.service.ProductService;

import java.io.IOException;
import java.util.List;

public class MainController  {
    int probnik;
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private ObservableList<Product> products = FXCollections.observableArrayList();

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, Integer> columnId;

    @FXML
    private TableColumn<Product,String> titleColumn;

    @FXML
    private TableColumn<Product, Double> costColumn;

    @FXML
    private TableColumn<Product, String> descriptionColumn;

    @FXML
    private TableColumn<Product, String> imagePathColumn;

    @FXML
    private TableColumn<Product, Integer> activeColumn;

    @FXML
    private TableColumn<Product, String> manufacturerColumn;

    @FXML
    private ImageView imageView;

    @FXML
    private ComboBox<Integer> boxAction;

    @FXML
    private Pagination paginator;



    @FXML
    void createAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Vtoroe Okno.fxml"));
        stage.setTitle("Great Britan");
        stage.setScene(new Scene(root));
        stage.show();
        borderPane.getScene().getWindow().hide();


    }

    @FXML
    void deleteAction(ActionEvent event) {
        Product t1 = (tableView.getSelectionModel().selectedItemProperty().getValue());
        ProductService productService = new ProductService(factory);
        productService.delete(t1);
        products.remove(t1);
        comboboxinit();

    }

    @FXML
    void updateAction(ActionEvent event) {

    }

    public void initialize(){
        DAO<Product, Integer> productIntegerDAO=new ProductService(factory);
        products.addAll(productIntegerDAO.readByAll());
        columnId.setCellValueFactory(productIntegerCellDataFeatures ->
                new SimpleObjectProperty<>(productIntegerCellDataFeatures.getValue().getId()));
        titleColumn.setCellValueFactory(stringProductCellDataFeatures ->
                new SimpleObjectProperty<>(stringProductCellDataFeatures.getValue().getTitle()));
        costColumn.setCellValueFactory(productStringCellDataFeatures ->
                new SimpleObjectProperty<>(productStringCellDataFeatures.getValue().getCost()));
        descriptionColumn.setCellValueFactory(productStringCellDataFeatures ->
                new SimpleObjectProperty<>(productStringCellDataFeatures.getValue().getDescription()));
        imagePathColumn.setCellValueFactory(productStringCellDataFeatures ->
                new SimpleObjectProperty<>(productStringCellDataFeatures.getValue().getMainImagePath()));
        activeColumn.setCellValueFactory(productStringCellDataFeatures ->
                new SimpleObjectProperty<>(productStringCellDataFeatures.getValue().getIsActive()));
        manufacturerColumn.setCellValueFactory(productStringCellDataFeatures ->
                new SimpleObjectProperty<>(productStringCellDataFeatures.getValue().getManufacturer().getName()));
        tableView.setItems(products);
        tableView.getSelectionModel().selectedItemProperty().addListener((observableValue, product, t1) -> {

            if (t1!=null){
                imageView.setImage(new Image("\\"+t1.getMainImagePath()));

            }

        });

        comboboxinit();

    }

    public void comboboxinit(){
        boxAction.setItems(FXCollections.observableArrayList(10,50,200, products.size()));
        boxAction.setValue(products.size());
        paginator.setPageCount(1);
        boxAction.valueProperty().addListener((observableValue, integer, t1) -> {
            probnik=t1;

            if (probnik > products.size()) {
                probnik=products.size();
                boxAction.setValue(products.size());
            }
            paginator.setPageCount((int) Math.ceil((double) products.size()/probnik));
            paginator.setCurrentPageIndex(0);
            tableView.setItems(FXCollections.observableArrayList(products.subList(0,probnik)));
            paginator.currentPageIndexProperty().addListener((observableValue1, number, t11) -> {
                int formula=probnik*(t11.intValue()+1);
                if (paginator.getCurrentPageIndex() + 1 == paginator.getPageCount()) {
                    tableView.setItems(FXCollections.observableArrayList(products.subList(formula-probnik,products.size())));
                }else {
                    tableView.setItems(FXCollections.observableArrayList(products.subList(formula-probnik,formula)));
                }

            });

        });
    }

}
