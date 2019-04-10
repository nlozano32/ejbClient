import com.adexflow.EjbExample;
import com.adexflow.EjbExampleIf;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Main {

    private static final String PKG_INTERFACES = "org.wildfly.naming.client.WildFlyInitialContextFactory";

    public static void main(String[] args) {


        try{

            String appName = "";
            String moduleName = "testejb01jar";
            String distinctName = "";
            String beanName = EjbExample.class.getSimpleName();
            String interfaceName = EjbExampleIf.class.getName();
            String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;

            Properties result = new Properties();
            result.put(Context.INITIAL_CONTEXT_FACTORY, PKG_INTERFACES);
            result.put(Context.URL_PKG_PREFIXES, "org.wildfly.ejb.client.naming");
            result.put(Context.PROVIDER_URL,"http-remoting://127.0.0.1:8080");
            result.put(Context.SECURITY_PRINCIPAL, "your_user");
            result.put(Context.SECURITY_CREDENTIALS, "your_password");
            result.put("wildfly.naming.client.ejb.context", false);

            Context ctx = new InitialContext (result);

            EjbExampleIf testEJB = (EjbExampleIf)ctx.lookup(name);

            System.out.println(testEJB.getMessage ());
        }catch(Exception e){

            e.printStackTrace();
        }


    }
}
