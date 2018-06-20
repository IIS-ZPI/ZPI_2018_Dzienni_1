package zpi.taxcalculator.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class InvoiceGeneratorTest {

    Product product1 = new Product("Apple", Product.ProductType.GROCERIES,25f);
    Product product2 = new Product("Pizza", Product.ProductType.PREPARED_FOOD, 10f);
    Product product3 = new Product("Shirt", Product.ProductType.CLOTHING, 350f);
    Product product4 = new Product("Shirt", Product.ProductType.CLOTHING, 150f);

    TaxPolicy policy1 = new TaxPolicy(new TaxData("Alabama", 4, "b", "b", "e", "b", "b", "b"));
    TaxPolicy policy2 = new TaxPolicy(new TaxData("Massachusetts", 6, "e", "b", "e", "b", "e,>175", "e"));
    TaxPolicy policy3 = new TaxPolicy(new TaxData("Montana", 0, "n/a", "n/a", "n/a", "n/a", "n/a", "n/a"));


    List<Product> productList = new ArrayList<Product>();
    List<TaxPolicy> policyList = new ArrayList<TaxPolicy>();

    List<InvoiceEntry> invoice = new ArrayList<InvoiceEntry>();

    HashMap<String, InvoiceEntry> listOfInvoiceEntriesByState = new HashMap<String, InvoiceEntry>();

    @Before
    public void setUp() throws Exception {
        productList.add(product1);
        productList.add(product2);
        policyList.add(policy1);
        policyList.add(policy2);

    }

    @Test
    public void generateInvoiceReturningHashMap(){
        listOfInvoiceEntriesByState = InvoiceGenerator.generateInvoice(product1, policyList);
    }

    @Test
    public void generateInvoiceReturningHashMapShouldReturnCorrectNumberOfStates(){
        policyList.add(policy3);
        listOfInvoiceEntriesByState = InvoiceGenerator.generateInvoice(product1, policyList);
        assertThat(3, is(listOfInvoiceEntriesByState.size()));
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

    //calculating taxes tests
    @Test
    public void TaxCalculationsShouldReturnCorrectGrossPriceWithPriceOverTaxationThreshold(){
        productList.add(product3); //tax on clothing above 175$ - should calculate
        policyList.add(policy2);

        invoice = InvoiceGenerator.generateInvoice(productList, policyList);

        assertThat(371f,is(invoice.get(2).getGrossPrice())); //adding tax >175$
    }
    @Test
    public void TaxCalculationsShouldReturnCorrectGrossPriceWithoutAdditionalTax(){
        productList.add(product4); //tax on clothing below 175 - shouldn't calculate.
        policyList.add(policy2);
        invoice = InvoiceGenerator.generateInvoice(productList, policyList);
        assertThat(150f,is(invoice.get(2).getGrossPrice())); //not adding tax <175$

    }
    @Test
    public void TaxCalculationsShouldReturnCorrectGrossPriceWithoutAnyTaxesOnState(){
        productList.add(product1);

        policyList.add(policy3);
        invoice = InvoiceGenerator.generateInvoice(productList, policyList);

        assertThat(25f, is(invoice.get(2).getGrossPrice())); //montana is without taxes - gross without changes

    }

}