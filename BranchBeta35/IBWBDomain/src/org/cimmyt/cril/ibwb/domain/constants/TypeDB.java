/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain.constants;

/**
 *
 * @author Desarrollo
 */
public enum TypeDB {
    
    IWIS(0, "IWIS"),
    IMIS(1, "IMIS"),
    OTHER(2, "OTHER");
    
    private Integer type;
    private String nombre;
    
    private TypeDB (Integer type,
            String nombre){
        this.type = type;
        this.nombre = nombre;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
