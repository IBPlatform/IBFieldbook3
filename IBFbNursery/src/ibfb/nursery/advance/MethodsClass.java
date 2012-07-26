package ibfb.nursery.advance;

import java.util.ArrayList;

public class MethodsClass {
    public static final int CIMMYT_WHEAT = 0;
    public static final int CIMMYT_MAIZE = 1;
    public static final int OTHER_CROPS = 2;
    
    int convention = 0;  //0=CIMMYT WHEAT,  1= CIMMYT MAIZE,   2=OTHER
    
    /**
     * Suffix to add 
     */
    private String suffix;

    public void setConvention(int conv) {
        this.convention = conv;
    }

    public ArrayList<String> giveMeDataDerivative(String seed, int samples) {
        ArrayList<String> data = new ArrayList<String>();
        String newSeed = "";

        switch (convention) {
            case CIMMYT_WHEAT:
                
                if (samples == 0) {
                    newSeed = seed + "-" + samples + suffix;
                    data.add(newSeed);
                }

                if (samples < 0) {
                    
                    
                    if(seed.endsWith("T")){
                        
                   // newSeed =  "0" + Math.abs(samples)+"TOP" + suffix;
                    newSeed = seed + "-0" + Math.abs(samples)+"TOP" + suffix;

                    data.add(newSeed);   
                    }else{
                    
                    newSeed = seed + "-0" + Math.abs(samples) + suffix;
                    data.add(newSeed);
                    }
                    
                    
                }


                if (samples > 0) {
                    for (int i = 0; i < samples; i++) {
                        newSeed = seed + "-" + (i + 1) + suffix;
                        data.add(newSeed);
                    }
                }

                break;

            case CIMMYT_MAIZE:

                newSeed = seed + "-" + samples;
                data.add(newSeed);


                break;


            case OTHER_CROPS:

                for (int i = 0; i < samples; i++) {
                    newSeed = seed + "-" + (i + 1)+ suffix;
                    data.add(newSeed);
                }


                break;

        }


        return data;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    
    
     
}
