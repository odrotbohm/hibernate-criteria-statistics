package de.olivergierke.samples.aspectj.hibernate;

import org.hibernate.loader.criteria.CriteriaLoader;
import org.hibernate.stat.Statistics;


/**
 * Aspect adding a method to {@link CriteriaLoader} to allow Hibernate
 * {@link Statistics} pick up criteria queries, too.
 * 
 * @author Oliver Gierke
 */
public privileged aspect CriteriaStatisticsAspect {

    public static final String PREFIX = "[CRITERIA] ";


    /**
     * @see http://opensource.atlassian.com/projects/hibernate/browse/HHH-3452
     * @return
     */
    public String CriteriaLoader.getQueryIdentifier() {

        return PREFIX + getSQLString();
    }
}
