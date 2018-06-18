package zpi.taxcalculator.model;

import java.util.Objects;

public class Product {

    private String name;

    public enum ProductType {
        NO_TYPE, GROCERIES, PREPARED_FOOD, PRESCRIPTION_DRUG, NON_PRESCRIPTION_DRUG, CLOTHING, INTANGIBLES
    }

    private ProductType productType;
    private Float netPrice;

    public Product(String name, ProductType productType, Float netPrice) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                productType == product.productType &&
                Objects.equals(netPrice, product.netPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, productType, netPrice);
    }
}
