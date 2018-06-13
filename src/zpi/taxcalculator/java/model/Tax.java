package model;

public class Tax {
    private float taxValue;
    private float taxValuePercent;

    public Tax(float taxValue, float taxValuePercent) {
        this.taxValue = taxValue;
        this.taxValuePercent = taxValuePercent;
    }

    public float getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(float taxValue) {
        this.taxValue = taxValue;
    }

    public float getTaxValuePercent() {
        return taxValuePercent;
    }

    public void setTaxValuePercent(float taxValuePercent) {
        this.taxValuePercent = taxValuePercent;
    }
}
