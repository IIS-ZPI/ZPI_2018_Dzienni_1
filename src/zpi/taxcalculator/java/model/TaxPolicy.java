package model;

import java.util.regex.Pattern;

public class TaxPolicy {
    private final TaxData taxData;

    public TaxPolicy(TaxData taxData) {
        this.taxData = taxData;
    }

    public Tax calculateTax(Product product) {
        Product.ProductType productType = product.getProductType();
        float productNetPrice = product.getNetPrice();

        Tax tax = new Tax();
        float parsedTaxValuePercent;

        switch (productType) {
            case NO_TYPE:
                tax.setTaxValuePercent(taxData.getBaseTax());
                tax.setTaxValue(product.getNetPrice() * taxData.getBaseTax());
                break;
            case GROCERIES:
                parsedTaxValuePercent = parseFloat(taxData.getGroceriesTax(), productNetPrice);
                tax.setTaxValuePercent(parsedTaxValuePercent);
                tax.setTaxValue(product.getNetPrice() * parsedTaxValuePercent);
            case PREPARED_FOOD:
                parsedTaxValuePercent = parseFloat(taxData.getFoodTax(), productNetPrice);
                tax.setTaxValuePercent(parsedTaxValuePercent);
                tax.setTaxValue(product.getNetPrice() * parsedTaxValuePercent);
            case PRESCRIPTION_DRUG:
                parsedTaxValuePercent = parseFloat(taxData.getPrescriptionDrugTax(), productNetPrice);
                tax.setTaxValuePercent(parsedTaxValuePercent);
                tax.setTaxValue(product.getNetPrice() * parsedTaxValuePercent);
            case NON_PRESCRIPTION_DRUG:
                parsedTaxValuePercent = parseFloat(taxData.getNonPrescriptionDrugTax(), productNetPrice);
                tax.setTaxValuePercent(parsedTaxValuePercent);
                tax.setTaxValue(product.getNetPrice() * parsedTaxValuePercent);
            case CLOTHING:
                parsedTaxValuePercent = parseFloat(taxData.getClothingTax(), productNetPrice);
                tax.setTaxValuePercent(parsedTaxValuePercent);
                tax.setTaxValue(product.getNetPrice() * parsedTaxValuePercent);
            case INTANGIBLES:
                parsedTaxValuePercent = parseFloat(taxData.getIntangiblesTax(), productNetPrice);
                tax.setTaxValuePercent(parsedTaxValuePercent);
                tax.setTaxValue(product.getNetPrice() * parsedTaxValuePercent);
        }
        return tax;
    }

    private float parseFloat(String taxString, Float productNetPrice) {
        Pattern taxThresholdPattern = Pattern.compile("[be],>\\d+");
        Pattern floatPattern = Pattern.compile("^\\d*\\.?\\d*$");

        if (taxString.equals("b")) {
            return taxData.getBaseTax();
        }
        else if (taxString.equals("?")) {
            return 0;
        }
        else if (taxString.equals("n/a")) {
            return 0;
        }
        else if (floatPattern.matcher(taxString).matches()) {
            return Float.parseFloat(taxString);
        }
        else if (taxThresholdPattern.matcher(taxString).matches()) {
            //[0] == e,b [1] ==  >value || <value
            String[] splitString = taxString.split(",");
            char typeBeyondThreshold = splitString[0].charAt(0);
            char sign = splitString[1].charAt(0);
            float thresholdValue = Float.parseFloat(splitString[1].substring(1));

            //TODO: Implement other cases handling (for cases currently nonexistent in our data file)
            if (typeBeyondThreshold == 'e') {
                if (sign == '>') {
                    if (productNetPrice < thresholdValue) {
                        return 0;
                    }
                    else return taxData.getBaseTax();
                }
                else {
                    if (productNetPrice > thresholdValue) {
                        return 0;
                    }
                    else return taxData.getBaseTax();
                }
            }
            else throw new UnsupportedOperationException();
        }
        else throw new IllegalArgumentException();
    }
}
