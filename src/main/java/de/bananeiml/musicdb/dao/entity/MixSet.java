package de.bananeiml.musicdb.dao.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author Martin Scholl
 */
@Entity
@Table(name = "mix_set")
public class MixSet extends CommonEntity implements NamedEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "mix_set_id_seq", sequenceName = "mix_set_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mix_set_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "jt_mix_set_title",
        joinColumns = {@JoinColumn(name = "mix_set_id")},
        inverseJoinColumns = {@JoinColumn(name = "title_id")}
    )
    @IndexColumn(name = "position")
    private List<Title> titles;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(final List<Title> titles) {
        this.titles = titles;
    }
}
