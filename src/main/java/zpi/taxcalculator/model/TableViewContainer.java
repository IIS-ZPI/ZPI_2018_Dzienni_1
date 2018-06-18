package zpi.taxcalculator.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableViewContainer {
    private final StringProperty stateName;
    private final FloatProperty netPrice;
    private final FloatProperty grossPrice;
    private final FloatProperty profit;

    public TableViewContainer(String stateName,float netPrice, float grossPrice,float profit){
        this.stateName = new SimpleStringProperty(stateName);
        this.netPrice = new SimpleFloatProperty(netPrice);
        this.grossPrice = new SimpleFloatProperty(grossPrice);
        this.profit = new SimpleFloatProperty(profit);
    }

    public String getStateName() {
        return stateName.get();
    }

    public void setStateName(String stateName) {
        this.stateName.set(stateName);
    }

    public StringProperty stateNameProperty() {
        return stateName;
    }

    public Float getNetPrice() {
        return netPrice.get();
    }

    public void setNetPrice(Float netPrice) {
        this.netPrice.set(netPrice);
    }

    public FloatProperty netPriceProperty() {
        return netPrice;
    }

    public Float getGrossPrice() {
        return grossPrice.get();
    }

    public void setGrossPrice(Float grossPrice) {
        this.grossPrice.set(grossPrice);
    }

    public FloatProperty grossPriceProperty() {
        return grossPrice;
    }

    public Float getProfit() {
        return profit.get();
    }

    public void setProfit(Float profit) {
        this.profit.set(profit);
    }

    public FloatProperty profitProperty() {
        return profit;
    }


}
