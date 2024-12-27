package com.foodapi.foodapp.db;

import com.foodapi.foodapp.Util.GeneralOperations;
import com.foodapi.foodapp.models.ParamList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBOperations {
    @PersistenceContext
    EntityManager entityManager;
    public  enum Option {
        ADD,
        EDIT
    }
    
    
    @Transactional
    public <T>Integer  saveEntity(T entity, Option option){
        Integer result = 0;
        try{
            if(option == Option.ADD) {
                 entityManager.persist(entity);

            } else if (option == Option.EDIT) {
               entityManager.merge(entity);


            }
            result = 1;
        }catch (Exception e)
        {
            throw new RuntimeException("Failed to save entity" + entity,e);
        }
    return result;
    }
    
    
    @Transactional
    public <T> List<T> getStoredProcedureData(String procName, Class<T> type, List<ParamList> paramList) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procName, type);
        String procedureQuery = "";
        if(paramList != null) {
            for (ParamList parameter : paramList) {
                storedProcedureQuery.registerStoredProcedureParameter(parameter.getParamName(), parameter.getType(),
                        ParameterMode.IN);
                storedProcedureQuery.setParameter(parameter.getParamName(), parameter.getParamValue());
                if(procedureQuery.equals(""))
                {
                	procedureQuery = parameter.getParamValue();
                }
                else {
					procedureQuery += ","+parameter.getParamValue();
				}
            }
        }

        GeneralOperations.logMessage(procName +"("+procedureQuery+")");
        storedProcedureQuery.execute();
        
        @SuppressWarnings("unchecked")
        List<T> result = storedProcedureQuery.getResultList();

        return result;
    }
    
    
    @Transactional
    public <T> List<T> getStoredProcedureDataWithoutClass(String procName,  List<ParamList> paramList) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procName);

        if(paramList != null) {
            for (ParamList parameter : paramList) {
                storedProcedureQuery.registerStoredProcedureParameter(parameter.getParamName(), parameter.getType(),
                        ParameterMode.IN);
                storedProcedureQuery.setParameter(parameter.getParamName(), parameter.getParamValue());
            }
        }

        storedProcedureQuery.execute();
        @SuppressWarnings("unchecked")
        List<T> result = storedProcedureQuery.getResultList();

        return result;
    }

    
    @Transactional
    public int executeUpdateProcedure(String procName, List<ParamList> paramList) {
        int result = 0;

        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procName);
            String procedureQuery = "";

            if (paramList != null) {
                for (ParamList parameter : paramList) {
                    storedProcedureQuery.registerStoredProcedureParameter(parameter.getParamName(), parameter.getType(), ParameterMode.IN);
                    storedProcedureQuery.setParameter(parameter.getParamName(), parameter.getParamValue());
                    if(procedureQuery.equals(""))
                    {
                    	procedureQuery = parameter.getParamValue();
                    }
                    else {
    					procedureQuery += ","+parameter.getParamValue();
    				}
                }
            }
            System.out.println(procName+"("+procedureQuery+")");
             result = storedProcedureQuery.executeUpdate();
//            if(res)
//            {
//            	result = 1;// Return 1 to indicate successful execution
//            	return result;
//            }
            
        } catch (Exception e) {
            // Log the exception if you need to
             System.out.println("Error executing update procedure: " + procName+"\n"+ e);
            return result;       
            }
        return result;
    }
    
    @Transactional
    public int executeQuery(String query)
    {
    	int result = 0;
    	try {
    		Query q = entityManager.createNativeQuery(query);
    		result = q.executeUpdate();
    	}
    	catch (Exception e) {
    		System.out.println("Error While Executing :::"+ query +"\n"+e);

		}
    	return result;
    }
    
    public String getSingleColumnStoredProcedureData(String procName, List<ParamList> paramList) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procName);

        if (paramList != null) {
            for (ParamList parameter : paramList) {
                storedProcedureQuery.registerStoredProcedureParameter(parameter.getParamName(), parameter.getType(),
                        ParameterMode.IN);
                storedProcedureQuery.setParameter(parameter.getParamName(), parameter.getParamValue());
            }
        }

        storedProcedureQuery.execute();
        
        Object result = storedProcedureQuery.getSingleResult();
        
        return result != null ? result.toString() : null;
    }

}
