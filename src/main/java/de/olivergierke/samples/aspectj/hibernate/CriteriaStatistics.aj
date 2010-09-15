package de.olivergierke.samples.aspectj.hibernate;

import org.hibernate.loader.criteria.CriteriaLoader;

/**
 *
 * @author Oliver Gierke
 */
public privileged aspect CriteriaStatistics {
    
    public static final String PREFIX = "[CRITERIA] ";

    
    public String CriteriaLoader.getQueryIdentifier() { 
        return PREFIX + getSQLString(); 
    }
}
