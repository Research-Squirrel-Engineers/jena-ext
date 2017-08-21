package org.linkedgeodesy.jenaext.run;

import org.linkedgeodesy.jenaext.config.POM_jenaext;
import org.linkedgeodesy.jenaext.log.Logging;
import java.io.IOException;
import java.sql.SQLException;

/**
 * main class
 * @author thiery
 */
public class Main {

    /**
     * main method
     * @param args
     * @throws IOException
     * @throws SQLException 
     */
    public static void main(String[] args) throws IOException, SQLException {
        String libinfo = "";
        try {
            libinfo = POM_jenaext.getInfo().toJSONString();
            System.out.println(libinfo);
        } catch (Exception e) {
            libinfo = Logging.getMessageJSON(e, "org.linkedgeodesy.jenaext.run.Main");
            System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.jenaext.run.Main"));
        }
    }

}
