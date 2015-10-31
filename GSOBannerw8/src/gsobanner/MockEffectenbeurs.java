/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.*;

/**
 *
 * @author Bart Memelink
 */
public class MockEffectenbeurs extends UnicastRemoteObject implements IEffectenbeurs, RemotePublisher{

    public ArrayList<Fond> fondsList;
    private transient Timer fondsTimer;
    private BasicPublisher basicPublisher;

    public MockEffectenbeurs() throws RemoteException {
        fondsList = new ArrayList<>();

        fondsList.add(new Fond("Nick", 300.0));
        fondsList.add(new Fond("Turtle", 400.0));
        fondsList.add(new Fond("Bart", 200.0));
        fondsList.add(new Fond("Skype", 150.0));

        basicPublisher = new BasicPublisher(new String[]{"koers"});

        fondsTimer = new Timer();
        fondsTimer.scheduleAtFixedRate(new fondsCalculator(), 0, 500);

    }

    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {
        basicPublisher.addListener(listener, null);
    }

    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {
        basicPublisher.removeListener(listener, null);
    }

    @Override
    public ArrayList<Fond> getKoersen() throws RemoteException {
        return fondsList;
    }

 

    class fondsCalculator extends TimerTask {

        @Override
        public void run() {
            Random randomizer = new Random();

            for (Fond fonds : fondsList) {
                int randomInt = randomizer.nextInt(1000);
                double randomDouble = randomizer.nextDouble();

                randomDouble = (double) Math.round(randomDouble * 10) / 10;

                fonds.setKoers(randomInt + randomDouble);
                fonds.setName(randomInt + "");

                informListener(fonds);

            }
        }
    }
    
    public void informListener(Fond fonds) {
        System.out.println("INFORMING");
        basicPublisher.inform(this, null, null, fonds);
    }
}