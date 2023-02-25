package ma.enset.javafx_tp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.javafx_tp.dao.implementation.CategoryRepositoryImpl;
import ma.enset.javafx_tp.dao.implementation.ProductRepositoryImpl;
import ma.enset.javafx_tp.entities.Category;
import ma.enset.javafx_tp.entities.Product;
import ma.enset.javafx_tp.service.CatalogService;
import ma.enset.javafx_tp.service.implementation.CatalogServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML private TextField textFieldName;
    @FXML private TextField textFieldRef;
    @FXML private TextField textFieldPrice;
    @FXML private ComboBox<Category> comboBoxCategory;
    @FXML private TextField textFieldSearch;
    @FXML private ListView<Product> listViewProducts;
    @FXML private TableView<Product> tableViewProducts;
    @FXML private TableColumn<Product, Long> tableColumnId;
    @FXML private TableColumn<Product, String> tableColumnName;
    @FXML private TableColumn<Product, String> tableColumnRef;
    @FXML private TableColumn<Product, Double> tableColumnPrice;
//    @FXML private TableColumn<Category, Product> tableColumnCategory;

    ObservableList<Product> productsData = FXCollections.observableArrayList();
    ObservableList<Category> categoriesData = FXCollections.observableArrayList();

    private CatalogService catalogService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        catalogService = new CatalogServiceImpl(new ProductRepositoryImpl(), new CategoryRepositoryImpl());
        comboBoxCategory.setItems(categoriesData);
        categoriesData.setAll(catalogService.findAllCategories());

        listViewProducts.setItems(productsData);
//        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        tableColumnRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
//        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        tableColumnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        loadData();
    }

    @FXML
    void onAddProduct() {
        Product product = new Product();
        product.setName(textFieldName.getText());
        product.setReference(textFieldRef.getText());
        product.setPrice(Double.parseDouble(textFieldPrice.getText()));
        product.setCategory(comboBoxCategory.getSelectionModel().getSelectedItem());

        this.catalogService.addProduct(product);
        loadData();
    }
    @FXML
    void onDeleteProduct() {

    }
    @FXML
    void onUpdateProduct() {

    }

    void loadData(){
        List<Product> allProducts = catalogService.findAllProducts();
        productsData.clear();
        productsData.addAll(allProducts);
    }



}
