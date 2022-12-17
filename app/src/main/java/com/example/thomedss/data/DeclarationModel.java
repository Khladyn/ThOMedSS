package com.example.thomedss.data;

import java.util.Date;

public class DeclarationModel {

    private String id;
    private static String declarationDate = "12/17/2022";
    private String purpose;
    private String placeOfVisit;
    private String temperature;
    private String fever;

    //SECTION 1
    private String feverDate;
    private String cough;
    private String coughDate;
    private String colds;
    private String coldsDate;
    private String throat;
    private String throatDate;
    private String shortnessBreath;
    private String shortnessBreathDate;
    private String difficultyBreath;
    private String difficultyBreathDate;
    private String diarrhea;
    private String diarrheaDate;
    private String rash;
    private String rashDate;
    private String lossTaste;
    private String lossTasteDate;
    private String lossSmell;
    private String lossSmellDate;

    //SECTION 2
    private String closedEnvironment;
    private String contactDate;
    private String sameHouse;
    private String sameDining;

    //SECTION 3
    private String diagnosed;
    private String diagnosedDate;

    //SECTION 4
    private String travelOutside;
    private String travelDate;
    private String arrivalDate;
    private String travelCountry;

    public DeclarationModel(String id, String declarationDate) {
        this.id = id;
        this.declarationDate = declarationDate;
    }

    public DeclarationModel
            (String id,
             String purpose,
             String placeOfVisit,
             String temperature,
             String fever,
             String feverDate,
             String cough,
             String coughDate,
             String colds,
             String coldsDate,
             String throat,
             String throatDate,
             String shortnessBreath,
             String shortnessBreathDate,
             String difficultyBreath,
             String difficultyBreathDate,
             String diarrhea,
             String diarrheaDate,
             String rash,
             String rashDate,
             String lossTaste,
             String lossTasteDate,
             String lossSmell,
             String lossSmellDate,
             String closedEnvironment,
             String contactDate,
             String sameHouse,
             String sameDining,
             String diagnosed,
             String diagnosedDate,
             String travelOutside,
             String travelDate,
             String arrivalDate,
             String travelCountry) {

        this.id = id;
        this.purpose = purpose;
        this.placeOfVisit = placeOfVisit;
        this.temperature = temperature;
        this.fever = fever;
        this.feverDate = feverDate;
        this.cough = cough;
        this.coughDate = coughDate;
        this.colds = colds;
        this.coldsDate = coldsDate;
        this.throat = throat;
        this.throatDate = throatDate;
        this.shortnessBreath = shortnessBreath;
        this.shortnessBreathDate = shortnessBreathDate;
        this.difficultyBreath = difficultyBreath;
        this.difficultyBreathDate = difficultyBreathDate;
        this.diarrhea = diarrhea;
        this.diarrheaDate = diarrheaDate;
        this.rash = rash;
        this.rashDate = rashDate;
        this.lossTaste = lossTaste;
        this.lossTasteDate = lossTasteDate;
        this.lossSmell = lossSmell;
        this.lossSmellDate = lossSmellDate;
        this.closedEnvironment = closedEnvironment;
        this.contactDate = contactDate;
        this.sameHouse = sameHouse;
        this.sameDining = sameDining;
        this.diagnosed = diagnosed;
        this.diagnosedDate = diagnosedDate;
        this.travelOutside = travelOutside;
        this.travelDate = travelDate;
        this.arrivalDate = arrivalDate;
        this.travelCountry = travelCountry;
    }

