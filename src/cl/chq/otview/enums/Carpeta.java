/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.chq.otview.enums;

/**
 *
 * @author pzunigap
 */
public enum Carpeta {
    DELETE(3), OUTBOK(4), SEND(5), INBOX(6), CALENDAR(9), CONTACTS(10), JOURNAL(11), NOTES(12);

    private final int id;
    
    Carpeta(int id) { 
        this.id = id; 
    }
    
    public int getValue() { 
        return id; 
    }
} 