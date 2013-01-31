package ibfb.workbook.utils;

import ibfb.domain.core.Constant;
import ibfb.domain.core.Factor;
import ibfb.domain.core.Variate;
import java.util.ArrayList;
import java.util.List;

/**
 * DUMMY class for retrieve Factors, Variates and Constants, used to fill
 * JLists objects
 * @author TMSANCHEZ
 */
public class ListUtils {

    private List<Constant> constantList;
    private List<Factor> factorList;
    private List<Variate> variateList;
    private static ListUtils listUtils;

    private ListUtils() {
        initLists();
    }

    public static ListUtils instance() {
        if (listUtils == null) {
            listUtils = new ListUtils();
        }
        return listUtils;
    }

    private void initLists() {
        initConstantList();
        initFactorList();
        initVariateList();
    }

    public String[] getConstantAsArray() {
        String[] list = new String[constantList.size()];
        for (int i = 0; i < constantList.size(); i++) {
            list[i] = constantList.get(i).getConstantName();
        }
        return list;
    }

    public String[] getVariateAsArray() {
        String[] list = new String[variateList.size()];
        for (int i = 0; i < variateList.size(); i++) {
            list[i] = variateList.get(i).getVariateName();
        }
        return list;
    }

    public String[] getFactorsAsArray() {
        String[] list = new String[factorList.size()];
        for (int i = 0; i < factorList.size(); i++) {
            list[i] = factorList.get(i).getFactorName();
        }
        return list;
    }

    public List<Constant> getConstantList() {
        return constantList;
    }

    public List<Factor> getFactorList() {
        return factorList;
    }

    public List<Variate> getVariateList() {
        return variateList;
    }

