package br.ufpr.tcc.gregs.resources;

import static org.neo4j.driver.Values.parameters;

import java.util.List;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.tcc.gregs.graph.Neo4JSessionFactory;

@RestController
public class TagsResource {
	

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/tags")
	public void putTag(@RequestBody List<String> tags) {
		
		try {
			Session s = Neo4JSessionFactory.getSession();
			tags.forEach(t -> s.writeTransaction(new TransactionWork<String>() {
				@Override
				public String execute(Transaction tx) {
			
					Result result = tx.run("CREATE (a:Tag) " + "SET a.name = $tag, a.tag = $tag "
							+ "RETURN a.Tag + ', from node ' + id(a)", parameters("tag", t.toUpperCase().trim()));
					return result.single().get(0).asString();
				}
			})
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
