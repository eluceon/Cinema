DROP schema if exists ex00 CASCADE;
CREATE SCHEMA IF NOT EXISTS ex00;

CREATE TABLE IF NOT EXISTS ex00.admins (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ex00.movie_halls(
    id              int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    serial_number   int UNIQUE NOT NULL,
    seats           int NOT NULL CHECK (seats > 0),
    admin_id        int,
    FOREIGN KEY (admin_id) REFERENCES ex00.admins (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS ex00.posters(
    id      int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    VARCHAR(255) DEFAULT 'no_poster',
    path    VARCHAR(255) NOT NULL,
    size    INT DEFAULT 0,
    mime    text,
    date    timestamp DEFAULT CURRENT_TIMESTAMP,
    admin_id int,
    FOREIGN KEY (admin_id) REFERENCES ex00.admins (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS ex00.movies(
    id              int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title           text NOT NULL,
    year_of_release int NOT NULL CHECK (year_of_release > 1990 AND year_of_release <= date_part ('year', CURRENT_DATE)),
    age_restriction int NOT NULL CHECK (age_restriction >= 0 AND age_restriction < 150),
    description     text,
    poster_id       int,
    admin_id        int,
    FOREIGN KEY (poster_id) REFERENCES ex00.posters (id) ON DELETE SET NULL,
    FOREIGN KEY (admin_id) REFERENCES ex00.admins (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS ex00.sessions(
    id              int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    price           DECIMAL(12, 2),
    date_time       timestamp DEFAULT CURRENT_TIMESTAMP,
    movie_id        int,
    movie_hall_id   int,
    admin_id        int,
    FOREIGN KEY (admin_id) REFERENCES ex00.admins (id) ON DELETE SET NULL,
    FOREIGN KEY (movie_id) REFERENCES ex00.movies (id) ON DELETE SET NULL ,
    FOREIGN KEY (movie_hall_id) REFERENCES ex00.movie_halls (id) ON DELETE SET NULL
);
