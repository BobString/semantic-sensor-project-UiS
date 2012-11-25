package data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class FetchData {

	public static void parseFile(String path) {
		File f = new File(path);
		BufferedReader entrada;
		try {

			entrada = new BufferedReader(new FileReader(f));
			// JENA
			OntModel m = ModelFactory
					.createOntologyModel(PelletReasonerFactory.THE_SPEC);
			FileInputStream fis;
			fis = new FileInputStream("buildingSensors.owl");
			DataInputStream in = new DataInputStream(fis);
			m.read(in, "");
			String NS = "http://www.sensors.uis.no/ontologies/Ontology1352127234683.owl#";

			OntClass itemClass = m.getOntClass(NS + "item");
			OntClass variableClass = m.getOntClass(NS + "Variable");

			String linea;

			String variableType = "";
			Individual I1 = null;
			List<Individual> variables = new ArrayList<Individual>();

			while (entrada.ready()) {
				linea = entrada.readLine();

				if (linea.startsWith("!")) {
					String itemName = linea.substring(1, linea.length());
					itemName = itemName.replaceAll(" ", "_");
					I1 = m.createIndividual(NS + itemName, itemClass);
				} else if (linea.startsWith("%")) {
					variableType = linea.substring(1, linea.length());
				} else {
					String variableName = "";
					String variableTag = "";
					String variableMetric = "";
					String variableDesc = "";

					// GET DATA
					String[] valores = linea.split("#");
					Individual variable = null;
					if (valores.length >= 1) {
						variableName = valores[0];
						variable = m.createIndividual(NS + variableName,
								variableClass);
						m.add(variable, m.createProperty(NS + "variableType"),
								ResourceFactory.createTypedLiteral(
										variableType, XSDDatatype.XSDstring));
						m.add(variable,
								m.createProperty(NS + "variableSymbol"),
								ResourceFactory.createTypedLiteral(
										variableName, XSDDatatype.XSDstring));
					}
					if (valores.length >= 2) {
						variableDesc = valores[1];

						m.add(variable, m.createProperty(NS
								+ "variableDescription"), ResourceFactory
								.createTypedLiteral(variableDesc,
										XSDDatatype.XSDstring));
					}
					if (valores.length >= 3) {
						variableMetric = valores[2];

						m.add(variable,
								m.createProperty(NS + "variableMetric"),
								ResourceFactory.createTypedLiteral(
										variableMetric, XSDDatatype.XSDstring));
					}
					if (valores.length >= 4) {
						variableTag = valores[3];
						m.add(variable, m.createProperty(NS + "variableTag"),
								ResourceFactory.createTypedLiteral(variableTag,
										XSDDatatype.XSDstring));
					}

					variables.add(variable);
				}

			}

			for (Individual i : variables) {
				m.add(I1, m.createProperty(NS + "theorical_variable"), i);
			}
			PrintStream p = new PrintStream("test.owl");
			m.write(p);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addTestValue(String data) {

		OntModel m = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		FileInputStream fis;
		try {
			fis = new FileInputStream("finalBuildingSensors.owl");
			DataInputStream in = new DataInputStream(fis);
			m.read(in, "");
			String NS = "http://www.sensors.uis.no/ontologies/Ontology1352127234683.owl#";

			OntClass att = m.getOntClass(NS + "Place");
			//
			Individual I1 = m.createIndividual(NS + "ROOMTEST", att);

			String country = "Spain";
			Property prop = m.createProperty(NS + "country");
			m.add(I1, prop, ResourceFactory.createTypedLiteral(country,
					XSDDatatype.XSDstring));

			// m.write(System.out);

			PrintStream p = new PrintStream("./test.owl");
			m.write(p);
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
