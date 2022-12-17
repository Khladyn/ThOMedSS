package com.example.thomedss.data;

public class HealthModel {

    private String id;
    private String illnesses;
    private boolean smoker;
    private boolean drinker;
    private boolean regular_cycle;
    private boolean contraceptives;
    private boolean pregnant;
    private boolean miscarriage;

//CONSTRUCTORS
    public HealthModel
            (String id,
             String illnesses,
             boolean smoker,
             boolean drinker,
             boolean regular_cycle,
             boolean contraceptives,
             boolean pregnant,
             boolean miscarriage) {

        this.id = id;
        this.illnesses = illnesses;
        this.smoker = smoker;
        this.drinker = drinker;
        this.regular_cycle = regular_cycle;
        this.contraceptives = contraceptives;
        this.pregnant = pregnant;
        this.miscarriage = miscarriage;
    }

    public HealthModel(String id, String illnesses) {
        this.id = id;
        this.illnesses = illnesses;
    }

    public HealthModel(String id, boolean smoker, boolean drinker) {
        this.id = id;
        this.smoker = smoker;
        this.drinker = drinker;
    }

    public HealthModel(String id, boolean regular_cycle, boolean contraceptives, boolean pregnant, boolean miscarriage) {
        this.id = id;
        this.regular_cycle = regular_cycle;
        this.contraceptives = contraceptives;
        this.pregnant = pregnant;
        this.miscarriage = miscarriage;
    }

    public HealthModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIllnesses() {
        return illnesses;
    }

    public void setIllnesses(String illnesses) {
        this.illnesses = illnesses;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public boolean isDrinker() {
        return drinker;
    }

    public void setDrinker(boolean drinker) {
        this.drinker = drinker;
    }

    public boolean isRegular_cycle() {
        return regular_cycle;
    }

    public void setRegular_cycle(boolean regular_cycle) {
        this.regular_cycle = regular_cycle;
    }

    public boolean isContraceptives() {
        return contraceptives;
    }

    public void setContraceptives(boolean contraceptives) {
        this.contraceptives = contraceptives;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public boolean isMiscarriage() {
        return miscarriage;
    }

    public void setMiscarriage(boolean miscarriage) {
        this.miscarriage = miscarriage;
    }
}




