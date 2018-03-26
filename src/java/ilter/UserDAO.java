
package ilter;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.List;
/**
 *
 * @author ilter
 */
public class UserDAO {
    String uname;
    String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getU_name() {
        return uname;
    }

    public void setU_name(String uname) {
        this.uname= uname;
    }
    Cluster c=null;
    static List<UserDAO> myList;
    static Session ses = null;
    static String query;
    static String data_name;
    static String data_pass;
    static int data_id;
    static int temp;
    public static boolean login(String uname, String pass){   //Login sayfası boolean olmalı 
    query = "SELECT pass, uname,u_id FROM users.user_table WHERE db_id = ? allow filtering";
    try{
         ses = Database.getSession();                   //Database bağlantısı içerisinde hata var.    public static Session getSession() kısmı hatalı ****
         PreparedStatement ps = ses.prepare(query);
            ResultSet rs = ses.execute(ps.bind(1));
            for(Row row : rs){
                data_name = row.getString("uname");             //Database'den gelen uname
                data_pass = row.getString("pass");                  //Database'den gelen pass
                 if(uname.equals(data_name) && pass.equals(data_pass)){
                 
                data_id = row.getInt("u_id");
                System.out.println(data_id);
                return true;
                    }
            }
           
//         PreparedStatement ps = ses.prepare(query);
//         ResultSet rs = ses.execute(ps.bind(1));
//         Row row = rs.one();
//         data = row.getString("uname");
//         
//         if(uname.equals(data)){
//         return true;
//         }
    }    
    catch (Exception e) {
                                        System.out.println("Login error -->" + e.getMessage());
	return false;
    }
    finally{
    Database.close(ses);
    }
    return false;
    }
}