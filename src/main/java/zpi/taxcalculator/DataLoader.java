package zpi.taxcalculator;

import zpi.taxcalculator.model.TaxData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

public class DataLoader {
    private static final byte DATA_PER_STATE = 8;
    private Scanner scanner;
    private String fileName;
    private Map<String, TaxData> data = new HashMap<>();

    public DataLoader(String fileName) {
        this.fileName = fileName;
        try {
            scanner = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<String, TaxData> getData() {
        String[] fileLine;
        while (scanner.hasNextLine()) {
            TaxData stateTaxData = convertFileLineToTaxDataObject(scanner.nextLine());
            data.put(stateTaxData.getState(), stateTaxData);
        }
        return data;
    }


    private TaxData convertFileLineToTaxDataObject(String line) {
        String[] splitLine = line.split("\t");

        //Data validity check
        if (splitLine.length != DATA_PER_STATE) {
            throw new IllegalArgumentException();
        }
        Pattern taxThresholdPattern = Pattern.compile("[be],>\\d+");
        Pattern floatPattern = Pattern.compile("^\\d*\\.?\\d*$");
        //Starting from base tax, we ignore state name checking
        for (int i = 1 ; i < splitLine.length ; i++) {
            if (!(splitLine[i].equals("b") || splitLine[i].equals("e") || splitLine[i].equals("n/a")
                    || splitLine[i].equals("?") || taxThresholdPattern.matcher(splitLine[i]).matches()
                    || floatPattern.matcher(splitLine[i]).matches())) {
                throw new IllegalArgumentException(splitLine[i]);
            }
        }
        //If data is correct, create TaxData object and return it
       return new TaxData(splitLine[0], Float.parseFloat(splitLine[1]), splitLine[2], splitLine[3], splitLine[4], splitLine[5], splitLine[6], splitLine[7]);
    }
}
