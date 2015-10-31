/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bart Memelink
 */
public interface IEffectenbeurs extends Remote, RemotePublisher{
    
    ArrayList<Fond> getKoersen() throws RemoteException;
    
     @Override
    void addListener(RemotePropertyListener listener, String property)
            throws RemoteException;
    @Override
    void removeListener(RemotePropertyListener listener, String property)
            throws RemoteException;
}