    public DeclarationModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(String declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPlaceOfVisit() {
        return placeOfVisit;
    }

    public void setPlaceOfVisit(String placeOfVisit) {
        this.placeOfVisit = placeOfVisit;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String isFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getFeverDate() {
        return feverDate;
    }

    public void setFeverDate(String feverDate) {
        this.feverDate = feverDate;
    }

    public String isCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getCoughDate() {
        return coughDate;
    }

    public void setCoughDate(String coughDate) {
        this.coughDate = coughDate;
    }

    public String isColds() {
        return colds;
    }

    public void setColds(String colds) {
        this.colds = colds;
    }

    public String getColdsDate() {
        return coldsDate;
    }

    public void setColdsDate(String coldsDate) {
        this.coldsDate = coldsDate;
    }

    public String isThroat() {
        return throat;
    }

    public void setThroat(String throat) {
        this.throat = throat;
    }

    public String getThroatDate() {
        return throatDate;
    }

    public void setThroatDate(String throatDate) {
        this.throatDate = throatDate;
    }

    public String isShortnessBreath() {
        return shortnessBreath;
    }

    public void setShortnessBreath(String shortnessBreath) {
        this.shortnessBreath = shortnessBreath;
    }

    public String getShortnessBreathDate() {
        return shortnessBreathDate;
    }

    public void setShortnessBreathDate(String shortnessBreathDate) {
        this.shortnessBreathDate = shortnessBreathDate;
    }

    public String isDifficultyBreath() {
        return difficultyBreath;
    }

    public void setDifficultyBreath(String difficultyBreath) {
        this.difficultyBreath = difficultyBreath;
    }

    public String getDifficultyBreathDate() {
        return difficultyBreathDate;
    }

    public void setDifficultyBreathDate(String difficultyBreathDate) {
        this.difficultyBreathDate = difficultyBreathDate;
    }

    public String isDiarrhea() {
        return diarrhea;
    }

    public void setDiarrhea(String diarrhea) {
        this.diarrhea = diarrhea;
    }

    public String getDiarrheaDate() {
        return diarrheaDate;
    }

    public void setDiarrheaDate(String diarrheaDate) {
        this.diarrheaDate = diarrheaDate;
    }

    public String isRash() {
        return rash;
    }

    public void setRash(String rash) {
        this.rash = rash;
    }

    public String getRashDate() {
        return rashDate;
    }

    public void setRashDate(String rashDate) {
        this.rashDate = rashDate;
    }

    public String isLossTaste() {
        return lossTaste;
    }

    public void setLossTaste(String lossTaste) {
        this.lossTaste = lossTaste;
    }

    public String getLossTasteDate() {
        return lossTasteDate;
    }

    public void setLossTasteDate(String lossTasteDate) {
        this.lossTasteDate = lossTasteDate;
    }

    public String isLossSmell() {
        return lossSmell;
    }

    public void setLossSmell(String lossSmell) {
        this.lossSmell = lossSmell;
    }

    public String getLossSmellDate() {
        return lossSmellDate;
    }

    public void setLossSmellDate(String lossSmellDate) {
        this.lossSmellDate = lossSmellDate;
    }

    public String isClosedEnvironment() {
        return closedEnvironment;
    }

    public void setClosedEnvironment(String closedEnvironment) {
        this.closedEnvironment = closedEnvironment;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setContactDate(String contactDate) {
        this.contactDate = contactDate;
    }

    public String isSameHouse() {
        return sameHouse;
    }

    public void setSameHouse(String sameHouse) {
        this.sameHouse = sameHouse;
    }

    public String isSameDining() {
        return sameDining;
    }

    public void setSameDining(String sameDining) {
        this.sameDining = sameDining;
    }

    public String isDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(String diagnosed) {
        this.diagnosed = diagnosed;
    }

    public String getDiagnosedDate() {
        return diagnosedDate;
    }

    public void setDiagnosedDate(String diagnosedDate) {
        this.diagnosedDate = diagnosedDate;
    }

    public String isTravelOutside() {
        return travelOutside;
    }

    public void setTravelOutside(String travelOutside) {
        this.travelOutside = travelOutside;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getTravelCountry() {
        return travelCountry;
    }

    public void setTravelCountry(String travelCountry) {
        this.travelCountry = travelCountry;
    }

}
