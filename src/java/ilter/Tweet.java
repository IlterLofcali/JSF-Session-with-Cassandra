
package ilter;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.datastax.driver.core.Row;
/**
 *
 * @author ilter
 */

@ManagedBean(name="tweet")
@SessionScoped
public class Tweet implements Serializable {
    Session s_t=null;

    String body,username;
    int user_id;
    
    String query = "INSERT INTO users.user_tweet_table VALUES (:body, u_id , t_id) WHERE user_table.u_id =user_tweet_table.u_id  ";
    public void add(){
                    
        try{

           s_t=  Database.getSession();
          PreparedStatement ps = s_t.prepare(query);
          
          BoundStatement bound = ps.bind()
                  .setString("body", body)
                  .setInt("u_id", user_id)
                  .setString("uname",username);
                  s_t.execute(bound);
    
    }finally{
            Database.close(s_t);
}

}
    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser_id(int user_id) {
        UserDAO.data_id = user_id;
    }
//
//    public Date getTweet_date() {
//        return tweet_date;
//    }
//
//    public void setTweet_date(Date tweet_date) {
//        this.tweet_date = tweet_date;
//    }
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
public List<ArrayListSource>getTweet(){
ResultSet rs;
Session s_d = null;
String query ="SELECT body, u_id, uname FROM user_tweet_table WHERE user_tweet_table.u_id = user_table.u_id allow fitering";
List<ArrayListSource> newList = new ArrayList<>();
  try{
s_d = Database.getSession();
rs = s_d.execute(query);
for(Row row : rs){
ArrayListSource array = new ArrayListSource();
array.setBody(row.getString("body"));
array.setU_id(row.getInt("u_id"));
array.setUsername(row.getString("uname"));
newList.add(array);
}
}finally{
         Database.close(s_d);
        }
  return newList;
}    
}