    private void initConstantList() {
        constantList = new ArrayList<Constant>();

        constantList.add(new Constant("GPSLTG", "GPS Latitude (Degrees)"));
        constantList.add(new Constant("GPSLTM", "GPS Latitude (Minutes)"));
        constantList.add(new Constant("GPSLAT", "GPS Latitude (N or S)"));
        constantList.add(new Constant("GPSLND", "GPS Longitude (Degrees)"));
        constantList.add(new Constant("GPSLNM", "GPS Longitude (Minutes)"));
        constantList.add(new Constant("GPSLON", "GPS Longitude ( E or W)"));
        constantList.add(new Constant("GPSALT", "GPS Altitude"));
        constantList.add(new Constant("COOPEM", "Cooperator's  E-mail"));
        constantList.add(new Constant("COOPNT", "Cooperator's Note Taker"));
        constantList.add(new Constant("SOWDAT", "SOWING DATE"));
        constantList.add(new Constant("EMGDAT", "EMERGENCE DATE"));
        constantList.add(new Constant("EMERGE", "Type of Emergence relative to long-term norm"));
        constantList.add(new Constant("ESTAND", "CROP/STAND DENSITY (if not at emergence, specify decimal code stage)"));
        constantList.add(new Constant("HRSDAT", "HARVEST Start Date"));
        constantList.add(new Constant("HRFDAT", "HARVEST End Data"));
        constantList.add(new Constant("NOROWS", "PLOT DIMENSION - SOWN (Number of Rows)"));
        constantList.add(new Constant("LEROWS", "    Length of Rows"));
        constantList.add(new Constant("SPAROW", "     Space between Rows"));
        constantList.add(new Constant("NOROWH", "PLOT DIMENSION - HARVESTED (Number of Rows)"));
        constantList.add(new Constant("LEROWH", "   Length of Rows"));
        constantList.add(new Constant("BEDSAR", "PLOT AREA SOWN ON BEDS"));
        constantList.add(new Constant("BEDHAR", "PLOR AREA HARVESTED ON BEDS"));
        constantList.add(new Constant("FOLDIS", "FOLIAR DISEASE"));
        constantList.add(new Constant("ROODIS", "ROOT DISEASE"));
        constantList.add(new Constant("DMGINS", "INSECT DAMAGE"));
        constantList.add(new Constant("DMGBIR", "BIRD DAMAGE"));
        constantList.add(new Constant("DMGHER", "HERBICIDE DAMAGE"));
        constantList.add(new Constant("WEEDPR", "WEED PROBLEM"));
        constantList.add(new Constant("ELODGE", "LODGING PROBLEM"));
        constantList.add(new Constant("DMGHAI", "HAIL DAMAGE"));
        constantList.add(new Constant("DMGFRS", "SPIKE FROST DAMAGE"));
        constantList.add(new Constant("MAJWED", "If weed problem moderate or severe, specify major species"));
        constantList.add(new Constant("OTHOBS", "OTHER COMMENTS (Problems and Obsevations on Plant Stresses excluding weather)"));
        constantList.add(new Constant("WEACOM", "WEATHER (General Comment, specially deviation from normal)"));
        constantList.add(new Constant("USEPRE", "USE OF FIELD IN SEASON PRECEDING TRIAL"));
        constantList.add(new Constant("USESCR", "     Specify Crop Group"));
        constantList.add(new Constant("FERTAP", "FERTILIZER APPLIED   (Y/N)?"));
        constantList.add(new Constant("SPEOFR", "  Specify unit of fertilizer applied if different from kg/ha"));
        constantList.add(new Constant("FERTI1", "1st Application Date"));
        constantList.add(new Constant("FERKG1", "   Amount of Fertilizer applied in kg/ha"));
        constantList.add(new Constant("FER%N1", "   %N"));
        constantList.add(new Constant("%P2O51", "   %P2O5"));
        constantList.add(new Constant("F%K2O1", "   %K2O"));
        constantList.add(new Constant("OTHEL1", "   Other Element"));
        constantList.add(new Constant("FERTI2", "2nd Application Date"));
        constantList.add(new Constant("FERKG2", "   Amount of Fertilizer applied in kg/ha"));
        constantList.add(new Constant("FER%N2", "   %N"));
        constantList.add(new Constant("F%K2O2", "   %P2O5"));
        constantList.add(new Constant("%P2O52", "   %K2O"));
        constantList.add(new Constant("OTHEL2", "   Other Element"));
        constantList.add(new Constant("FERTI3", "3rd Application Date"));
        constantList.add(new Constant("FERKG3", "   Amount of Fertilizer applied in kg/ha"));
        constantList.add(new Constant("FER%N3", "  %N"));
        constantList.add(new Constant("F%K2O3", "  %P2O5"));
        constantList.add(new Constant("%P2O53", "  %K2O"));
        constantList.add(new Constant("HERBIC", "HERBICIDE (Y/N)?"));
        constantList.add(new Constant("HERPRO", "   If Yes, specify product (s)"));
        constantList.add(new Constant("FUNGIC", "FUNGICIDE (Y/N)?"));
        constantList.add(new Constant("FUNPRO", "   If Yes, specify product (s)"));
        constantList.add(new Constant("PESTIC", "PESTICIDE (Y/N)?"));
        constantList.add(new Constant("PESPRO", "   If Yes, specify product (s)"));
        constantList.add(new Constant("OTCHEM", "OTHER CHEMICAL (Y/N)?"));
        constantList.add(new Constant("OCHPRO", "   If Yes, specify product (s)"));
        constantList.add(new Constant("SOILCL", "SOIL CLASSIFICATION"));
        constantList.add(new Constant("SOILTX", "SOIL SURFACE TEXTURE"));
        constantList.add(new Constant("SOISUR", "  If other type, Specify"));
        constantList.add(new Constant("SOILPH", "SOIL SURFACE PH"));
        constantList.add(new Constant("SOILAV", "   If not in list, specify actual value"));
        constantList.add(new Constant("ALTOXI", "ALUMINUM TOXICITY (Y/N)"));
        constantList.add(new Constant("ALCLAS", "  If Yes, choose classification"));
        constantList.add(new Constant("SOILOM", "% ORGANIC MATTER"));
        constantList.add(new Constant("SOIRBA", "ROOT BARRIER"));
        constantList.add(new Constant("SOIDBA", "  If Yes, specify depth"));
        constantList.add(new Constant("SOIDRZ", "  If No, specify depth of root soze"));
        constantList.add(new Constant("OMNTYN", "Other know micronutrient (s) toxicity/deficiency (Y/N)?"));
        constantList.add(new Constant("NOROWS", "NOROWS"));
        constantList.add(new Constant("IRRIGD", "IRRIGATED (Y/N)?"));
        constantList.add(new Constant("NPREIR", " If Yes, number pre-sowing irrigations"));
        constantList.add(new Constant("NPOSIR", " If yes, number post-sowing irrigations"));
        constantList.add(new Constant("RESH2O", "Stored available moisture (excluding pre-sowing irrigation) in full root zone at sowing"));
        constantList.add(new Constant("PREIRR", "Water applied as pre-sowing irrigation"));
        constantList.add(new Constant("PPNCYC", "Precipitation from sowing to maturity"));
        constantList.add(new Constant("IRRCYC", "Water applied by irrigation after sowing"));
        constantList.add(new Constant("CTWIRR", "TOTAL WATER AVAILABILITY"));
        constantList.add(new Constant("PPN11M", "PRECIPITATION on 11th Month Before Harvest"));
        constantList.add(new Constant("PPN10M", "   10th Month Before Harvest"));
        constantList.add(new Constant("PPN09M", "   9th Month Before Harvest"));
        constantList.add(new Constant("PPN08M", "   8th Month Before Harvest"));
        constantList.add(new Constant("PPN07M", "   7th Month Before Harvest"));
        constantList.add(new Constant("PPN06M", "   6th Month Before Harvest"));
        constantList.add(new Constant("PPN05M", "   5th Month Before Harvest"));
        constantList.add(new Constant("PPN04M", "   4th Month Before Harvest"));
        constantList.add(new Constant("PPN03M", "   3rd Month Before Harvest"));
        constantList.add(new Constant("PPN02M", "   2nd Month Before Harvest"));
        constantList.add(new Constant("PPN01M", "   1st Month Before Harvest"));
        constantList.add(new Constant("PPN00M", "    Month of Harvest"));
        constantList.add(new Constant("TP_12M", "TOTAL PRECIPITATION in 12 months"));

    }

