package com.apps.medicine;

public class AddNewMedicine {
    String ID, MedicineName,MedicineDes,MedicineStat,MedicineWarnings,MedicineSideEffects,MedicineTradeName,MedicineDrugInteraction;

    public AddNewMedicine() {
    }

    public AddNewMedicine(String id, String medicineName, String medicineDes, String medicineStat, String medicineWarnings, String medicineSideEffects, String medicineTradeName, String medicineDrugInteraction) {
        ID=id;
        MedicineName = medicineName;
        MedicineDes = medicineDes;
        MedicineStat = medicineStat;
        MedicineWarnings = medicineWarnings;
        MedicineSideEffects = medicineSideEffects;
        MedicineTradeName = medicineTradeName;
        MedicineDrugInteraction = medicineDrugInteraction;
    }


    public String getMedicineName() {
        return MedicineName;
    }

    public void setMedicineName(String medicineName) {
        MedicineName = medicineName;
    }

    public String getMedicineDes() {
        return MedicineDes;
    }

    public void setMedicineDes(String medicineDes) {
        MedicineDes = medicineDes;
    }

    public String getMedicineStat() {
        return MedicineStat;
    }

    public void setMedicineStat(String medicineStat) {
        MedicineStat = medicineStat;
    }

    public String getMedicineWarnings() {
        return MedicineWarnings;
    }

    public void setMedicineWarnings(String medicineWarnings) {
        MedicineWarnings = medicineWarnings;
    }

    public String getMedicineSideEffects() {
        return MedicineSideEffects;
    }

    public void setMedicineSideEffects(String medicineSideEffects) {
        MedicineSideEffects = medicineSideEffects;
    }

    public String getMedicineTradeName() {
        return MedicineTradeName;
    }

    public void setMedicineTradeName(String medicineTradeName) {
        MedicineTradeName = medicineTradeName;
    }

    public String getMedicineDrugInteraction() {
        return MedicineDrugInteraction;
    }

    public void setMedicineDrugInteraction(String medicineDrugInteraction) {
        MedicineDrugInteraction = medicineDrugInteraction;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
