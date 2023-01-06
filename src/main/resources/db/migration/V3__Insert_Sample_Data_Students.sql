INSERT INTO student (customer, created, updated, deleted, number, name, mail, password)
    VALUES ('foundationX', NOW(), NOW(), FALSE, '123', 'Student 1 of X', 'a@foundationX.com', '123');
INSERT INTO student (customer, created, updated, deleted, number, name, mail, password)
    VALUES ('foundationX', NOW(), NOW(), FALSE, '123', 'Student 2 of X', 'ab@foundationX.com', '123');
INSERT INTO student (customer, created, updated, deleted, number, name, mail, password)
    VALUES ('foundationX', NOW(), NOW(), FALSE, '123', 'Student 3 of X', 'abc@foundationX.com', '123');
INSERT INTO student (customer, created, updated, deleted, number, name, mail, password)
    VALUES ('foundationY', NOW(), NOW(), FALSE, '123', 'Student 1 of Y', 'a@foundationY.com', '123');
INSERT INTO student (customer, created, updated, deleted, number, name, mail, password)
    VALUES ('foundationY', NOW(), NOW(), FALSE, '123', 'Student 2 of Y', 'ab@foundationY.com', '123');

INSERT INTO foundation_student(foundation_id, student_id) VALUES (1, 1);
INSERT INTO foundation_student(foundation_id, student_id) VALUES (1, 2);
INSERT INTO foundation_student(foundation_id, student_id) VALUES (1, 3);
INSERT INTO foundation_student(foundation_id, student_id) VALUES (2, 4);
INSERT INTO foundation_student(foundation_id, student_id) VALUES (2, 5);
