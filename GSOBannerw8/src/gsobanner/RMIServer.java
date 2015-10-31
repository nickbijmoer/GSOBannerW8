/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Martijn
 */
public class RMIServer {

    // Set port number
    private static final int portNumber = 1099;

    // Set binding name for MockEffectenbeurs
    private static final String bindingName = "mockEffectenBeurs";

    // References to registry and MockEffectenbeurs
    private Registry registry = null;
    private MockEffectenbeurs mockEffectenBeurs = null;

    // Constructor
    public RMIServer() {

        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        // Create MockEffectenbeurs
        try {
            mockEffectenBeurs = new MockEffectenbeurs();
            System.out.println("Server: MockEffectenbeurs created");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create MockEffectenbeurs");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            mockEffectenBeurs = null;
        }

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind MockEffectenbeurs using registry
        try {
            registry.rebind(bindingName, mockEffectenBeurs);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind MockEffectenbeurs");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Welcome message
        System.out.println("SERVER USING REGISTRY");

        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server: IP Address: " + localhost.getHostAddress());

        // Create server
        RMIServer server = new RMIServer();
    }
}
