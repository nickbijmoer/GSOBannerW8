/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bart Memelink
 */
public class Effectenbeurs implements IEffectenbeurs, Serializable {
    private List<IFonds> fondsList = new ArrayList<>();
    
    @Override
    public List<IFonds> getKoersen() {
        return fondsList;
    }
    
}
