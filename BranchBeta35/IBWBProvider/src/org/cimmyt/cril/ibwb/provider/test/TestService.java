/**
 * 
 */
package org.cimmyt.cril.ibwb.provider.test;


import org.cimmyt.cril.ibwb.api.AppServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author MasterGama
 *
 */
public class TestService {
	protected String[] archivos = {"ibwApiApplicationContextStatic.xml"};
	protected ApplicationContext context = new ClassPathXmlApplicationContext(archivos);
	protected AppServices servicios;
	
	public TestService() {
//              ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"classpath:/applicationContext-resources.xml"});
//              DataSourceFactory dataSourceFactory = new DataSourceFactory(ctx);
//              JdbcTemplate source = this.getJdbcTemplate();
//              source.setDataSource(dataSourceFactory.getDataSource(esquemaParticular));
		servicios = (AppServices)context.getBean("ibwbAppServices");
		
	}
}
