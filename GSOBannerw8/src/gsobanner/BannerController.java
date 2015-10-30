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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javax.annotation.Resource;

/**
 *
 * @author Bart Memelink
 */
public class BannerController {
    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    public Registry registry = null;
    public String bindingName = "AEX";
    private List<IFonds> fondslist;
    private RMIClient RMIC;


    public BannerController(AEXBanner banner) throws RemoteException {
        this.RMIC = new RMIClient("127.0.0.1", 1099);

        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();
        
        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                String s = "";
                for (IFonds eb : RMIC.GetKoersen()) {
                    s = s + " " + eb.getName() + " " + eb.getKoers();
                }
                final String fs = s;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        banner.setKoersen(fs);
                    }
                });

            }
        }, 0, 1500);
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
        ((MockEffectenbeurs) effectenbeurs).StopTimer();
    }
    
  
    

}
