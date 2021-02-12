package br.ufpr.tcc.gregs.graph;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public abstract class Neo4JSessionFactory {

	@Value("${neo4j.uri}")
	private static final String uri = "bolt://localhost:7687/";

	//ERICK: admin
	//CASS : admin
	//BRUNO: neo4j
	@Value("${neo4j.user}")
	private static final String user = "admin";

	//ERICK: admin
	//CASS: admin
	//BRUNO: admin
	@Value("${neo4j.password}")
	private static final String password = "admin";
	
	private static Driver driver;
	
	public static Session getSession() {
		try {			
			if(driver == null) {
				driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
			}
			return driver.session();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 

	}

}	