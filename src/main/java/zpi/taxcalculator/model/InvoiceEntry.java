package zpi.taxcalculator.model;

public class InvoiceEntry {
    private Product product;
    private Tax tax;
    private float grossPrice;

    public InvoiceEntry(Product product, Tax tax, float grossPrice) {
        this.product = product;
        this.tax = tax;
        this.grossPrice = grossPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public float getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(float grossPrice) {
        this.grossPrice = grossPrice;
    }
}
