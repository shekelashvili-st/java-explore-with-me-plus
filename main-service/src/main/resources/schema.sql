--DROP TABLE event_audit;
--DROP TABLE event_views;
--DROP TABLE compilation_events;
--DROP TABLE compilations;
--DROP TABLE participation_requests;
--DROP TABLE events;
--DROP TABLE categories;
--DROP TABLE users;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(254) NOT NULL UNIQUE,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS events (
    id SERIAL PRIMARY KEY,
    title VARCHAR(120) NOT NULL,
    annotation VARCHAR(2000) NOT NULL,
    description VARCHAR(7000),
    event_date TIMESTAMP NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    published_on TIMESTAMP NULL,
    location_lat FLOAT NOT NULL,
    location_lon FLOAT NOT NULL,
    paid BOOLEAN DEFAULT FALSE,
    participant_limit INTEGER DEFAULT 0,
    request_moderation BOOLEAN DEFAULT TRUE,
    state VARCHAR(20) DEFAULT 'PENDING'
        CHECK (state IN ('PENDING', 'PUBLISHED', 'CANCELED')),
    views BIGINT DEFAULT 0,
    category_id INTEGER NOT NULL REFERENCES categories(id) ON DELETE RESTRICT,
    initiator_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS  participation_requests (
    id SERIAL PRIMARY KEY,
    event_id INTEGER NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    requester_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    status VARCHAR(20) DEFAULT 'PENDING'
        CHECK (status IN ('PENDING', 'CONFIRMED', 'REJECTED', 'CANCELED')),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (event_id, requester_id)
);

CREATE TABLE IF NOT EXISTS  compilations (
    id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL UNIQUE,
    pinned BOOLEAN DEFAULT FALSE,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS  compilation_events (
    compilation_id INTEGER NOT NULL REFERENCES compilations(id) ON DELETE CASCADE,
    event_id INTEGER NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    PRIMARY KEY (compilation_id, event_id)
);

CREATE TABLE IF NOT EXISTS  event_views (
    id SERIAL PRIMARY KEY,
    event_id INTEGER NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    views BIGINT DEFAULT 0,
    date DATE NOT NULL,
    UNIQUE (event_id, date)
);

CREATE TABLE IF NOT EXISTS  event_audit (
    id SERIAL PRIMARY KEY,
    event_id INTEGER NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users(id),
    action VARCHAR(50) NOT NULL,
    old_values JSONB,
    new_values JSONB,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);