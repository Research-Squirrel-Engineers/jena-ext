package org.linkedgeodesy.jenaext.config;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing Class
 * @author thiery
 */
public class POM_jenaextTest {
    
    public POM_jenaextTest() {
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
    public void testPOMInfoNotNull() throws Exception {
        System.out.println("testPOMInfoNotNull");
        JSONObject info = POM_jenaext.getInfo();
        assertNotNull(info);
    }
    
    @Test
    public void testLoadPomInfoAndPackagingIsJAR() throws Exception {
        System.out.println("testLoadPomInfoAndPackagingIsJAR");
        String packaging = JenaExtProperties.getPropertyParam("packaging");
        assertEquals("jar",packaging);
    }
    
}
