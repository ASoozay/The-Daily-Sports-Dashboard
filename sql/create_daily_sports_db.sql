CREATE TABLE Writers (
			writer_id INT PRIMARY KEY AUTO_INCREMENT,
			role VARCHAR(10),
			first_name VARCHAR(100),
			last_name VARCHAR(100),
			standing VARCHAR(100),
			hire_date DATE,
			email VARCHAR(100),
			phone VARCHAR(100));


CREATE TABLE Logins (
			writer_id INT REFERENCES Writer(writer_id),	
			username VARCHAR(100),
			password_hash VARCHAR(65535));


CREATE TABLE Games (
			game_id INT PRIMARY KEY,	
			sport VARCHAR(100),
			home_team VARCHAR(100),
			away_team VARCHAR(100),
			city VARCHAR(100),
			state VARCHAR(100),
			time DATE,
			notes VARCHAR(65535));

CREATE TABLE Assignments (
			assignment_id INT PRIMARY KEY,
			writer_id INT REFERENCES Writers(writer_id),
			game_id	INT REFERENCES Game(game_id));