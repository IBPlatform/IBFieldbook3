package ibfb.nursery.maize;

import java.util.ArrayList;

public class MaizeMethods {

    private String cycle;
    private String nurseryName;
    private int entryNumber;
    private int polinizationNumber;
    private int plantsSelected;
    private boolean withParentheses;

    public String getCycle() {
        return cycle;
    }

    public boolean isWithParentheses() {
        return withParentheses;
    }

    public void setWithParentheses(boolean withParentheses) {
        this.withParentheses = withParentheses;
    }

    public int getPolinizationNumber() {
        return polinizationNumber;
    }

    public void setPolinizationNumber(int polinizationNumber) {
        this.polinizationNumber = polinizationNumber;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public int getPlantsSelected() {
        return plantsSelected;
    }

    public void setPlantsSelected(int plantsSelected) {
        this.plantsSelected = plantsSelected;
    }

    //Autofecundadas y mazorcas desgranadas individualmente (-)
    public ArrayList<MaizeEntry> procesaAutofecundadasIndividualmente(String gen, int selected) {
        ArrayList<MaizeEntry> data = new ArrayList<MaizeEntry>();
        String genealogia;

        for (int i = 0; i < selected; i++) {
            MaizeEntry maizeEntry = new MaizeEntry();

            if (isWithParentheses()) {
                genealogia = "(" + gen + ")" + "-" + (i + 1);
            } else {
                genealogia = gen + "-" + (i + 1);
            }

            String origen = getCycle() + "-" + getNurseryName() + "-" + getPolinizationNumber() + "-" + (i + 1);

            maizeEntry.setEntryNum(getEntryNumber());
            maizeEntry.setGenealogy(genealogia);
            maizeEntry.setOrigin(origen);
            maizeEntry.setSelected(1);

            System.out.println("GENEALOGIA: " + maizeEntry.getGenealogy());
            System.out.println("ORIGEN: " + maizeEntry.getOrigin());
            System.out.println("");

            data.add(maizeEntry);
        }
        return data;
    }

    //Autofecundadas y mazorcas bulked (-B)
    public ArrayList<MaizeEntry> procesaAutofecundadasBulked(String gen, int selected) {
        ArrayList<MaizeEntry> data = new ArrayList<MaizeEntry>();
        MaizeEntry maizeEntry = new MaizeEntry();

        String genealogia;

        if (isWithParentheses()) {
            genealogia = "(" + gen + ")" + "-B";
        } else {
            genealogia = gen + "-B";
        }
        String origen = getCycle() + "-" + getNurseryName() + "-" + getPolinizationNumber();

        maizeEntry.setEntryNum(getEntryNumber());
        maizeEntry.setGenealogy(genealogia);
        maizeEntry.setOrigin(origen);
        maizeEntry.setSelected(selected);

        System.out.println("GENEALOGIA: " + maizeEntry.getGenealogy());
        System.out.println("ORIGEN: " + maizeEntry.getOrigin());
        System.out.println("");

        data.add(maizeEntry);

        return data;
    }

    //sib-increase (-#)
    public ArrayList<MaizeEntry> procesaSibIncrease(String gen, int selected) {
        ArrayList<MaizeEntry> data = new ArrayList<MaizeEntry>();

        MaizeEntry maizeEntry = new MaizeEntry();
        String genealogia;

        if (isWithParentheses()) {
            genealogia = "(" + gen + ")" + "-#";
        } else {
            genealogia = gen + "-#";
        }
        String origen = getCycle() + "-" + getNurseryName() + "-" + getPolinizationNumber();

        maizeEntry.setEntryNum(getEntryNumber());
        maizeEntry.setGenealogy(genealogia);
        maizeEntry.setOrigin(origen);
        maizeEntry.setSelected(selected);

        System.out.println("GENEALOGIA: " + maizeEntry.getGenealogy());
        System.out.println("ORIGEN: " + maizeEntry.getOrigin());
        System.out.println("");

        data.add(maizeEntry);

        return data;
    }

    //Colchicinize
    public ArrayList<MaizeEntry> procesaColchicinize(String gen, int selected) {
        ArrayList<MaizeEntry> data = new ArrayList<MaizeEntry>();

        for (int i = 0; i < selected; i++) {
            MaizeEntry maizeEntry = new MaizeEntry();

            String genealogia = "(" + gen + ")" + "DH" + (i + 1);
            String origen = getCycle() + "-" + getNurseryName() + "-" + getPolinizationNumber() + "-" + (i + 1);

            maizeEntry.setEntryNum(getEntryNumber());
            maizeEntry.setGenealogy(genealogia);
            maizeEntry.setOrigin(origen);
            maizeEntry.setSelected(1);

            System.out.println("GENEALOGIA: " + maizeEntry.getGenealogy());
            System.out.println("ORIGEN: " + maizeEntry.getOrigin());
            System.out.println("");

            data.add(maizeEntry);
        }
        return data;
    }
}
