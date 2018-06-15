package zpi.taxcalculator.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class InvoiceGeneratorTest {

    Product product1 = new Product("Apple", Product.ProductType.GROCERIES,25f);
    Product product2 = new Product("Pizza", Product.ProductType.PREPARED_FOOD, 10f);

    TaxPolicy policy1 = new TaxPolicy(new TaxData("Alabama", 4, "b", "b", "e", "b", "b", "b"));
    TaxPolicy policy2 = new TaxPolicy(new TaxData("Massachusetts", 6, "e", "b", "e", "b", "e,>175", "e"));

    List<Product> productList = new ArrayList<Product>();
    List<TaxPolicy> policyList = new ArrayList<TaxPolicy>();

    List<InvoiceEntry> invoice = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        productList.add(product1);
        productList.add(product2);
        policyList.add(policy1);
        policyList.add(policy2);

    }

    @Test
    public void generateInvoice() {
        invoice = InvoiceGenerator.generateInvoice(productList,policyList);

    }

    @Test(expected = IllegalArgumentException.class)
    public void generatingInvoiceWithDifferentSizedListsShouldThrowException(){
        productList.add(new Product("placeholder", Product.ProductType.GROCERIES, 5f));
        invoice = InvoiceGenerator.generateInvoice(productList, policyList);
    }

    @Test
    public void generateInvoiceShouldReturnCorrectAmountOfProductsOnInvoice(){
        invoice = InvoiceGenerator.generateInvoice(productList, policyList);
        assertThat(2,is(invoice.size()));
    }

}