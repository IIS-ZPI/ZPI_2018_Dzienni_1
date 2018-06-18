package zpi.taxcalculator.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import zpi.taxcalculator.model.TaxData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RootLayoutController {

    private Map<String, TaxData> data;
    private List<String> listOfStates;


    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField sellingPriceTextField;

    @FXML
    private TextField buyingPriceTextField;

    @FXML
    private TableView table;
    @FXML
    private Button buttonCalculate;

    @FXML
    public void initialize() {
//        Map<String, TaxData> data = new HashMap<>();
//        DataLoader dataLoader = new DataLoader( "data/data.tsv");
//        data = dataLoader.getData();
//        listOfStates = data.keySet().stream().collect(Collectors.toList());
//        comboBox.setItems(FXCollections.observableList(listOfStates));
//        comboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void calculate() {
        //Use regex in future for security
        Float sellingPrice = Float.valueOf(sellingPriceTextField.getCharacters().toString());
        Float buyingPrice = Float.valueOf(buyingPriceTextField.getCharacters().toString());
        System.out.println("Not implemented yet !");
        //TO-DO
    }



}
