package zpi.taxcalculator.controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import zpi.taxcalculator.DataLoader;
import zpi.taxcalculator.ProductLoader;
import zpi.taxcalculator.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RootLayoutController {
    private Map<String, TaxData> taxDatasMap;
    private Map<String, Product> productsMap;
    private List<Product> productList;
    private List<TaxPolicy> taxPolicyList;
    private Map<String,InvoiceEntry> choosenProductTaxDataMap = new HashMap<>();
    private List<String> categories;


    @FXML
    private ComboBox<String> comboBoxProducts;

    @FXML
    private ComboBox<String> comboBoxCategories;

    @FXML
    private Label labelPrice;

    @FXML
    private TextField sellingPriceTextField;

    @FXML
    private TableView table;

    @FXML
    private Button buttonCalculate;

    @FXML
    private TableColumn<TableViewContainer,String> stateColumn;
    @FXML
    private TableColumn<TableViewContainer,Float> nettoColumn;
    @FXML
    private TableColumn<TableViewContainer,Float> bruttoColumn;
    @FXML
    private TableColumn<TableViewContainer,Float> profitColumn;


    @FXML
    public void initialize() {
        DataLoader dataLoader = new DataLoader( "data/data.tsv");
        ProductLoader productLoader = new ProductLoader( "data/product_list.csv");
        productList = productLoader.getData();
        taxDatasMap = dataLoader.getData();
        productsMap = new HashMap<>();
        categories = new ArrayList<>();

        for(Product product : productList) {
            productsMap.put(product.getName(), product);
            if(!categories.contains(product.getProductType().toString()))
                categories.add(product.getProductType().toString());
        }

        comboBoxCategories.setItems(FXCollections.observableList(categories));
        comboBoxCategories.getSelectionModel().selectFirst();

        comboBoxProducts.setItems(FXCollections.observableList(getComboBoxProductsListByCategory(comboBoxCategories.getSelectionModel().getSelectedItem())));
        comboBoxProducts.getSelectionModel().selectFirst();

        comboBoxCategories.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                comboBoxProducts.setItems(FXCollections.observableList(getComboBoxProductsListByCategory(t1)));
                comboBoxProducts.getSelectionModel().selectFirst();
            }
        });

        List<TaxData> taxDataList = new ArrayList<>(taxDatasMap.values());
        taxPolicyList = new ArrayList<>();
        for(TaxData taxdata : taxDataList){
            taxPolicyList.add(new TaxPolicy(taxdata));
        }
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("stateName"));
        nettoColumn.setCellValueFactory(new PropertyValueFactory<>("netPrice"));
        bruttoColumn.setCellValueFactory(new PropertyValueFactory<>("grossPrice"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));

        table.getColumns().setAll(stateColumn,nettoColumn,bruttoColumn,profitColumn);

    }

    @FXML
    public void calculate() {
        String sellingPriceTextFieldValue = sellingPriceTextField.getCharacters().toString();
        float sellingPrice = 0;
        if(!sellingPriceTextFieldValue.isEmpty())
            if (sellingPriceTextFieldValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                sellingPrice = Float.valueOf(sellingPriceTextFieldValue);
                if(sellingPrice<=0) sellingPrice=0;
            }
        Product choosenProduct =  productsMap.get(comboBoxProducts.getSelectionModel().getSelectedItem());
        choosenProductTaxDataMap = InvoiceGenerator.generateInvoice(choosenProduct,taxPolicyList);

        table.setItems(getProductTaxTableData(choosenProductTaxDataMap,sellingPrice));



    }

    private ObservableList<TableViewContainer> getProductTaxTableData(Map<String,InvoiceEntry> map, float sellingPrice){
        ObservableList<TableViewContainer> tableViewContainerObservableList = FXCollections.observableArrayList();
        if(sellingPrice!=0) {
            for (String stateName : map.keySet()) {
                tableViewContainerObservableList.add(new TableViewContainer(
                        stateName,
                        map.get(stateName).getProduct().getNetPrice(),
                        map.get(stateName).getGrossPrice(),
                        sellingPrice - map.get(stateName).getGrossPrice()));
            }
        }
        else{
            for (String stateName : map.keySet()) {
                tableViewContainerObservableList.add(new TableViewContainer(
                        stateName,
                        map.get(stateName).getProduct().getNetPrice(),
                        map.get(stateName).getGrossPrice(),
                        sellingPrice));
            }
        }
        return tableViewContainerObservableList;
    }

    private List<String> getComboBoxProductsListByCategory(String category){
        List<String> comboBoxProductsList = new ArrayList<>();
        for(Product product : productList) {
            if(product.getProductType().toString().equals(category))
                comboBoxProductsList.add(product.getName());
        }
        return comboBoxProductsList;
    }
}
