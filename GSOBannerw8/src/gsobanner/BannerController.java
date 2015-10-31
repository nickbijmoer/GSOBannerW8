/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sam
 */
public class BannerController implements RemotePropertyListener {

    private final IEffectenbeurs effectenBeurs;
    public AEXBanner AEXbanner;
    private final RMIClient rmiClient;
    private String ipAddress;
    private int portNumber;

    public BannerController(AEXBanner banner) {
        askForData();
        rmiClient = new RMIClient(ipAddress, portNumber);
        effectenBeurs = rmiClient.setUp();
        try {
            UnicastRemoteObject.exportObject(this, portNumber + 1);
            effectenBeurs.addListener(this, null);
        } catch (RemoteException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.AEXbanner = banner;
        if (AEXbanner == null) {
            System.out.println("AEXBANNER ISNULL");
        }
        try {
            AEXbanner.setAmountOfElements(effectenBeurs.getKoersen().size());
        } catch (RemoteException ex) {
        }
    }

    public void askForData() {
        // Welcome message
        System.out.println("CLIENT USING REGISTRY");

        // Get ip address of server
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        ipAddress = input.nextLine();

        // Get port number
        System.out.print("Client: Enter port number: ");
        portNumber = input.nextInt();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        AEXbanner.addFond((Fond) evt.getNewValue());
    }
}
