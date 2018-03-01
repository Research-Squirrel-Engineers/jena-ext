package org.linkedgeodesy.jenaext.model;

import java.io.UnsupportedEncodingException;
import org.apache.jena.riot.Lang;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.linkedgeodesy.jenaext.log.JenaModelException;

public class JenaModelTest {

    public JenaModelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSetTripleManuallyAndGetModelOutput() throws JenaModelException, ParseException, UnsupportedEncodingException {
        System.out.println("test set triples manually and get model output");
        JenaModel jm = new JenaModel();
        jm.setLiteral("http://example.org#Spiderman", "http://xmlns.com/foaf/0.1/name", "Spiderman");
        jm.setLiteralWithLanguage("http://example.org#GreenGoblin", "http://xmlns.com/foaf/0.1/name", "Grüner Kobild", "de");
        jm.setURI("http://example.org#Spiderman", "http://www.perceive.net/schemas/relationship/enemyOf>", "http://example.org#GreenGoblin");
        jm.setTriple("http://example.org#GreenGoblin", "http://xmlns.com/foaf/0.1/name", "\"Green Goblin\"@en");
        JSONObject obj = (JSONObject) new JSONParser().parse(jm.getModelAsJSONLD());
        JSONArray graph = (JSONArray) obj.get("@graph");
        for (Object item : graph) {
            JSONObject tmp = (JSONObject) item;
            if (tmp.get("@id").equals("http://example.org#Spiderman")) {
                assertEquals("http://example.org#Spiderman", tmp.get("@id"));
                System.out.println("assertEquals(\"http://example.org#Spiderman\", tmp.get(\"@id\"));");
                assertEquals("Spiderman", tmp.get("foaf:name"));
                System.out.println("assertEquals(\"Spiderman\", tmp.get(\"foaf:name\"));");
                assertEquals("http://example.org#GreenGoblin", tmp.get(""));
                System.out.println("assertEquals(\"http://example.org#GreenGoblin\", tmp.get(\"\"));");
            }
            if (tmp.get("@id").equals("http://example.org#GreenGoblin")) {
                assertEquals("http://example.org#GreenGoblin", tmp.get("@id"));
                System.out.println("assertEquals(\"http://example.org#GreenGoblin\", tmp.get(\"@id\"));");
                JSONArray names = (JSONArray) tmp.get("foaf:name");
                for (Object name : names) {
                    JSONObject tmp2 = (JSONObject) name;
                    if (tmp2.get("@value").equals("Green Goblin")) {
                        assertEquals("Green Goblin", tmp2.get("@value"));
                        System.out.println("assertEquals(\"Green Goblin\", tmp2.get(\"@value\"));");
                        assertEquals("en", tmp2.get("@language"));
                        System.out.println("assertEquals(\"en\", tmp2.get(\"@language\"));");
                    }
                    if (tmp2.get("@value").equals("Grüner Kobild")) {
                        assertEquals("Grüner Kobild", tmp2.get("@value"));
                        System.out.println("assertEquals(\"Grüner Kobild\", tmp2.get(\"@value\"));");
                        assertEquals("de", tmp2.get("@language"));
                        System.out.println("assertEquals(\"de\", tmp2.get(\"@language\"));");
                    }
                }
            }
        }
    }

    @Test
    public void testReadTurtleWriteJSONLDReadJSONLDWriteNTriples() throws Exception {
        System.out.println("test read in turtle, write json-ld, read in json-ld, write N-Triples");
        // read in turtle, write json-ld, read in json-ld, write N-Triples
        String turtle = "<http://example.org/#spiderman> <http://www.perceive.net/schemas/relationship/enemyOf> <http://example.org/#green-goblin> ; <http://xmlns.com/foaf/0.1/name> \"Spiderman\" .";
        JenaModel jm = new JenaModel();
        JenaModel jm2 = new JenaModel();
        jm.readRDF(turtle, Lang.TURTLE);
        String first = jm.getModelAsRDFFormatedString("JSON-LD");
        String turtleld = jm.getModelAsRDFFormatedString("JSON-LD");
        jm2.readRDF(turtleld, Lang.JSONLD);
        String second = jm2.getModelAsRDFJSON();
    }

}
