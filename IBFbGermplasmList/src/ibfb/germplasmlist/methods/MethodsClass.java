package ibfb.germplasmlist.methods;

import java.util.ArrayList;

public class MethodsClass {

    int convention = 0;  //0=CIMMYT WHEAT,  1= CIMMYT MAIZE,   2=IRRI RICE
    int method=0;

    public void setConvention(int conv) {
        this.convention = conv;
    }

     public void setMethod(int methodIndex) {
        this.method=methodIndex;
    }
    
    
    public ArrayList<String> giveMeDataDerivative(String seed, int samples) {
        ArrayList<String> data = new ArrayList<String>(); 
        String newSeed = "";

        switch (convention) {
             case 0:
                
                if (samples == 0) {
                    newSeed = seed + "-" + samples;
                    data.add(newSeed);
                }

                if (samples < 0) {
                    newSeed = seed + "-0" + Math.abs(samples);
                    data.add(newSeed);
                }


                if (samples > 0) {
                    for (int i = 0; i < samples; i++) {
                        newSeed = seed + "-" + (i + 1);
                        data.add(newSeed);
                    }
                }

                break;


            case 1:

                  newSeed=seed+"-"+samples;  
                  data.add(newSeed);
                
                
                break;


            case 2:

                  String nuevaSeed=seed+"-"+samples;  
                  data.add(nuevaSeed);
                
                
                break;

        }


        return data;
    }

    public String giveCross(String femalePedigree, String malePedigree) {
           
        String cross="";
        
        switch (convention) {
            case 0:
               
     
                   cross=femalePedigree+"/"+malePedigree;
        
                
  
                break;


            case 1:

                 
                cross=femalePedigree+"/"+malePedigree;
                
                break;


            case 2:

                  
                cross=femalePedigree+"/"+malePedigree;
                
                break;

        }


        return cross;
    }

   
}
