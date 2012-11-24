package data;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

	/**
	 * 
	 * @param query
	 * @return
	 */
	public static String runQuery(String s) {

		OntModel m = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		FileInputStream fis;
		try {
			fis = new FileInputStream("buildingSensors.owl");
			DataInputStream in = new DataInputStream(fis);
			m.read(in, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Query q = QueryFactory.create(s);
		QueryExecution qe = SparqlDLExecutionFactory.create(q, m);
		ResultSet rs = qe.execSelect();
		System.out.println();
		return ResultSetFormatter.asText(rs);

	}

}
