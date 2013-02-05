/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import java.awt.Dialog;
import org.openide.DialogDescriptor;
import org.openide.util.NbBundle;

/**
 * DialogUtil - a utility for the Fieldbook Study Editor to display 
 *              Dialogs using the DialogDisplayer
 * @see {@link http://bits.netbeans.org/dev/javadoc/org-openide-dialogs/org/openide/DialogDisplayer.html DialogDisplayer}
 * @author mtrulat
 * @version 0.1
 */
public class DialogUtil {

    public DialogUtil() {
    }

    private static DialogDisplayer getDialogDisplayer() {
        return DialogDisplayer.getDefault();
    }

    /* Display message in a dialog box. 
     * Type of message to be displayed is assumed to be of INFORMATION_MESSAGE.
     * 
     * @param message - text to be displayed
     * 
     */
    public static void display(String message) {
        display(message, NotifyDescriptor.INFORMATION_MESSAGE);
    }

    public static void display(Class Aclass, String propertiesMessage) {
        display(getMessage(Aclass, propertiesMessage));
    }

    /* Display message of type messageType in a dialog box. 
     * 
     * @param message - the text to be displayed
     * @param messageType - the type of message to be displayed
     * 
     */
    public static void display(String message, int messageType) {
        NotifyDescriptor.Message d = new NotifyDescriptor.Message(message, messageType);
        getDialogDisplayer().notify(d);

    }

    /* Display message of type INFO in a dialog box. 
     * 
     * @param message - information text to be displayed
     */
    public static void displayInfo(String message) {
        display(message, NotifyDescriptor.INFORMATION_MESSAGE);
    }

    public static void displayInfo(Class Aclass, String propertiesMessage) {
        display(getMessage(Aclass, propertiesMessage), NotifyDescriptor.INFORMATION_MESSAGE);
    }

    /* Display message of type ERROR in a dialog box. 
     * 
     * @param message - error text to be displayed
     */
    public static void displayError(String message) {
        display(message, NotifyDescriptor.ERROR_MESSAGE);
    }

    public static void displayError(Class Aclass, String propertiesMessage) {
        display(getMessage(Aclass, propertiesMessage), NotifyDescriptor.ERROR_MESSAGE);
    }

    /* Display message of type WARNING in a dialog box. 
     * 
     * @param message - warning text to be displayed
     */
    public static void displayWarning(String message) {
        display(message, NotifyDescriptor.WARNING_MESSAGE);
    }

    public static void displayWarning(Class Aclass, String propertiesMessage) {
        display(getMessage(Aclass, propertiesMessage), NotifyDescriptor.WARNING_MESSAGE);
    }
    /*
     * Create Dialog
     * 
     * @param DialogDescriptor 
     */

    public static void createDialog(DialogDescriptor dialogDescriptor) {
        Dialog dialog = getDialogDisplayer().createDialog(dialogDescriptor);
        dialog.setVisible(true);
        dialog.toFront();
    }

    private static String getMessage(Class Aclass, String propertiesMessage) {
        return NbBundle.getMessage(Aclass, propertiesMessage);
    }

    public static void displayConfirmation(String message) {
        displayConfirmation(message, "Warning!", NotifyDescriptor.OK_CANCEL_OPTION);
    }

    public static void displayConfirmation(String message, int optionType) {
        displayConfirmation(message, "Warning!", optionType);
    }

    public static boolean displayConfirmation(String message, String title, int optionType) {
        NotifyDescriptor d = new NotifyDescriptor.Confirmation(message, title, optionType);
        Object value = getDialogDisplayer().notify(d);

        //if (getDialogDisplayer().notify(d) == NotifyDescriptor.OK_OPTION ) {
        if (value.equals(NotifyDescriptor.OK_OPTION) || value.equals(NotifyDescriptor.YES_OPTION)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static Object displayConfirmationDialog(String message, String title, int optionType) {
        NotifyDescriptor d = new NotifyDescriptor.Confirmation(message, title, optionType);
        Object value = getDialogDisplayer().notify(d);
        return value;
    }
}
