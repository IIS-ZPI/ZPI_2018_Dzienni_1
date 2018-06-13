package model;

public class Product {

    public enum ProductType {
        NO_TYPE, GROCERIES, PREPARED_FOOD, PRESCRIPTION_DRUG, NON_PRESCRIPTION_DRUG, CLOTHING, INTANGIBLES
    }

    private ProductType productType;
    private Float netPrice;

    public Product(ProductType productType, Float netPrice) {
        this.productType = productType;
        this.netPrice = netPrice;
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
