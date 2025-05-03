-- Table for users
  CREATE TABLE users (
    user_id INT NOT NULL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL
);
-- Table for trails
  CREATE TABLE trails (
    trail_id INT NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(150) NOT NULL,
    type VARCHAR(30) NULL,
    biome VARCHAR(30) NULL,
    difficulty VARCHAR(20) NULL,
    length DECIMAL(5,2) NULL,
    image VARCHAR(100) NULL
);
-- Table for multiple friends
CREATE TABLE friends (
    user1_id INT NOT NULL,
    friend_id INT NOT NULL,
    PRIMARY KEY (user1_id, friend_id),
    FOREIGN KEY (user1_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES users(user_id) ON DELETE CASCADE
);
-- Table for favorite trails
CREATE TABLE favorites (
    user_id INT NOT NULL,
    trail_id INT NOT NULL,
    rating INT NOT NULL,
    PRIMARY KEY (user_id, trail_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (trail_id) REFERENCES trails(trail_id) ON DELETE CASCADE
);
-- Table for keeping sessions
CREATE TABLE sessions (
    user1_id INT NOT NULL,
    user2_id INT NOT NULL,
    inviteCode INT NOT NULL,
    PRIMARY KEY (user1_id, user2_id),
    FOREIGN KEY (user1_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (user2_id) REFERENCES users (user_id) ON DELETE CASCADE
);