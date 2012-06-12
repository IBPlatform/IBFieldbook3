/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.domain.core;

import java.io.File;
import java.io.Serializable;

/**
 * Java Bean do define a Design
 * @author TMSANCHEZ
 */
public class DesignBean implements Serializable{
    
   /**
     * Trial number
     */ 
   private Integer trialNumber;
   /**
    * Design  name
    */
   private String design;
   /**
    * Number of replications
    */
   private Integer replications;
   /**
    * Block size 
    */
   private Integer blockSize;
   /**
    * Block per Replicate
    */
   private Integer blocksPerReplicate;
   
   /*
    * file name for user defined design
    */
   private File userDefinedDesign;

    
   

   /**
    * Default Constructor
    */
    public DesignBean() {
    }

   /**
    * Full Constructor
    * @param trialNumber
    * @param design
    * @param replications
    * @param blockSize
    * @param bloksPerReplicate 
    */
    public DesignBean(Integer trialNumber, String design, Integer replications, Integer blockSize, Integer bloksPerReplicate) {
        this.trialNumber = trialNumber;
        this.design = design;
        this.replications = replications;
        this.blockSize = blockSize;
        this.blocksPerReplicate = bloksPerReplicate;
    }

     public DesignBean(Integer trialNumber, String design, Integer replications, Integer blockSize, Integer bloksPerReplicate, File userDefinedDesign) {
        this(trialNumber, design, replications, blockSize, bloksPerReplicate);
        this.userDefinedDesign = userDefinedDesign;
        
    }
     
    public Integer getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(Integer blockSize) {
        this.blockSize = blockSize;
    }

    public Integer getBlocksPerReplicate() {
        return blocksPerReplicate;
    }

    public void setBlocksPerReplicate(Integer bloksPerReplicate) {
        this.blocksPerReplicate = bloksPerReplicate;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public Integer getReplications() {
        return replications;
    }

    public void setReplications(Integer replications) {
        this.replications = replications;
    }

    public Integer getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(Integer trialNumber) {
        this.trialNumber = trialNumber;
    }

    public File getUserDefinedDesign() {
        return userDefinedDesign;
    }

    public void setUserDefinedDesign(File userDefinedDesign) {
        this.userDefinedDesign = userDefinedDesign;
    }
    
   /**
     * Is a valid Design?, it checks all fields are filled correctly
     * @return <code>true</code> if all values are filled, <code>false</code> if not
     */
   public boolean isValidDesign() {
       boolean isValidDesign = false;
       
       if (trialNumber != null && design != null && replications != null && blockSize != null && blocksPerReplicate != null) {
           isValidDesign = true;
       }
       
       return isValidDesign;
   }

   
}
