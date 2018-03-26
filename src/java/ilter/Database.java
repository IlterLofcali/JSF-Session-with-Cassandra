/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilter;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import java.sql.Connection;

/**
 *
 * @author ilter
 */
public class Database {                
    static Session cassandraSession;

    protected static Session getSession() {
        if(cassandraSession == null){
            cassandraSession = CreateSession();
        }
  return cassandraSession;
        }
    
    public static Session CreateSession(){
Cluster c = Cluster.builder().addContactPoint("localhost").build();
  return c.connect("users");
}
    public static void close(Session  son) {
		try {
			son.close();
		} catch (Exception ex) {
		}
	}
}
