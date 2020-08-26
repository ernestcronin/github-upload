
CREATE TABLE IF NOT EXISTS USER(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(250) NOT NULL,
	PASSWORD VARCHAR(250) NOT NULL,
	ROLE VARCHAR(250) NOT NULL,
	IS_ACTIVE INT NOT NULL
);

CREATE TABLE IF NOT EXISTS  RECIPE_INGREDIENT(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	RECIPE_ID INT NOT NULL,
	QUANTITY VARCHAR(250) NOT NULL,
	MEASUREMENT VARCHAR(250) NOT NULL,
	INGREDIENT_NAME VARCHAR(250) NOT NULL,
	
	CONSTRAINT FK_RECIPE_ID FOREIGN KEY(RECIPE_ID) REFERENCES RECIPE(ID)
);

CREATE TABLE IF NOT EXISTS RECIPE(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(250) NOT NULL,
	DESCRIPTION VARCHAR(250) NOT NULL
);

INSERT INTO USER(ID, NAME, PASSWORD, ROLE, IS_ACTIVE) VALUES (1, 'admin', '$2a$10$O1vzALoXubYVyXhL2WTm1OLLrkgxLtIvfTqKDa3Yzl3VZ.WfDjWVS', 'ADMIN', 1);
INSERT INTO USER(ID, NAME, PASSWORD, ROLE, IS_ACTIVE) VALUES (2, 'user', '$2a$10$O1vzALoXubYVyXhL2WTm1OLLrkgxLtIvfTqKDa3Yzl3VZ.WfDjWVS', 'USER', 1);

INSERT INTO RECIPE(ID, NAME, DESCRIPTION) VALUES (1, 'chocolate milk', 'A delicious chocolatey drink');
INSERT INTO RECIPE_INGREDIENT(ID, RECIPE_ID, QUANTITY, MEASUREMENT, INGREDIENT_NAME) VALUES (1, 1, '3 1/2', 'cups', 'whole milk');
INSERT INTO RECIPE_INGREDIENT(ID, RECIPE_ID, QUANTITY, MEASUREMENT, INGREDIENT_NAME) VALUES (2, 1, '2', 'ounces', 'cocoa');

/*
password = pass
*/