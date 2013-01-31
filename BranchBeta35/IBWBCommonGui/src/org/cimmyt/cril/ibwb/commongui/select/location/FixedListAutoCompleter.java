/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui.select.location;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.cimmyt.cril.ibwb.commongui.AutoCompleter;

/**
 *
 * @author TMSANCHEZ
 */
public class FixedListAutoCompleter extends AutoCompleter {

    private List<String> values = new ArrayList<String>();

    public FixedListAutoCompleter(JTextComponent comp) {
        super(comp);
        initList();
    }

    @Override
    protected boolean updateListData() {
        boolean result = true;
        String value = textComp.getText();
        List<String> listValue = null;

        result = value != null && !value.isEmpty();
        if (result) {
            listValue = new ArrayList<String>();
            for (String text : values) {
                if (text.toUpperCase().startsWith(value.toUpperCase())) {
                    listValue.add(text);
                }
            }
            list.setListData(listValue.toArray());
        }

        return result;
    }

    @Override
    protected void acceptedListItem(String selected) {
        if (selected == null) {
            return;
        }
        
        textComp.setText(selected);

    }

    private void initList() {
        values.add("Afghanistan");
        values.add("Albania");
        values.add("Algeria");
        values.add("American Samoa");
        values.add("Andorra");
        values.add("Angola");
        values.add("Antarctica");
        values.add("Antigua and Barbuda");
        values.add("Argentina");
        values.add("Armenia");
        values.add("Australia");
        values.add("Austria");
        values.add("Azerbaijan");
        values.add("Bahamas");
        values.add("Bahrain");
        values.add("Bangladesh");
        values.add("Barbados");
        values.add("Belgium");
        values.add("BELGIUM-LUX");
        values.add("Belize");
        values.add("Benin");
        values.add("Bermuda");
        values.add("Bhutan");
        values.add("Bolivia");
        values.add("Bosnia and Herzegovina");
        values.add("Botswana");
        values.add("Alaska");
        values.add("Bouvet Island");
        values.add("Brazil");
        values.add("British Indian Ocean Territory");
        values.add("Brunei Darussalam");
        values.add("Bulgaria");
        values.add("Myanmar");
        values.add("Burundi");
        values.add("Belarus");
        values.add("Cameroon");
        values.add("Canada");
        values.add("Canton-End Is");
        values.add("Cape Verde");
        values.add("Cayman Islands");
        values.add("Central African Republic");
        values.add("Chad");
        values.add("Chile");
        values.add("China");
        values.add("China Exc Taiwan");
        values.add("Christmas Island");
        values.add("Cocos (Keeling) Islands");
        values.add("Colombia");
        values.add("Comoros");
        values.add("Congo");
        values.add("Cook Islands");
        values.add("Costa Rica");
        values.add("Cuba");
        values.add("Cyprus");
        values.add("Czech Republic");
        values.add("Czechoslovakia");
        values.add("Denmark");
        values.add("Djibouti");
        values.add("Dominica");
        values.add("Dominican Republic");
        values.add("Dronning Maud La");
        values.add("Timor-Leste");
        values.add("Ecuador");
        values.add("Egypt");
        values.add("El Salvador");
        values.add("Equatorial Guinea");
        values.add("Eritrea");
        values.add("Estonia");
        values.add("Ethiopia");
        values.add("Faroe Islands");

    }
}
