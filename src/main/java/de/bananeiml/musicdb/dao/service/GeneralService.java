package de.bananeiml.musicdb.dao.service;

import de.bananeiml.musicdb.dao.entity.Album;
import de.bananeiml.musicdb.dao.entity.Artist;
import de.bananeiml.musicdb.dao.entity.Key;
import de.bananeiml.musicdb.dao.entity.Title;
import de.bananeiml.musicdb.dao.entity.Title.FittingTitles;
import java.util.List;

/**
 *
 * @author Martin Scholl
 */
public interface GeneralService {
    List<Album> getAlbums(final Artist artist);
    List<Title> getTitles(final Artist artist);
    List<Title> getTitles(final Album album);
    Title getTitle(final String name, final Artist artist);
    
    /**
     * Fetch all titles for the given key. <code>null</code> is allowed here. It fetches all titles that have no key
     * information.
     */
    List<Title> getTitles(final Key key);
    FittingTitles getFittingTitles(final Key key);
    
    Key getKey(final String key);
}