    private void initFactorList() {
        factorList = new ArrayList<Factor>();

        factorList.add(new Factor("CID", ""));
        factorList.add(new Factor("SID", ""));
        factorList.add(new Factor("Cross Name", ""));
        factorList.add(new Factor("Selection History", ""));
        factorList.add(new Factor("Origin", ""));
        factorList.add(new Factor("Plot", ""));
        factorList.add(new Factor("Rep", ""));
        factorList.add(new Factor("SubBlock", ""));
        factorList.add(new Factor("Entry", ""));
        factorList.add(new Factor("Row", ""));
        factorList.add(new Factor("Col.", ""));

    }

    private void initVariateList() {
        variateList = new ArrayList<Variate>();
        variateList.add(new Variate("Aglu", "Notation (1 √† 3) au moment du battage"));
        variateList.add(new Variate("ANTHO", "A noter apres floraison (en m√™me temps que caract√®re juteux) sur les plantes not√©es pour feuille drapeau A+ ou A-"));
        variateList.add(new Variate("ANTHR", "Anthracnose"));
        variateList.add(new Variate("ARIST", "Voir descripteurs"));
        variateList.add(new Variate("BSUIE", "Bande suie"));
        variateList.add(new Variate("CASSP", "A noter si cela arrive: nb de plante par parcelle"));
        variateList.add(new Variate("CB", "Observation visuelle"));
        variateList.add(new Variate("CBmr", "Pr√©sence d'une nervure brune"));
        variateList.add(new Variate("CGLU", "Voir descripteurs"));
        variateList.add(new Variate("CHARB", "Charbon"));
        variateList.add(new Variate("COULPR", "Observation visuelle"));
        variateList.add(new Variate("COULTO", "Observation visuelle"));
        variateList.add(new Variate("CPAN", "Notation : 1 (compacte) √† 5 (lache) selon norme Icrisat au champ avant r√©colte et √† maturit√©"));
        variateList.add(new Variate("DFD", "Moyenne de la date de ligulation de la feuille drapeau"));
        variateList.add(new Variate("DGR", "Poids de 250 ml de grain"));
        variateList.add(new Variate("DMAT", "date maturit√© (point noir)"));
        variateList.add(new Variate("FERMTO", "Fermet√© d'un echantillon de to"));
        variateList.add(new Variate("FLO50", "Calcul√© =  DFD + 15j"));
        variateList.add(new Variate("HBUG", "Observation visuelle"));
        variateList.add(new Variate("HP", "Sur 1 plante repr√©sentative (moyenne) avant la r√©colte, mesur√©e √† la r√®gle de la base de la plante, √† la base du pedoncule, √† la base de la panicule et au sommet de la plante"));
        variateList.add(new Variate("HTIG1", "Sur 2x3 tiges coup√©es √† la r√©colte (sans le poquet de bordure)"));
        variateList.add(new Variate("HTIG2", "Sur 2x3 tiges coup√©es √† la r√©colte (sans le poquet de bordure)"));
        variateList.add(new Variate("HUMGR", "Taux d'humidit√© au battage"));
        variateList.add(new Variate("IRPAN", "Calcul√© = PGR/PPAN"));
        variateList.add(new Variate("KP", "Calcul√© = (DeltaDFD)/(DeltaDatesmis) si au moins  deux dates de semis disponibles"));
        variateList.add(new Variate("LPAN", "Longueur entre le premier entre n≈ìud et l'extremit√© de la panicule sur 6 panicules tir√©es au hasard dans l'ens des panicules r√©colt√©es"));
        variateList.add(new Variate("LPED", "Sur 2x3 tiges coup√©es √† la r√©colte (sans le poquet de bordure)"));
        variateList.add(new Variate("NbETN", "Sur 2x3 tiges coup√©es √† la r√©colte (sans le poquet de bordure)"));
        variateList.add(new Variate("NbFV", "A noter en m√™me temps et sur les m√™me plantes que la date de maturit√©"));
        variateList.add(new Variate("NbGPAN", "Calcul√© = PGR/NbPAN"));
        variateList.add(new Variate("NbPan", "Nb de panicules avec grain r√©colt√©es sur toutes les plantes de la parcelle"));
        variateList.add(new Variate("NbPoq", "Nb de poquets dont au moins 1 plante produit des grains sur toutes les plantes de la parcelle"));
        variateList.add(new Variate("NBPPAN", "Nb de branches primaires sur les panicules mesur√©es"));
        variateList.add(new Variate("NbTAL", "Calcul√© = NbPan - (NpPoq x nb plant par poquet au d√©marriage)"));
        variateList.add(new Variate("NERV", "Aspect de la nervure centrale √† maturit√© (sec ou humide, blanc ou vert)"));
        variateList.add(new Variate("NIRSAM", "Amylose content"));
        variateList.add(new Variate("NIRSET", "Endosperm texture"));
        variateList.add(new Variate("NIRSLI", "Lipid content"));
        variateList.add(new Variate("NIRSPS", "Grain hardness"));
        variateList.add(new Variate("NIRSPT", "Protein content"));
        variateList.add(new Variate("OISD", "%age d'attaque de la parcelle"));
        variateList.add(new Variate("OPER", "Observation visuelle"));
        variateList.add(new Variate("PGR", "Poids de grain battu r√©colt√© sur l'ensemble de la parcelle"));
        variateList.add(new Variate("PMG", "Poids de 250 grains"));
        variateList.add(new Variate("Ppan", "Poids de l'ensemble des panicules avec grain (coup√©es √† la base) r√©colt√©es apres sechage et avant battage"));
        variateList.add(new Variate("RDC", "rapport entre poids de grain apres et avant decorticage"));
        variateList.add(new Variate("Repiq", "Nb de poquets repiqu√©s au moment du d√©mariage"));
        variateList.add(new Variate("SFD", "Calcul√© = DFD - Date semis"));
        variateList.add(new Variate("STG", "Stay-green"));
        variateList.add(new Variate("TGRMO", "%age de grains moisis apr√®s r√©colte et battage"));
        variateList.add(new Variate("TL10", "%age de poquets lev√© √† 10j apres semis"));
        variateList.add(new Variate("TL5", "%age de poquets lev√© √† 5j apres semis"));
        variateList.add(new Variate("VERSC", "A noter si cela arrive: nb de plante par parcelle"));
        variateList.add(new Variate("VERSR", "A noter si cela arrive: nb de plante vers√©e par parcelle"));
        variateList.add(new Variate("VITRO", "%age d'albumen vitreux. Sur 10 grains."));
        variateList.add(new Variate("VL", "Vigueur √† la lev√©e"));


    }
}
