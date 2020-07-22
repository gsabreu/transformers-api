DROP TABLE IF EXISTS transformers;

CREATE TABLE transformers (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  team VARCHAR(1) NOT NULL,
  strength INT NOT NULL,
  intelligence INT NOT NULL,
  speed INT NOT NULL,
  endurance INT NOT NULL,
  rank INT NOT NULL,
  courage INT NOT NULL,
  firepower INT NOT NULL,
  skill INT NOT NULL
);

INSERT INTO transformers (name, team, strength, intelligence, speed, endurance, rank, courage, firepower, skill) VALUES
  ('Soundwave', 'D', 8, 9, 2, 6, 7, 5, 6, 10),
  ('Bluestreak', 'A', 6, 6, 7, 9, 5, 2, 9, 7),
  ('Hubcap', 'A', 4, 4, 4, 4, 4, 4, 4, 4);
