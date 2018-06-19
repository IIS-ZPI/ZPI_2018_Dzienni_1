package zpi.taxcalculator;

import zpi.taxcalculator.model.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class ProductLoader {
    private static final byte DATA_PER_PRODUCT = 3;
    private Scanner scanner;
    private URL fileName;
    private List<Product> data = new ArrayList<>();

    public ProductLoader(URL fileName) {
        this.fileName = fileName;
        try {
            scanner = new Scanner(fileName.openStream());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getData() {
        String[] fileLine;
        while (scanner.hasNextLine()) {
            Product product = convertFileLineToProductObject(scanner.nextLine());
            data.add(product);
        }
        return data;
    }


    private Product convertFileLineToProductObject(String line) {
        String[] splitLine = line.split(",");

        //Data validity check
        if (splitLine.length != DATA_PER_PRODUCT) {
            throw new IllegalArgumentException();
        }
        Pattern floatPattern = Pattern.compile("^\\d*\\.?\\d*$");
        //Starting from category, we ignore product name
        Product.ProductType productType;
        switch(splitLine[1].toLowerCase()) {
            case "groceries":
                productType = Product.ProductType.GROCERIES;
                break;
            case "prepared food":
                productType = Product.ProductType.PRESCRIPTION_DRUG;
                break;
            case "prescription drug":
                productType = Product.ProductType.PRESCRIPTION_DRUG;
                break;
            case "non-prescription drug":
                productType = Product.ProductType.NON_PRESCRIPTION_DRUG;
                break;
            case "clothing":
                productType = Product.ProductType.CLOTHING;
                break;
            case "intangibles":
                productType = Product.ProductType.INTANGIBLES;
                break;
            default:
                throw new IllegalArgumentException(splitLine[1]);
        }
        //Price check
        if (!floatPattern.matcher(splitLine[2]).matches()) {
            throw new IllegalArgumentException(splitLine[2]);
        }
        //If data is correct, create Product object and return it
        return new Product(splitLine[0], productType, Float.parseFloat(splitLine[2]));
    }
}
