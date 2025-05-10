-- Table for users
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(50),
    email    VARCHAR(100),
    role     VARCHAR(100) NOT NULL
);
-- Table for trails
CREATE TABLE trails
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100)  NOT NULL,
    location   VARCHAR(150)  NOT NULL,
    type       VARCHAR(30)   NULL,
    biome      VARCHAR(30)   NULL,
    difficulty VARCHAR(20)   NULL,
    length     DECIMAL(5, 2) NULL,
    image      VARCHAR(100)  NULL
);
-- Table for multiple friends
CREATE TABLE friends
(
    user1_id  INT NOT NULL,
    friend_id INT NOT NULL,
    PRIMARY KEY (user1_id, friend_id),
    FOREIGN KEY (user1_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES users (id) ON DELETE CASCADE
);
-- Table for favorite trails
CREATE TABLE favorites
(
    user_id  INT NOT NULL,
    trail_id INT NOT NULL,
    PRIMARY KEY (user_id, trail_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (trail_id) REFERENCES trails (id) ON DELETE CASCADE
);
-- Tables for keeping sessions and their data
CREATE TABLE trekker.sessions
(
    user1_id    BIGINT       NOT NULL,
    user2_id    BIGINT,
    invite_code VARCHAR(255) NOT NULL UNIQUE,
    state       VARCHAR(50)  NOT NULL,
    length_min  DOUBLE PRECISION,
    length_max  DOUBLE PRECISION,
    difficulty  VARCHAR(50),
    biome       VARCHAR(50),
    PRIMARY KEY (user1_id, user2_id),
    FOREIGN KEY (user1_id) REFERENCES trekker.users (id),
    FOREIGN KEY (user2_id) REFERENCES trekker.users (id)
);

CREATE TABLE trekker.session_likes
(
    id               SERIAL PRIMARY KEY,
    session_user1_id BIGINT  NOT NULL,
    session_user2_id BIGINT  NOT NULL,
    trail_id         BIGINT  NOT NULL,
    user_id          BIGINT  NOT NULL,
    liked            BOOLEAN NOT NULL,
    round            INTEGER NOT NULL,
    FOREIGN KEY (session_user1_id, session_user2_id) REFERENCES trekker.sessions (user1_id, user2_id),
    FOREIGN KEY (trail_id) REFERENCES trekker.trails (id),
    FOREIGN KEY (user_id) REFERENCES trekker.users (id)
);

CREATE TABLE trekker.session_results
(
    id               SERIAL PRIMARY KEY,
    session_user1_id BIGINT  NOT NULL,
    session_user2_id BIGINT  NOT NULL,
    trail_id         BIGINT  NOT NULL,
    final_rank       INTEGER NOT NULL,
    FOREIGN KEY (session_user1_id, session_user2_id) REFERENCES trekker.sessions (user1_id, user2_id),
    FOREIGN KEY (trail_id) REFERENCES trekker.trails (id)
);