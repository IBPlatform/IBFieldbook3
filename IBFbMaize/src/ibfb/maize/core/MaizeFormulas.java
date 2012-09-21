package ibfb.maize.core;

public class MaizeFormulas {

    /**
     * ENGLISH:(FieldWt/1000)*((100-GrainMoisturePer)/(100-12.5)*(10/PlotSize))*ShellPer
     * <p> SPANISH: (Peso de Campo / 1000) * ((100 - %Humedad) / (100 - 12.5) *
     * (10 / Tamaño de la Parcela)) * %Desgrane
     */
    public double GrainYieldTons_FieldWt(double FieldWt, double GrainMoisturePer, double PlotSize, double ShellPer) {
        double result = (FieldWt / 1000) * ((100 - GrainMoisturePer) / (100 - 12.5) * (10 / PlotSize)) * ShellPer;
        return result;
    }

    public boolean isValidGrainYieldTons_FieldWt(double data) {
        int min = 10;
        int max = 20000;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * Ear Rot Total Num / Num Ears <p> SPANISH: 100 * (Numero de
     * Mazorcas Podridas / Numero de Mazorcas)
     */
    public double EarRotTotalPer(int EarRot, int NumEars) {
        double result = 100 * EarRot / NumEars;
        return result;
    }

    public boolean isValidEarRotTotalPer(double data) {
        int min = 0;
        int max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: Ear Hght (cm) / Plant Hght (cm) <p> SPANISH: Altura de Mazorca
     * (cm) / Altura de Planta (cm)
     */
    public double EarPosition(double EarHight, double PlantHight) {
        double result = EarHight / PlantHight;
        return result;
    }

    public boolean isValidEarPosition(double data) {
        double min = 0.10;
        double max = 0.70;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: #Ears hvst / #Plts hvst <p> SPANISH: #Mazorcas Cosechadas /
     * #Plantas Cosechad
     */
    public double EPPNo(int Earshvst, int Pltshvst) {
        double result = Earshvst / Pltshvst;
        return result;
    }

    public boolean isValidEPPNo(double data) {
        double min = 0;
        double max = 0;
        //if (data >= min && data <= max) {
        return true;
        // } else {
        //   return false;
        // }
    }

    /**
     * ENGLISH: 100 * (#Root Lodg / #Plts hvst) <p> SPANISH: 100 * (#Acame de
     * Raíz / #Plantas Cosechadas)d
     */
    public double RootLodgingPer(int RootLodg, int Pltshvst) {
        double result = 100 * (RootLodg / Pltshvst);
        return result;
    }

    public boolean isValidRootLodgingPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * #Stalk Lodg / #Plts hvs <p> SPANISH: 100 * (#Acame de
     * Tallo / #Plantas Cosechadas)
     */
    public double StemLodgingPer(double StalkLodg, int Pltshvst) {
        double result = 100 * StalkLodg / Pltshvst;
        return result;
    }

    public boolean isValidStemLodgingPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * #Bad Hsk / #Plts hvst <p> SPANISH: 100 * (#Mala Cobertura
     * / #Plantas Cosechadas)
     */
    public double BadHuskCoverPer(double badHsk, int pltsHvst) {
        double result = 100 * badHsk / pltsHvst;
        return result;
    }

    public boolean isValidBadHuskCoverPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * #Stunt CSC / #Plts hvst <p> SPANISH: 100 *
     * #Achaparramiento del Maíz / #Plantas Cosechada
     */
    public double CornStuntPer(double stuntCSC, int pltsHvst) {
        double result = 100 * stuntCSC / pltsHvst;
        return result;
    }

    public boolean isValidCornStuntPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: Days Flwr. Fem. - Days Flwr. Male <p> SPANISH: Dias a Floración
     * Femenina - Dias a Floración Masculina
     */
    public double ASI(int daysFlwrFem, int daysFlwrMale) {
        double result = daysFlwrFem - daysFlwrMale;
        return result;
    }

    public boolean isValidASI(double data) {
        double min = -20;
        double max = 20;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: (Grain Wght g/1000)*((100-% Moisture)/(100-12.5))*(10/PlotSize)
     * <p> SPANISH: (Peso de Grano/1000)*((100 - %Humedad)/(100-12.5))*(10 /
     * Tamaño de la Parcela)
     */
    public double GrainYieldTons_GrainWt(double grainWght, int moisture, double plotSize) {
        double result = (grainWght / 1000) * ((100 - moisture) / (100 - 12.5)) * (10 / plotSize);
        return result;
    }

    public boolean isValidGrainYieldTons_GrainWt(double data) {
        double min = 10;
        double max = 20000;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100* #Sorg DM / #Plts hvst <p> SPANISH: 100 * #Sorgo DM /
     * #Plantas Cosechadas
     */
    public double DMSorghumPer(double sorgDM, int pltsHvst) {
        double result = 100 * sorgDM / pltsHvst;
        return result;
    }

    public boolean isValidDMSorghumPer(double data) {
        double min = 0;
        double max = 0;
        // if (data >= min && data <= max) {
        return true;
        // } else {
        //    return false;
        // }
    }

    /**
     * ENGLISH: 100* #Java DM / #Plts hvst <p> SPANISH: 100 * #Mildiú Java del
     * Maíz / #Plantas Cosechadas
     */
    public double DMJavaPer(int javaDM, int pltsHvst) {
        double result = 100 * javaDM / pltsHvst;
        return result;
    }

    public boolean isValidDMJavaPer(double data) {
        double min = 0;
        double max = 0;
        // if (data >= min && data <= max) {
        return true;
        // } else {
        //    return false;
    }

    /**
     * ENGLISH: 100 * #Dwf. MozV / Plts hvst <p> SPANISH: % Mosaico del Enanismo
     * del Maíz
     */
    public double VirusMaizeDwarfMosaicPer(int dwfMozV, int pltsHvst) {
        double result = 100 * dwfMozV / pltsHvst;
        return result;
    }

    public boolean isValidVirusMaizeDwarfMosaicPer(double data) {
        double min = 0;
        double max = 0;
        // if (data >= min && data <= max) {
        return true;
        // } else {
        //    return false;
    }

    /**
     * ENGLISH: AVERAGE(Leaf Roll1 (1-5), Leaf Roll2 (1-5), Leaf Roll3 (1-5))
     * <p> SPANISH: Promedio (Hoja Enrollada1 (1-5), Hoja Enrollada2 (1-5), Hoja
     * Enrollada3 (1-5))
     */
    public double LeafRolling1_5(double leafRoll1, double leafRoll2, double leafRoll3) {
        double result = (leafRoll1 + leafRoll2 + leafRoll3) / 3;
        return result;
    }

    public boolean isValidLeafRolling1_5(double data) {
        double min = 1;
        double max = 5;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: AVERAGE(Leaf Sen1 (1-100), Leaf Sen2 (1-100, Leaf Sen3 (1-100)
     * <p> SPANISH: Promedio (Hoja Sen1 (1-100), Hoja Sen2 (1-100),Hoja Sen3
     * (1-100))
     */
    public double Senescence0_10(double leafSen1, double leafSen2, double leafSen3) {
        double result = (leafSen1 + leafSen2 + leafSen3) / 3;
        return result;
    }

    public boolean isValidSenescence0_10(double data) {
        double min = 0;
        double max = 0;
        // if (data >= min && data <= max) {
        return true;
        // } else {
        //    return false;
    }

    /**
     * ENGLISH: AVERAGE(Leaf SPAD1, Leaf SPAD2, Leaf SPAD3) <p> SPANISH:
     * Promedio (Hoja SPAD1,Hoja SPAD2,Hoja SPAD3)
     */
    public double Spad(double leafSpad1, double leafSpad2, double leafSpad3) {
        double result = (leafSpad1 + leafSpad2 + leafSpad3) / 3;
        return result;
    }

    public boolean isValidSpad(double data) {
        double min = 0;
        double max = 0;
        // if (data >= min && data <= max) {
        return true;
        // } else {
        //    return false;
    }

    /**
     * ENGLISH: 100 * #Chupones / #Plts hvst <p> SPANISH: 100 * #Chupones /
     * #Plantas Cosechadas
     */
    public double ChuponPer(int chupones, int pltsHvst) {
        double result = 100 * chupones / pltsHvst;
        return result;
    }

    public boolean isValidChuponPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * # Common smut / #Plts hvst <p> SPANISH: 100 * #Carbón
     * Común / #Plantas Cosechadas
     */
    public double CommonSmutPer(int commonSmut, int pltsHvst) {
        double result = 100 * commonSmut / pltsHvst;
        return result;
    }

    public boolean isValidCommonSmutPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * Crazy top / #Plts hvst <p> SPANISH: 100 * Punta Loca /
     * #Plantas Cosechadas
     */
    public double CrazyTopPer(int crazyTop, int pltsHvst) {
        double result = 100 * crazyTop / pltsHvst;
        return result;
    }

    public boolean isValidCrazyTopPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * Head Smut / #Plts hvst <p> SPANISH: 100 * Carbón de la
     * Espiga / #Plantas Cosechadas
     */
    public double HeadSmutPer(int headSmut, int pltsHvst) {
        double result = 100 * headSmut / pltsHvst;
        return result;
    }

    public boolean isValidHeadSmutPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * Stalk Rot Fus Gram Num(1) / #Plts hvst <p> SPANISH: 100 *
     * Numero de Pudrición de Tallo por Fusarium Graminearum / #Plantas
     * Cosechadas
     */
    public double StalkRotFusGramPer(double stalkRotFusGramNum, int pltsHvst) {
        double result = 100 * stalkRotFusGramNum / pltsHvst;
        return result;
    }

    public boolean isValidStalkRotFusGramPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * Stalk Rot Num / #Plts hvst <p> SPANISH: 100 * Numero de
     * Pudrición de Tallo / #Plantas Cosechadas
     */
    public double StalkRotPer(double stalkRotFusGramNum, int pltsHvst) {
        double result = 100 * stalkRotFusGramNum / pltsHvst;
        return result;
    }

    public boolean isValidStalkRotPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENGLISH: 100 * StalkRotPythiumNum / #Plts hvst <p> SPANISH: 100 * Numero
     * de Pudrición de Tallo por Phytium / #Plantas Cosechadas
     */
    public double StalkRotPythiumPer(double stalkRotPythiumNum, int pltsHvst) {
        double result = 100 * stalkRotPythiumNum / pltsHvst;
        return result;
    }

    public boolean isValidStalkRotPythiumPer(double data) {
        double min = 0;
        double max = 100;
        if (data >= min && data <= max) {
            return true;
        } else {
            return false;
        }
    }
}
