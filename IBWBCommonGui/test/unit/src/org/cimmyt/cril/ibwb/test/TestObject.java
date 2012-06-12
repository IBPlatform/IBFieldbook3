/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.test;

import java.io.Serializable;

/**
 *
 * @author TMSANCHEZ
 */
public class TestObject implements Serializable {
    private Integer id;
    private String name;

    public TestObject() {
    }

    public TestObject(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        return id + " " + name;
    }
    
}
