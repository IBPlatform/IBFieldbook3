/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import javax.swing.JTable;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.BindingListener;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
/**
 *
 * @author TMSANCHEZ
 */
@SuppressWarnings("unchecked")
public class TableBindingUtil {
     public static void createColumnsFromDB(Class<?> clazz, List list, JTable table, BindingListener bl) {
        BindingGroup myBindingGroup = new BindingGroup();
        JTableBinding tableBinding =
                SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, list, table);
        JTableBinding.ColumnBinding columnBinding;

        Field fields[] = clazz.getDeclaredFields();

        for (Field f : fields) {

            if (f.getModifiers() != (Modifier.PUBLIC + Modifier.STATIC + Modifier.FINAL)) {
                if (!f.getName().equals("serialVersionUID")) {

                    // include only primitive types and String
                    Class<?> clazzOfField = toWrapper(f.getType());

                    String name = f.getName();
                    if (clazzOfField == null || f.getName().toLowerCase().equals("id")) {
                        continue;
                    }
                    columnBinding = tableBinding.addColumnBinding(ELProperty.create("${" + name + "}"));
                    columnBinding.setColumnName(name);
                    columnBinding.setColumnClass(clazzOfField);
                }
            }
        }

        // observe changes to every single property
        myBindingGroup.addBindingListener(bl);
        myBindingGroup.addBinding(tableBinding);
        myBindingGroup.bind();
    }
     
@SuppressWarnings("unchecked")
    public static void createColumnsFromDB(Class<?> clazz, List list, JTable table, String propList, BindingListener bl) {
        BindingGroup myBindingGroup = new BindingGroup();
        JTableBinding tableBinding =
                SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, list, table);
        JTableBinding.ColumnBinding columnBinding;

        Field fields[] = clazz.getDeclaredFields();

        for (Field f : fields) {

            if (f.getModifiers() != (Modifier.PUBLIC + Modifier.STATIC + Modifier.FINAL)
                    && propList.contains(f.getName())) {
                if (!f.getName().equals("serialVersionUID")) {

                    // include only primitive types and String
                    Class<?> clazzOfField = toWrapper(f.getType());

                    String name = f.getName();
                    if (clazzOfField == null || f.getName().toLowerCase().equals("id")) {
                        continue;
                    }
                    columnBinding = tableBinding.addColumnBinding(ELProperty.create("${" + name + "}"));
                    columnBinding.setColumnName(name);
                    columnBinding.setColumnClass(clazzOfField);
                }
            }
        }


        // observe changes to every single property
        myBindingGroup.addBindingListener(bl);
        myBindingGroup.addBinding(tableBinding);
        myBindingGroup.bind();
    }

@SuppressWarnings("unchecked")
    public static void createColumnsFromDB(Class<?> clazz, List list, JTable table, String propList, String columnHeaders) {
        BindingGroup myBindingGroup = new BindingGroup();
        JTableBinding tableBinding =
                SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, list, table);
        JTableBinding.ColumnBinding columnBinding;

        HashMap<String,Field> fieldMaps =  new HashMap<String, Field>();
        Field fields[] = clazz.getDeclaredFields();

        for (Field f : fields) {
            fieldMaps.put(f.getName(), f);
        }

        String[] fieldList = propList.split(",");
        String[] headers = columnHeaders.split(",");
        int headerIndex = 0;

        for (String field : fieldList) {
            try {
                //Field f = clazz.getField(field);
                Field f = fieldMaps.get(field);
                if (field != null) {
                    // include only primitive types and String
                    Class<?> clazzOfField = toWrapper(f.getType());

                    String name = f.getName();
                    if (clazzOfField == null || f.getName().toLowerCase().equals("id")) {
                        continue;
                    }
                    columnBinding = tableBinding.addColumnBinding(ELProperty.create("${" + name + "}"));
                    columnBinding.setColumnName(headers[headerIndex]);
                    columnBinding.setColumnClass(clazzOfField);
                    columnBinding.setEditable(false);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            headerIndex++;
        }

        // observe changes to every single property
        myBindingGroup.addBinding(tableBinding);
        myBindingGroup.bind();
    }

    public static Class toWrapper(Class clazz) {
        if (Number.class.isAssignableFrom(clazz)) {
            return clazz;
        } else if (clazz.equals(int.class)) {
            return Integer.class;
        } else if (clazz.equals(long.class)) {
            return Long.class;
        } else if (clazz.equals(boolean.class)) {
            return Boolean.class;
        } else if (clazz.equals(float.class)) {
            return Float.class;
        } else if (clazz.equals(double.class)) {
            return Double.class;
        } else if (clazz.equals(short.class)) {
            return Short.class;
        } else if (clazz.equals(String.class)) {
            return String.class;
        } else {
            return null;
        }
    }
}
