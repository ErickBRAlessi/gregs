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
	private static String uri = "bolt://localhost:7687/";

	@Value("${neo4j.user}")
	private static String user = "neo4j";

	@Value("${neo4j.password}")
	private static String password = "admin";

	public static Session getSession() {
		try {			
			Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
			return driver.session();
		} catch (Exception e) {
			return null;
		}
	}

}