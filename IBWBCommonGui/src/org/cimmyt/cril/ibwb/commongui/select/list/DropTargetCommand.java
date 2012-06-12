/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui.select.list;

/**
 * Custom functionality when an Object is dropped in a list
 * @author TMSANCHEZ
 */
public interface DropTargetCommand {
    
    /**
     * 
     * @param droppedText 
     */
    public void onDropExecute(String droppedText);
    
}
