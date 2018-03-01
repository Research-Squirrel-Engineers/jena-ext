package org.linkedgeodesy.jenaext.model;

import com.github.jsonldjava.jena.JenaJSONLD;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.linkedgeodesy.jenaext.log.JenaModelException;

/**
 * CLASS for set up a JenaModel graph and export it
 */
public class JenaModel {

    private Model model;

    /**
     * init Jena Model Object
     *
     * @throws JenaModelException
     */
    public JenaModel() throws JenaModelException {
        model = ModelFactory.createDefaultModel();
        setPrefixes();
    }

    /**
     * read RDF from String
     *
     * @param rdf
     * @param format
     * @throws JenaModelException
     */
    public void readRDF(String rdf, Lang format) throws JenaModelException {
        try {
            RDFDataMgr.read(model, new ByteArrayInputStream(rdf.getBytes()), null, format);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * read JSON-LD from String
     *
     * @param jsonld
     * @throws JenaModelException
     */
    public void readJSONLD(String jsonld) throws JenaModelException {
        try {
            RDFDataMgr.read(model, new ByteArrayInputStream(jsonld.getBytes()), null, Lang.JSONLD);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * set model to existing model
     *
     * @param model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * set triple with literal as object
     *
     * @param subject
     * @param predicate
     * @param object
     * @throws JenaModelException
     */
    public void setLiteral(String subject, String predicate, String object) throws JenaModelException {
        try {
            Resource s = model.createResource(subject);
            Property p = model.createProperty(predicate);
            Literal o = model.createLiteral(object);
            model.add(s, p, o);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * set triple with literal and language as object
     *
     * @param subject
     * @param predicate
     * @param object
     * @param lang
     * @throws JenaModelException
     */
    public void setLiteralWithLanguage(String subject, String predicate, String object, String lang) throws JenaModelException {
        try {
            Resource s = model.createResource(subject);
            Property p = model.createProperty(predicate);
            Literal o = model.createLiteral(object, lang);
            model.add(s, p, o);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * set triple with uri as object
     *
     * @param subject
     * @param predicate
     * @param object
     * @throws JenaModelException
     */
    public void setURI(String subject, String predicate, String object) throws JenaModelException {
        try {
            Resource s = model.createResource(subject);
            Property p = model.createProperty(predicate);
            Resource o = model.createResource(object);
            model.add(s, p, o);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * set triple
     *
     * @param subject
     * @param predicate
     * @param object
     * @throws JenaModelException
     */
    public void setTriple(String subject, String predicate, String object) throws JenaModelException {
        try {
            if (object.startsWith("http://") || object.contains("mailto")) {
                setURI(subject, predicate, object);
            } else if (object.contains("@")) {
                String literalLanguage[] = object.split("@");
                setLiteralWithLanguage(subject, predicate, literalLanguage[0].replaceAll("\"", ""), literalLanguage[1]);
            } else {
                setLiteral(subject, predicate, object.replaceAll("\"", ""));
            }
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * get model
     *
     * @return
     */
    public Model getModel() {
        return model;
    }

    /**
     * get model as RDF in different formats
     *
     * @param format
     * @return
     * @throws UnsupportedEncodingException
     * @throws JenaModelException
     */
    public String getModelAsRDFFormatedString(String format) throws UnsupportedEncodingException, JenaModelException {
        // https://jena.apache.org/documentation/io/rdf-output.html#jena_model_write_formats
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, format);
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * get model as RDF/XML
     *
     * @return
     * @throws JenaModelException
     */
    public String getModelAsRDFXML() throws JenaModelException {
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, "RDF/XML");
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * get model as TURTLE
     *
     * @return
     * @throws JenaModelException
     */
    public String getModelAsTURTLE() throws JenaModelException {
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, "TURTLE");
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * get model as N-TRIPLES
     *
     * @return
     * @throws JenaModelException
     */
    public String getModelAsNTRIPLES() throws JenaModelException {
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, "N-TRIPLES");
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * get model as JSON-LD
     *
     * @return
     * @throws UnsupportedEncodingException
     * @throws JenaModelException
     */
    public String getModelAsJSONLD() throws UnsupportedEncodingException, JenaModelException {
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, "JSON-LD");
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * get model as N3
     *
     * @return
     * @throws JenaModelException
     */
    public String getModelAsN3() throws JenaModelException {
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, "N3");
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * get model as N3
     *
     * @return
     * @throws JenaModelException
     */
    public String getModelAsRDFJSON() throws JenaModelException {
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, "RDF/JSON");
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * get JSON-LD @context by url
     *
     * @param url
     * @return
     * @throws IOException
     * @throws JenaModelException
     */
    public static JSONObject getJSONLDContextByURL(String url) throws IOException, JenaModelException {
        try {
            // read GeoJSON-LD Context
            JSONObject data = new JSONObject();
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                data = (JSONObject) new JSONParser().parse(response.toString());
            }
            JSONObject context = (JSONObject) data.get("@context");
            return context;
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    /**
     * set model prefixes from prefixes.csv
     *
     * @throws JenaModelException
     */
    private void setPrefixes() throws JenaModelException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("prefixes.csv").getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");
                model.setNsPrefix(split[0], split[1]);
            }
            scanner.close();
        } catch (IOException e) {
            throw new JenaModelException(e.getMessage());
        }
    }

}
