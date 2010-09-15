package de.olivergierke.samples.aspectj.hibernate;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.*;

import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;


/**
 * Integration test to see whether {@link CriteriaStatisticsAspect} aspect really
 * enables statistics for criteria queries.
 * 
 * @author Oliver Gierke
 */
public class CriteriaStatisticsIntegrationTest {

    SessionFactory sessionFactory;


    @Before
    public void setUp() throws Exception {

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect",
                "org.hibernate.dialect.HSQLDialect");

        AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
        bean.setDataSource(new EmbeddedDatabaseBuilder().setType(HSQL).build());
        bean.setPackagesToScan(new String[] { getClass().getPackage().getName() });
        bean.setSchemaUpdate(true);
        bean.setHibernateProperties(hibernateProperties);
        bean.afterPropertiesSet();

        this.sessionFactory = bean.getObject();
        this.sessionFactory.getStatistics().setStatisticsEnabled(true);
    }


    @Test
    public void hibernateStatisticsIncludeCriteriaCalls() throws Exception {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User("Gierke");
        session.save(user);

        Criteria criteria = session.createCriteria(User.class);
        criteria.list();

        transaction.commit();

        String[] queries = this.sessionFactory.getStatistics().getQueries();

        assertThat(queries.length, is(1));
        assertTrue(queries[0].startsWith(CriteriaStatisticsAspect.PREFIX));
    }
}
