package zpi.taxcalculator.model;

public class Product {

    private String name;

    public enum ProductType {
        NO_TYPE, GROCERIES, PREPARED_FOOD, PRESCRIPTION_DRUG, NON_PRESCRIPTION_DRUG, CLOTHING, INTANGIBLES
    }

    private ProductType productType;
    private Float netPrice;

    public Product(String name, ProductType productType, Float netPrice) {
        this.productType = productType;
        this.netPrice = netPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Float getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Float netPrice) {
        this.netPrice = netPrice;
    }
}
