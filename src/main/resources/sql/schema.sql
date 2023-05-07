DROP schema if exists cinema CASCADE;
CREATE SCHEMA IF NOT EXISTS cinema;

CREATE TABLE IF NOT EXISTS cinema.admins (
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cinema.movie_halls(
    id              int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    serial_number   int UNIQUE NOT NULL CHECK (serial_number >= 0),
    seats           int NOT NULL CHECK (seats >= 0),
    admin_id        int,
    FOREIGN KEY (admin_id) REFERENCES cinema.admins (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS cinema.posters(
    id      int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    VARCHAR(255) NOT NULL DEFAULT 'no_poster',
    path    VARCHAR(255) NOT NULL,
    size    bigint NOT NULL DEFAULT 0 CHECK (size >= 0),
    mime    text,
    date    timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    admin_id int,
    FOREIGN KEY (admin_id) REFERENCES cinema.admins (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS cinema.movies(
    id              int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title           text NOT NULL,
    year_of_release int NOT NULL CHECK (year_of_release >= 1900 AND year_of_release <= date_part ('year', CURRENT_DATE)),
    age_restriction int NOT NULL CHECK (age_restriction >= 0 AND age_restriction <= 150),
    description     text,
    poster_id       int,
    admin_id        int,
    FOREIGN KEY (poster_id) REFERENCES cinema.posters (id) ON DELETE SET NULL,
    FOREIGN KEY (admin_id) REFERENCES cinema.admins (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS cinema.sessions(
    id              int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    price           DECIMAL(12, 2),
    date_time       timestamp,
    movie_id        int,
    movie_hall_id   int,
    admin_id        int,
    FOREIGN KEY (admin_id) REFERENCES cinema.admins (id) ON DELETE SET NULL,
    FOREIGN KEY (movie_id) REFERENCES cinema.movies (id) ON DELETE SET NULL ,
    FOREIGN KEY (movie_hall_id) REFERENCES cinema.movie_halls (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS cinema.chat_messages(
    id              bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type            int NOT NULL,
    content         text,
    sender          VARCHAR(255) NOT NULL,
    movie_id        int REFERENCES cinema.movies (id) ON DELETE CASCADE,
    date_time       timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS cinema.authentications
(
    id          bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ip          VARCHAR(48) NOT NULL,
    date_time   timestamp DEFAULT CURRENT_TIMESTAMP,
    admin_id    int NOT NULL,
    FOREIGN KEY (admin_id) REFERENCES cinema.admins (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cinema.avatars
(
    id          VARCHAR(255) NOT NULL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    path        VARCHAR(255) NOT NULL,
    size        INT DEFAULT 0,
    mime        TEXT,
    date_time   timestamp DEFAULT CURRENT_TIMESTAMP,
    admin_id    int NOT NULL,
    FOREIGN KEY (admin_id) REFERENCES cinema.admins (id) ON DELETE CASCADE
);
