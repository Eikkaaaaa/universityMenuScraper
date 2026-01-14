package com.eikka.universityMenuScraper.components;

public class Prices {

    private float students;
    private float researcher_students;
    private float staff;
    private float others;

    public Prices(){}

    public Prices(Prices prices){
        this.students = prices.getStudents();
        this.researcher_students = prices.getResearcherStudents();
        this.staff = prices.getStaff();
        this.others = prices.getOthers();
    }

    public void setStudents(float students) {
        this.students = students;
    }

    public void setResearcherStudents(float researcher_students) {
        this.researcher_students = researcher_students;
    }

    public void setStaff(float staff) {
        this.staff = staff;
    }

    public void setOthers(float others) {
        this.others = others;
    }

    public float getStudents() {
        return students;
    }

    public float getResearcherStudents() {
        return researcher_students;
    }

    public float getStaff() {
        return staff;
    }

    public float getOthers() {
        return others;
    }

    public void mapPrices(String clientele, float price){
        switch (clientele){
            case "students":
                this.students = price;
                break;
            case "researcher_students":
                this.researcher_students = price;
                break;
            case "staff":
                this.staff = price;
                break;
            case "others":
                this.others = price;
                break;
        }
    }

    @Override
    public String toString() {
        return "Prices{" +
                "students=" + students +
                ", researcher_students=" + researcher_students +
                ", personal=" + staff +
                ", others=" + others +
                '}';
    }
}
