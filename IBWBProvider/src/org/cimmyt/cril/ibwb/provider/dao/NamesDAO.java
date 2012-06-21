package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Germplsm;
import org.cimmyt.cril.ibwb.domain.Names;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class NamesDAO extends AbstractDAO<Names, Integer> {

    public NamesDAO() {
        super(Names.class);
    }

    @Override
    public Names prepareToCreate(Names names) {
        if (isLocal()) {
            names.setNid(getNextMin());
        }
        if (isCentral()) {
            names.setNid(getNextMax());
        }
        return names;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Names filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "nid";
    }

    @Override
    public String getConsulta(Names filtro) {
    	String query = "from Names as n";
        
        if(filtro.getGlobalsearch() == null){
            setQuery("n.nid", filtro.getNid());
            setQuery("n.gid", filtro.getGid());
            setQuery("n.ntype", filtro.getNtype());
            setQuery("n.nstat", filtro.getNstat());
            setQuery("n.nuid", filtro.getNuid());
            setQuery("n.nval", filtro.getNval());
            setQuery("n.nlocn", filtro.getNlocn());
            setQuery("n.ndate", filtro.getNdate());
            setQuery("n.nref", filtro.getNref());
        }else{
            global = true;
            if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
                setQuery("n.nid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("n.gid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("n.ntype", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("n.nstat", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("n.nuid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("n.nval", filtro.getGlobalsearch());
                setQuery("n.nlocn", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("n.ndate", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("n.nref", Integer.valueOf(filtro.getGlobalsearch()));
            }else{
                setQueryInTo("n.nval", filtro.getGlobalsearch());
            }
        }
        return query;
    }
    
    public Names getNamesByGid(Integer gid, Boolean preferido){
        if(gid == null){
            return null;
        }else{
            StringBuilder hqlSB = new StringBuilder();
            hqlSB.append("from Names as n where n.gid = ");
            hqlSB.append(gid);
            hqlSB.append(" ");
            if(preferido){
                hqlSB.append(" and n.nstat > 0 ");
            }
            hqlSB.append("order by n.nstat ");
            hqlSB.append("asc");
            
            List<Names> listNames = this.executeQueryCustomListOfT(hqlSB.toString());
            if(listNames == null){
                return getNamesByGid(gid);
            }else if(listNames.size()>0){
                if(listNames.get(0) != null){
                    return listNames.get(0);
                }else{
                    return getNamesByGid(gid);
                }
            }else{
                return getNamesByGid(gid);
            }
        }
    }
    
    public Names getNamesByGid(Germplsm germplasm, Boolean preferido){
        if(germplasm == null){
            return null;
        }else{
            StringBuilder hqlSB = new StringBuilder();
            hqlSB.append("from Names as n where n.gid = ");
            hqlSB.append(germplasm.getGid());
            hqlSB.append(" ");
            if(preferido){
                hqlSB.append(" and n.nstat > 0 ");
            }else{
                if(germplasm.getGnpgs() == -1){
                    hqlSB.append(" and n.ntype = ");
                    hqlSB.append(1028);
                    hqlSB.append(" ");
                }else if(germplasm.getGnpgs() == 2){
                    hqlSB.append(" and n.ntype = ");
                    hqlSB.append(1027);
                    hqlSB.append(" ");
                }
            }
            hqlSB.append("order by n.nstat ");
            hqlSB.append("asc");
            
            List<Names> listNames = this.executeQueryCustomListOfT(hqlSB.toString());
            if(listNames == null){
                return getNamesByGid(germplasm.getGid());
            }else if(listNames.size()>0){
                if(listNames.get(0) != null){
                    return listNames.get(0);
                }else{
                    return getNamesByGid(germplasm.getGid());
                }
            }else{
                return getNamesByGid(germplasm.getGid());
            }
        }
    }
    
    public Names getNamesByGid(Integer gid){
        if(gid == null){
            return null;
        }else{
            StringBuilder hqlSB = new StringBuilder();
            hqlSB.append("from Names as n where n.gid = ");
            hqlSB.append(gid);
            hqlSB.append(" ");
            List<Names> listNames = this.executeQueryCustomListOfT(hqlSB.toString());
            if(listNames == null){
                return null;
            }else if(listNames.size() > 0){
                return listNames.get(0);
            }else{
                return null;
            }
        }
    }
    
    public Integer getMaxForSelection(String cadena, Integer ntype){
        String query = "select max(NVAL) As xName from names where NTYPE=" + ntype + " and NVAL Like '" + cadena + "%';";
        List result = executeQueryCustomListOfGSqlNat(query);
        Integer inicial = 0;
        if(result == null){
            return inicial;
        }else if (result.isEmpty()){
            return inicial;
        }else if(result.size() == 0){
            return inicial;
        }else if(result.get(0) == null){
            return inicial;
        }else{
            String resultado = result.get(0).toString();
            String consecutivo = resultado.substring(cadena.length(), resultado.length());
            StringBuilder sb = new StringBuilder();
            for(char c : consecutivo.toCharArray()){
                if(c > 49 && c < 58 || c == '-'){
                    sb.append(c);
                }else{
                    sb = new StringBuilder();
                    break;
                }
            }
            if(! sb.toString().isEmpty()){
                Integer numeroConsecutivo = Integer.valueOf(sb.toString());
                return numeroConsecutivo;
            }else{
                return 0;
            }
        }
    }
    
    public String getNextMaxForBCID(String cadena, Integer ntype){
        //ntype para (dos padres) bcid = 1027
        //ntype para (un padre) = 1028 para seleccion con gnpgs del germplasm -1
        
        // nstat 1 es preferido
        String query = "select max(NVAL) As xName from names where NTYPE=" + ntype + " and NVAL Like '" + cadena + "%';";
        List result = executeQueryCustomListOfGSqlNat(query);
        String inicial = "00001";
        if(result == null){
            return inicial;
        }else if (result.isEmpty()){
            return inicial;
        }else if(result.size() == 0){
            return inicial;
        }else if(result.get(0) == null){
            return inicial;
        }else{
            String consecutivo = result.get(0).toString().substring(cadena.length(), cadena.length() + 5);
            Integer numeroConsecutivo = Integer.valueOf(consecutivo);
            numeroConsecutivo += 1;
            String siguienteConsecutivo = String.valueOf(numeroConsecutivo);
            StringBuilder consecutivoNuevo = new StringBuilder();
            for(int i = 0 ; i<5 - siguienteConsecutivo.length() ; i++){
                consecutivoNuevo.append(0);
            }
            consecutivoNuevo.append(siguienteConsecutivo);
            return consecutivoNuevo.toString();
        }
    }
}