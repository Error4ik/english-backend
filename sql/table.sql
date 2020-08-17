CREATE EXTENSION pgcrypto;

CREATE TABLE categories (
  id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(255) NOT NULL default '',
  number_of_cards INTEGER NOT NULL default 0
);

CREATE TABLE cards (
  id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  word VARCHAR(255) NOT NULL default '',
  category_id UUID,
  transcription VARCHAR(255) NOT NULL default '',
  translation VARCHAR(255) NOT NULL default '',
  phrase VARCHAR(255) NOT NULL default '',
  translate_phrase VARCHAR(255) NOT NULL default '',

  FOREIGN KEY (category_id) REFERENCES categories (id)
);
