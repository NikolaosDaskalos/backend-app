CREATE TABLE IF NOT EXISTS cars (
	car_id BIGSERIAL NOT NULL PRIMARY KEY,
	manufacture VARCHAR(50),
	model VARCHAR(50),
	model_year Integer,
	color VARCHAR(50)
);
