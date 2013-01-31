/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui;

import javax.swing.text.JTextComponent;

/**
 *
 * @author TMSANCHEZ
 */
public class LocationAutocompleter extends AutoCompleter {

    public LocationAutocompleter(JTextComponent comp) {
        super(comp);
    }
    @Override
    protected boolean updateListData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void acceptedListItem(String selected) {
       if (selected == null) {
            return;
        }
        
        textComp.setText(selected);
    }
    
}
