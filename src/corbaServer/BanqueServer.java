package corbaServer;

import javax.naming.Context;
import javax.naming.InitialContext;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import service.BanqueImpl;

public class BanqueServer {
    public static void main(String[] args) {
        try {
            // a. initialiser l'ORB
            ORB orb = ORB.init(args, null);

            // b. RootPOA + activation du POAManager
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            poa.the_POAManager().activate();

            // c. annuaire JNDI
            Context ctx = new InitialContext();

            // d. créer le servant
            BanqueImpl od = new BanqueImpl();

            // e. publier la référence
            ctx.rebind("BK", poa.servant_to_reference(od));
            System.out.println("Serveur prêt");

            // f. attendre les clients
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}