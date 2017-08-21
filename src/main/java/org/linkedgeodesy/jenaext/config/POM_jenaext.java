package org.linkedgeodesy.jenaext.config;

import java.io.IOException;
import org.json.simple.JSONObject;

/**
 * Class for POM details
 */
public class POM_jenaext {

    /**
     * get POM info as JSON
     *
     * @return pom JSON
     * @throws IOException
     */
    public static JSONObject getInfo() throws IOException {
        JSONObject outObj = new JSONObject();
        JSONObject maven = new JSONObject();
        maven.put("modelVersion", JenaExtProperties.getPropertyParam("modelVersion"));
        maven.put("mavenCompilerSource", JenaExtProperties.getPropertyParam("source"));
        maven.put("mavenCompilerTarget", JenaExtProperties.getPropertyParam("target"));
        outObj.put("maven", maven);
        JSONObject project = new JSONObject();
        project.put("buildNumber", JenaExtProperties.getPropertyParam("buildNumber"));
        project.put("buildNumberShort", JenaExtProperties.getPropertyParam("buildNumber").substring(0, 7));
        project.put("buildRepository", JenaExtProperties.getPropertyParam("url").replace(".git", "/tree/" + JenaExtProperties.getPropertyParam("buildNumber")));
        project.put("artifactId", JenaExtProperties.getPropertyParam("artifactId"));
        project.put("groupId", JenaExtProperties.getPropertyParam("groupId"));
        project.put("version", JenaExtProperties.getPropertyParam("version"));
        project.put("packaging", JenaExtProperties.getPropertyParam("packaging"));
        project.put("name", JenaExtProperties.getPropertyParam("name"));
        project.put("description", JenaExtProperties.getPropertyParam("description"));
        project.put("url", JenaExtProperties.getPropertyParam("url"));
        project.put("encoding", JenaExtProperties.getPropertyParam("sourceEncoding"));
        outObj.put("project", project);
        return outObj;
    }

}
