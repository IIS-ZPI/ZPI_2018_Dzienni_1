package zpi.taxcalculator.model;

public class TaxData {

    private String state;

    private float baseTax;
    private String groceriesTax;
    private String foodTax;
    private String prescriptionDrugTax;
    private String nonPrescriptionDrugTax;
    private String clothingTax;
    private String intangiblesTax;

    public TaxData(String state, float baseTax, String groceriesTax, String foodTax, String prescriptionDrugTax, String nonPrescriptionDrugTax, String clothingTax, String intangiblesTax) {
        this.state = state;
        this.baseTax = baseTax;
        this.groceriesTax = groceriesTax;
        this.foodTax = foodTax;
        this.prescriptionDrugTax = prescriptionDrugTax;
        this.nonPrescriptionDrugTax = nonPrescriptionDrugTax;
        this.clothingTax = clothingTax;
        this.intangiblesTax = intangiblesTax;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getBaseTax() {
        return baseTax;
    }

    public void setBaseTax(float baseTax) {
        this.baseTax = baseTax;
    }

    public String getGroceriesTax() {
        return groceriesTax;
    }

    public void setGroceriesTax(String groceriesTax) {
        this.groceriesTax = groceriesTax;
    }

    public String getFoodTax() {
        return foodTax;
    }

    public void setFoodTax(String foodTax) {
        this.foodTax = foodTax;
    }

    public String getPrescriptionDrugTax() {
        return prescriptionDrugTax;
    }

    public void setPrescriptionDrugTax(String prescriptionDrugTax) {
        this.prescriptionDrugTax = prescriptionDrugTax;
    }

    public String getNonPrescriptionDrugTax() {
        return nonPrescriptionDrugTax;
    }

    public void setNonPrescriptionDrugTax(String nonPrescriptionDrugTax) {
        this.nonPrescriptionDrugTax = nonPrescriptionDrugTax;
    }

    public String getClothingTax() {
        return clothingTax;
    }

    public void setClothingTax(String clothingTax) {
        this.clothingTax = clothingTax;
    }

    public String getIntangiblesTax() {
        return intangiblesTax;
    }

    public void setIntangiblesTax(String intangiblesTax) {
        this.intangiblesTax = intangiblesTax;
    }

    @Override public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        TaxData data = (TaxData) obj;
        return (data.baseTax == this.baseTax && data.clothingTax.equals(this.clothingTax) && data.foodTax.equals(this.foodTax)
                && data.groceriesTax.equals(this.groceriesTax) && data.intangiblesTax.equals(this.intangiblesTax)
                && data.nonPrescriptionDrugTax.equals(this.nonPrescriptionDrugTax) && data.prescriptionDrugTax.equals(
        this.prescriptionDrugTax) && data.state.equals(this.state));

    }

/*        return (data.baseTax == this.baseTax && data.clothingTax.equals(this.clothingTax) && data.foodTax.equals(this.foodTax)
            && data.groceriesTax.equals(this.groceriesTax) && data.intangiblesTax.equals(this.intangiblesTax)
            && data.nonPrescriptionDrugTax.equals(this.nonPrescriptionDrugTax) && data.prescriptionDrugTax.equals(
            this.prescriptionDrugTax) && data.state.equals(this.state));*/

}
