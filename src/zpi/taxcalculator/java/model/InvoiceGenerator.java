package model;

import java.util.ArrayList;
import java.util.List;

public class InvoiceGenerator {
    public static List<InvoiceEntry> generateInvoice(List<Product> productList, List<TaxPolicy> taxPolicyList) {
        List<InvoiceEntry> invoiceEntryList = new ArrayList<>();

        if (productList.size() != taxPolicyList.size()) {
            throw new IllegalArgumentException("productList size does not equal taxPolicyList size");
        }

        for (int i = 0; i < productList.size() ; i++) {
            Product product = productList.get(i);
            Tax tax = taxPolicyList.get(i).calculateTax(product);
            Float grossPrice = product.getNetPrice() + tax.getTaxValue();
            invoiceEntryList.add(new InvoiceEntry(productList.get(i), tax, grossPrice));
        }
        return invoiceEntryList;
    }
}
