/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Bart Memelink
 */
public class MockEffectenbeurs extends UnicastRemoteObject implements IEffectenbeurs{

    private List<IFonds> fonds = new ArrayList<>();
    private Timer timer;
    private Random random;
    
    public MockEffectenbeurs() throws RemoteException
    {
        ArrayList<IFonds> temps = new ArrayList<IFonds>();
        temps.add(new Fond("Nick", 3));
        temps.add(new Fond("Bart", 7.1));
        temps.add(new Fond("Bananen", 2));
        temps.add(new Fond("Incredible Gentlemen", 2));
        temps.add(new Fond("Skype", 2));
        temps.add(new Fond("Steam", 2));
        temps.add(new Fond("Netbeans", 2));


        this.fonds = temps;
        this.timer = new Timer();
        this.random = new Random();
        StartTimer();
    }
    
    @Override
    public List<IFonds> getKoersen() throws RemoteException {
        return fonds;
    }
    
    private void StartTimer()
    {
        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                 for(IFonds f : fonds)
                   ((Fond) f).setKoers(random.nextInt(99 - 1) /100 + random.nextInt(99 -1));
            }
        }, 0, 8000);
    }
    
    public void StopTimer()
    {
        this.timer.cancel();
    }
    
}
