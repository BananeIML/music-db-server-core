package de.bananeiml.musicdb.dao.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Martin Scholl
 */
@Entity
public class Title extends CommonEntity implements NamedEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "title_id_seq", sequenceName = "title_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "title_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "bpm")
    private Integer bpm;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "key_id")
    private Key key;
    
    // TODO: fix cascades
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "artist_id")
    private Artist artist;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(final Integer bpm) {
        this.bpm = bpm;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(final Key key) {
        this.key = key;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(final Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(final Artist artist) {
        this.artist = artist;
    }
    
    public static final class FittingTitles {
        public List<Title> base, lower, higher, pusher, opposite;
    }
}
