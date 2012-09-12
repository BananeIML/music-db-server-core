package de.bananeiml.musicdb.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Martin Scholl
 */
@Entity
public class Artist extends CommonEntity implements NamedEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "artist_id_seq", sequenceName = "artist_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }
}
