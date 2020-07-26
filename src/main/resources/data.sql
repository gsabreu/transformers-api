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
