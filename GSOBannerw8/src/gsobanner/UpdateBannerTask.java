/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.rmi.RemoteException;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickbijmoer
 */
public class UpdateBannerTask extends TimerTask {
private AEXBanner banner;
private IEffectenbeurs beurs;
private List<IFonds> fondslist;

public UpdateBannerTask(AEXBanner banner, IEffectenbeurs beurs)
{
    this.banner = banner;
    this.beurs = beurs;
}
    @Override
    public void run() {
    try {
        fondslist = beurs.getKoersen();
    } catch (RemoteException ex) {
        Logger.getLogger(UpdateBannerTask.class.getName()).log(Level.SEVERE, null, ex);
    }
        String koersen = "";
        for(IFonds fonds : fondslist)
        {
         koersen += fonds.getName() + " " + Double.toString(fonds.getKoers()) + " - ";
        }
        banner.setKoersen(koersen);
    }
    
}
