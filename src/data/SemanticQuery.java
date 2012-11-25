package data;

import java.io.DataInputStream;
import java.io.InputStream;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * @author robertomm
 * 
 */
public class SemanticQuery {

	public SemanticQuery() {

	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public String execQuery(String s) {

		OntModel m = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		try {
			InputStream fis;
			fis = getClass().getResourceAsStream("finalBuildingSensors.owl");
			DataInputStream input = new DataInputStream(fis);
			m.read(input, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Query query = QueryFactory.create(s);
		QueryExecution qe = SparqlDLExecutionFactory.create(query, m);
		ResultSet rs = qe.execSelect();
		System.out.println();
		return ResultSetFormatter.asText(rs);

	}

}
