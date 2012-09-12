/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bananeiml.musicdb.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Martin Scholl
 */
@Entity
public class Key extends CommonEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    
    @Column(name = "key")
    private String key;
    
    @Column(name = "description")
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }
    
    public String getKey() {
        return key;
    }

    protected void setKey(final String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(final String description) {
        this.description = description;
    }
    
    public static FittingKeys getFittingKeys(final Key key){
        if(key.getKey() == null){
            return null;
        }
        
        final char mode = key.getMode();
        final int tone = key.getTone();
        final int lowerTone = getLowerTone(tone);
        final int higherTone = getHigherTone(tone);
        final int pusherTone = getHigherTone(higherTone);
        
        final char oppositeMode;
        if(mode == 'A'){
            oppositeMode = 'B';
        } else if(mode == 'B'){
            oppositeMode = 'A';
        } else {
             throw new IllegalStateException("illegal mode: " + mode); // NOI18N
        }
        
        final FittingKeys fittingKeys = new FittingKeys();
        fittingKeys.base = String.valueOf(tone) + mode;
        fittingKeys.lower = String.valueOf(lowerTone) + mode;
        fittingKeys.higher = String.valueOf(higherTone) + mode;
        fittingKeys.pusher = String.valueOf(pusherTone) + mode;
        fittingKeys.opposite = String.valueOf(tone) + oppositeMode;
        
        return fittingKeys;
    }
    
    public static FittingKeys getFittingKeys(final String key){
        final Key dummyKey = new Key();
        dummyKey.setKey(key);
        
        return getFittingKeys(dummyKey);
    }
    
    public int getTone(){
        final String keyString = getKey();
        
        if(keyString == null){
            throw new IllegalStateException("key has not key string: " + key); // NOI18N
        }
        
        if(keyString.length() < 2 || keyString.length() > 3){
            throw new IllegalStateException("illegal key string: " + keyString); // NOI18N
        }
        
        try{
            return Integer.parseInt(keyString.substring(0, keyString.length() - 1));
        } catch(final NumberFormatException e){
            throw new IllegalStateException("illegal key string: " + keyString, e); // NOI18N
        }
    }
    
    public char getMode(){
        final String keyString = getKey();
        
        if(keyString == null){
            throw new IllegalStateException("key has not key string: " + key); // NOI18N
        }
        
        if(keyString.length() < 2 || keyString.length() > 3){
            throw new IllegalStateException("illegal key string: " + keyString); // NOI18N
        }
        
        return keyString.toUpperCase().charAt(keyString.length() - 1);
    }
    
    public static int getLowerTone(final int tone){
        if(tone < 1 || tone > 12){
            throw new IllegalArgumentException("illegal tone: " + tone); // NOI18N
        }
        
        final int lowerTone = tone - 1;
        if(lowerTone < 1){
            return 12;
        } else {
            return lowerTone;
        }
    }
    
    public static int getHigherTone(final int tone){
        if(tone < 1 || tone > 12){
            throw new IllegalArgumentException("illegal tone: " + tone); // NOI18N
        }
        
        final int lowerTone = tone + 1;
        if(lowerTone > 12){
            return 1;
        } else {
            return lowerTone;
        }
    }
    
    public static final class FittingKeys{
        public String base, lower, higher, opposite, pusher;
    }
}
