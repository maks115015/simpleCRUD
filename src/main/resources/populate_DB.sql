DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 1000;

INSERT INTO users ( name, age, isAdmin) VALUES
('maks666', '22','1'),
('макс', '44', '0'),
('дима', '11', '1' ),
('юра', '6', '1'),
('дима', '3', '0'),
('витя', '54', '0'),
('вася', '32', '0'),
('ваня', '21', '0'),
('сергей', '46', '0'),
('женя', '67', '0'),
('влад', '33', '1' ),
('олег', '31', '0'),
('andrew', '12', '0'),
('tom', '16', '1' ),
('leo', '46', '0');