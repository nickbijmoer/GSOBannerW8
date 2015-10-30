/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.io.Serializable;

/**
 *
 * @author Bart Memelink
 */
public interface IFonds extends Serializable{
    
   String getName();
   double getKoers();
    
}
