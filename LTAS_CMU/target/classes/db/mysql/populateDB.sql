INSERT IGNORE INTO instructors VALUES (1, 'James', 'Carter','abcd@gmail.com','0891231234','jirada','berrybiw');
INSERT IGNORE INTO instructors VALUES (2, 'Helen', 'Leary','abcd@gmail.com','0891231234','jirada','berrybiw');
INSERT IGNORE INTO instructors VALUES (3, 'Linda', 'Douglas','abcd@gmail.com','0891231234','jirada','berrybiw');
INSERT IGNORE INTO instructors VALUES (4, 'Rafael', 'Ortega','abcd@gmail.com','0891231234','jirada','berrybiw');
INSERT IGNORE INTO instructors VALUES (5, 'Henry', 'Stevens','abcd@gmail.com','0891231234','jirada','berrybiw');
INSERT IGNORE INTO instructors VALUES (6, 'Sharon', 'Jenkins','abcd@gmail.com','0891231234','jirada','berrybiw');

INSERT IGNORE INTO faculties VALUES (1, 'CAMT');
INSERT IGNORE INTO faculties VALUES (2, 'ECON');
INSERT IGNORE INTO faculties VALUES (3, 'Acc-ba');

INSERT IGNORE INTO instructor_faculties VALUES (1, 1);
INSERT IGNORE INTO instructor_faculties VALUES (2, 2);
INSERT IGNORE INTO instructor_faculties VALUES (3, 3);
INSERT IGNORE INTO instructor_faculties VALUES (4, 1);
INSERT IGNORE INTO instructor_faculties VALUES (5, 2);
INSERT IGNORE INTO instructor_faculties VALUES (6, 3);


INSERT IGNORE INTO types VALUES (1, 'Mandatory');
INSERT IGNORE INTO types VALUES (2, 'Optional');
INSERT IGNORE INTO types VALUES (3, 'SMO');
INSERT IGNORE INTO types VALUES (4, 'Faculty');
INSERT IGNORE INTO types VALUES (5, 'University');
INSERT IGNORE INTO types VALUES (6, 'Club');

INSERT IGNORE INTO activities VALUES (1, 'Sport Day', 'DDDDD', '20','2010-09-07', '2010-09-10',1);
INSERT IGNORE INTO activities VALUES (2, 'Sport Night', 'DDDDD', '20','2010-09-07', '2010-09-10',1);
INSERT IGNORE INTO activities VALUES (3, 'Freshly Day', 'DDDDD', '20','2010-09-07', '2010-09-10',1);
INSERT IGNORE INTO activities VALUES (4, 'Freshly Night', 'DDDDD', '20', '2010-09-07', '2010-09-10',1);
INSERT IGNORE INTO activities VALUES (5, 'Teach book', 'DDDDD', '20','2010-09-07', '2010-09-10',1);
INSERT IGNORE INTO activities VALUES (6, 'Build school', 'DDDDD', '20', '2010-09-07', '2010-09-10',1);
INSERT IGNORE INTO activities VALUES (7, 'Plant', 'DDDDD', '20','2010-09-07', '2010-09-10',1);
INSERT IGNORE INTO activities VALUES (8, 'Congratulation day', 'DDDDD', '20', '2010-09-07', '2010-09-10',1);
INSERT IGNORE INTO activities VALUES (9, 'Teacher day', 'DDDDD', '20', '2010-09-07', '2010-09-10',1);
INSERT IGNORE INTO activities VALUES (10, 'Clean day', 'DDDDD', '20', '2010-09-07', '2010-09-10',1);

INSERT IGNORE INTO students VALUES (1, 'Leo','542115001', 1, 1);
INSERT IGNORE INTO students VALUES (2, 'Basil','542115002', 2, 2);
INSERT IGNORE INTO students VALUES (3, 'Rosy','542115003',3, 3);
INSERT IGNORE INTO students VALUES (4, 'Jewel','542115004',  1, 3);
INSERT IGNORE INTO students VALUES (5, 'Iggy','542115005',  2, 4);
INSERT IGNORE INTO students VALUES (6, 'George','542115006',3, 5);
INSERT IGNORE INTO students VALUES (7, 'Samantha','542115007', 1, 6);
INSERT IGNORE INTO students VALUES (8, 'Max', '542115008', 2, 6);
INSERT IGNORE INTO students VALUES (9, 'Lucky', '542115009',3, 7);
INSERT IGNORE INTO students VALUES (10, 'Mulligan','542115010', 1, 8);
INSERT IGNORE INTO students VALUES (11, 'Freddy', '542115011', 2, 9);
INSERT IGNORE INTO students VALUES (12, 'Lucky', '542115012', 3, 10);
INSERT IGNORE INTO students VALUES (13, 'Sly', '542115013',1, 10);


INSERT IGNORE INTO visits VALUES (1, 7, '2013-01-01', 'very happy');
INSERT IGNORE INTO visits VALUES (2, 8, '2013-01-02', 'thank for good activity');
INSERT IGNORE INTO visits VALUES (3, 8, '2013-01-03', 'funny');
INSERT IGNORE INTO visits VALUES (4, 7, '2013-01-04', 'I like this activity');
