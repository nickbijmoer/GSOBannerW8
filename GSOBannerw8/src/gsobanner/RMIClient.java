/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Nick
 */
public class RMIClient {

    // Set binding name for student administration
    private static final String bindingName = "mockEffectenBeurs";

    // References to registry and student administration
    private Registry registry = null;
    private IEffectenbeurs effectebeurs = null;

    private String ipAddress;
    private int portNumber;

    // Constructor
    public RMIClient(String ipAddress, int portNumber) {
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
    }

    public IEffectenbeurs setUp() {
        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Print result locating registry
        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        // Bind student administration using registry
        if (registry != null) {
            try {
                effectebeurs = (IEffectenbeurs) registry.lookup(bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind effectenbeurs");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                effectebeurs = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind effectenbeurs");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                effectebeurs = null;
            }
        }

        // Print result binding student administration
        if (effectebeurs != null) {
            System.out.println("Client: Effectenbeurs bound");
        } else {
            System.out.println("Client: Effectenbeurs is null pointer");
        }

        // Test RMI connection
        if (effectebeurs != null) {
            return effectebeurs;
        } else {
            return null;
        }
    }
}
