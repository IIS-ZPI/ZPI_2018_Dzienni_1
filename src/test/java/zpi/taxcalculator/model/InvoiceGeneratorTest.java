package zpi.taxcalculator.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InvoiceGeneratorTest {

    Product product1 = new Product(Product.ProductType.GROCERIES,25f);
    Product product2 = new Product(Product.ProductType.PREPARED_FOOD, 10f);
   // TaxPolicy policy1 = new TaxPolicy(new TaxData("Alabama",4,"b","b","b","b","b","b"));
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
}