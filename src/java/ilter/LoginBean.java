
package ilter;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 *
 * @author ilter
 */

@ManagedBean(name = "loginBean" )
@SessionScoped
public class LoginBean implements Serializable{
    
    static String uname;
    static String pass;
    static int id;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        LoginBean.pass = pass;
    }
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
       LoginBean.uname = uname;
    }
    public String loginProject() {
        
        boolean result = UserDAO.login(uname, pass);                   //fonksiyonun içinde problem var.
        if (result) {                                                               //True döndü
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("uname", uname);
            return "success";            
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
            return "invalid";
        }
    }
 
    public String logout() {
      HttpSession session = Util.getSession();
      session.invalidate();
      return "home";
   }
   
}
