CREATE DATABASE manga_db;
USE manga_db;

-- USERS Table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL
);

-- MANGA Table
CREATE TABLE manga (
    manga_id INT PRIMARY KEY AUTO_INCREMENT,
    mangatitle VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    mangadescription TEXT
);

-- GENRE Table
CREATE TABLE genre (
    genre_id INT PRIMARY KEY AUTO_INCREMENT,
    genrename VARCHAR(100) UNIQUE NOT NULL
);

-- MANGA_GENRE (Linking Table for Manga and Genre)
CREATE TABLE manga_genre (
    manga_id INT,
    genre_id INT,
    PRIMARY KEY (manga_id, genre_id),
    FOREIGN KEY (manga_id) REFERENCES manga(manga_id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre(genre_id) ON DELETE CASCADE
);

-- VOLUME Table
CREATE TABLE volume (
    volume_id INT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(20) UNIQUE,
    volumenumber INT NOT NULL,
    releasedate DATE,
    manga_id INT,
    FOREIGN KEY (manga_id) REFERENCES manga(manga_id) ON DELETE CASCADE
);

-- CHAPTER Table
CREATE TABLE chapter (
    chapter_id INT PRIMARY KEY AUTO_INCREMENT,
    chapterno INT NOT NULL,
    chaptertitle VARCHAR(200),
    volume_id INT,
    FOREIGN KEY (volume_id) REFERENCES volume(volume_id) ON DELETE CASCADE
);

-- REVIEW Table
CREATE TABLE review (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    reviewdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT,
    manga_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (manga_id) REFERENCES manga(manga_id) ON DELETE CASCADE
);

-- USER_MANGA Table (to track follows or bookmarks)
CREATE TABLE user_manga (
    user_id INT,
    manga_id INT,
    PRIMARY KEY (user_id, manga_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (manga_id) REFERENCES manga(manga_id) ON DELETE CASCADE
);
