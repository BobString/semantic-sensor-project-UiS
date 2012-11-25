package data;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String coldRooms = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX : <http://www.sensors.uis.no/ontologies/Ontology1352127234683.owl#> "
				+ "SELECT ?room ?country ?quantity ?ts "
				+ "WHERE {	?elemType :dataChoice ?dc ."
				+ "			?dc :item ?i ."
				+ "			?i :location ?room ."
				+ "			?i :datarecord ?dr ."
				+ "			?dr :variable_name :TroomAirdb ."
				+ " 		?dr :quantity ?quantity."
				+ "			?dr :timestamp ?ts ."
				+ "			?room :country ?country } ";

		SemanticQuery s0 = new SemanticQuery();
		System.out.println(s0.execQuery(coldRooms));

		String icedRooms = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX : <http://www.sensors.uis.no/ontologies/Ontology1352127234683.owl#> "
				+ "SELECT ?room ?country ?quantity ?ts "
				+ "WHERE {	?elemType :dataChoice ?dc ."
				+ "			?dc :item ?i ."
				+ "			?i :location ?room ."
				+ "			?i :datarecord ?dr ."
				+ "			?dr :variable_name :Troom_air_db ."
				+ " 		?dr :quantity ?quantity."
				+ "			?dr :timestamp ?ts ."
				+ "			?room :country ?country } ";
		SemanticQuery s = new SemanticQuery();
		System.out.println(s.execQuery(icedRooms));

		String rooms = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX : <http://www.sensors.uis.no/ontologies/Ontology1352127234683.owl#> "
				+ "SELECT ?room ?country "
				+ "WHERE {	?room rdf:type :Place ."
				+ "?room :country ?country. } ";
		SemanticQuery s2 = new SemanticQuery();
		System.out.println(s2.execQuery(rooms));

		String equipmentType = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX : <http://www.sensors.uis.no/ontologies/Ontology1352127234683.owl#> "
				+ "SELECT ?eq ?i ?country "
				+ "WHERE {	?elemType :dataChoice ?dc ."
				+ "			?dc :item ?i ."
				+ " 		?i :location ?room ."
				+ "			?i :equipmentType ?eq ."
				+ "			?room :country ?country } ";
		SemanticQuery s3 = new SemanticQuery();
		System.out.println(s3.execQuery(equipmentType));

		String variables = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX : <http://www.sensors.uis.no/ontologies/Ontology1352127234683.owl#> "
				+ "SELECT ?variable ?desc "
				+ "WHERE {	?variable rdf:type :Variable ."
				+ "?variable :variableDescription ?desc. } ";
		SemanticQuery s4 = new SemanticQuery();
		System.out.println(s4.execQuery(variables));
	}

}
