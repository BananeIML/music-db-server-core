BEGIN;

CREATE TABLE artist (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE key (
    id BIGSERIAL PRIMARY KEY,
    key VARCHAR(3) NOT NULL,
    description VARCHAR(50) NOT NULL
);

CREATE TABLE album (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    artist_id BIGINT NOT NULL,

    FOREIGN KEY (artist_id) REFERENCES artist
);
    
CREATE TABLE title (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    key_id BIGINT,
    bpm INT,
    artist_id BIGINT NOT NULL,
    album_id BIGINT,

    FOREIGN KEY (key_id) REFERENCES key,
    FOREIGN KEY (artist_id) REFERENCES artist,
    FOREIGN KEY (album_id) REFERENCES album
);

CREATE TABLE mix_set (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT
);

CREATE TABLE jt_mix_set_title (
    id BIGSERIAL PRIMARY KEY,
    mix_set_id BIGINT,
    title_id BIGINT,
    position INT,
    
    FOREIGN KEY (mix_set_id) REFERENCES mix_set,
    FOREIGN KEY (title_id) REFERENCES title
);

COMMIT;

BEGIN;

INSERT INTO key (key, description) VALUES ('1A', 'A-Flat Minor');
INSERT INTO key (key, description) VALUES ('2A', 'E-Flat Minor');
INSERT INTO key (key, description) VALUES ('3A', 'B-Flat Minor');
INSERT INTO key (key, description) VALUES ('4A', 'F Minor');
INSERT INTO key (key, description) VALUES ('5A', 'C Minor');
INSERT INTO key (key, description) VALUES ('6A', 'G Minor');
INSERT INTO key (key, description) VALUES ('7A', 'D Minor');
INSERT INTO key (key, description) VALUES ('8A', 'A Minor');
INSERT INTO key (key, description) VALUES ('9A', 'E Minor');
INSERT INTO key (key, description) VALUES ('10A', 'B Minor');
INSERT INTO key (key, description) VALUES ('11A', 'G-Flat Minor');
INSERT INTO key (key, description) VALUES ('12A', 'D-Flat Minor');

INSERT INTO key (key, description) VALUES ('1B', 'B Major');
INSERT INTO key (key, description) VALUES ('2B', 'F-Sharp Major');
INSERT INTO key (key, description) VALUES ('3B', 'D-Flat Major');
INSERT INTO key (key, description) VALUES ('4B', 'A-Flat Major');
INSERT INTO key (key, description) VALUES ('5B', 'E-Flat Major');
INSERT INTO key (key, description) VALUES ('6B', 'B-Flat Major');
INSERT INTO key (key, description) VALUES ('7B', 'F Major');
INSERT INTO key (key, description) VALUES ('8B', 'C Major');
INSERT INTO key (key, description) VALUES ('9B', 'G Major');
INSERT INTO key (key, description) VALUES ('10B', 'D Major');
INSERT INTO key (key, description) VALUES ('11B', 'A Major');
INSERT INTO key (key, description) VALUES ('12B', 'E Major');

COMMIT;