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

	public static void printGreeting(final String message) {
		try (Session session = Neo4JSessionFactory.getSession()) {
			String greeting = session.writeTransaction(new TransactionWork<String>() {
				@Override
				public String execute(Transaction tx) {
					Result result = tx.run("CREATE (a:Greeting) " + "SET a.message = $message "
							+ "RETURN a.message + ', from node ' + id(a)", parameters("message", message));
					return result.single().get(0).asString();
				}
			});
			System.out.println(greeting);
		}
	}

	public static void main(String... args) throws Exception {

		Neo4JSessionFactory.printGreeting("hello, world");
	}
}