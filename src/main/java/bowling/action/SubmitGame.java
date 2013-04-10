package bowling.action;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;
 
public class SubmitGame 
    extends ActionSupport implements ServletContextAware{
 
	private static final Logger logger = Logger.getLogger(SubmitGame.class);
	
	ServletContext context;
	
	public String execute() throws Exception {
 
		
		context.getAttribute("");
		return SUCCESS;
		
	}
 
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
}