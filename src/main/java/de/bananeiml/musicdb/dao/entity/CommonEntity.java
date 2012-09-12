package de.bananeiml.musicdb.dao.entity;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author Martin Scholl
 */
public abstract class CommonEntity implements Serializable {
    
    public abstract Long getId();
    public abstract void setId(final Long id);
    
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof CommonEntity)) {
            return false;
        }
        
        final CommonEntity other = (CommonEntity) object;
        if ((this.getId() == null && other.getId() != null) 
                || (getId() != null && !getId().equals(other.getId()))) {
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" ["); // NOI18N
        
        try {
            final Field[] fields = this.getClass().getDeclaredFields();
            for(final Field f : fields){
                f.setAccessible(true);
                sb.append(f.getName()).append('=').append(f.get(this));
                sb.append('|');
            }
            
            if(sb.charAt(sb.length() - 1) == '|'){
                sb.deleteCharAt(sb.length() - 1);
            }
        } catch (final Exception e){
            sb.append("Exception="); // NOI18N
            sb.append(e.getMessage());
        }
        
        sb.append(']');
        
        return sb.toString();
    }
}
