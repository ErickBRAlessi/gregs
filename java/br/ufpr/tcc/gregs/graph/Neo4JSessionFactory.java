package br.ufpr.tcc.gregs.graph;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.neo4j.driver.Values.parameters;

@Component
public abstract class Neo4JSessionFactory {

	@Value("${neo4j.uri}")
	private static String uri = "bolt://localhost:7687/";

	@Value("${neo4j.user}")
	private static String user = "admin";

	@Value("${neo4j.password}")
	private static String password = "admin";

	public static Session getSession() {
		Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
		return driver.session();
	}

}