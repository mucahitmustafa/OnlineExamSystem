INSERT INTO student (id, customer, created, updated, deleted, number, name, mail, password, blocked, last_login_date)
    VALUES (1, 'foundationX', NOW(), NOW(), FALSE, '123', 'Student 1 of X', 'a@foundationX.com', '123', FALSE, NULL);
INSERT INTO student (id, customer, created, updated, deleted, number, name, mail, password, blocked, last_login_date)
    VALUES (2, 'foundationX', NOW(), NOW(), FALSE, '123', 'Student 2 of X', 'ab@foundationX.com', '123', FALSE, NULL);
INSERT INTO student (id, customer, created, updated, deleted, number, name, mail, password, blocked, last_login_date)
    VALUES (3, 'foundationX', NOW(), NOW(), FALSE, '123', 'Student 3 of X', 'abc@foundationX.com', '123', FALSE, NULL);
INSERT INTO student (id, customer, created, updated, deleted, number, name, mail, password, blocked, last_login_date)
    VALUES (4, 'foundationY', NOW(), NOW(), FALSE, '123', 'Student 1 of Y', 'a@foundationY.com', '123', FALSE, NULL);
INSERT INTO student (id, customer, created, updated, deleted, number, name, mail, password, blocked, last_login_date)
    VALUES (5, 'foundationY', NOW(), NOW(), FALSE, '123', 'Student 2 of Y', 'ab@foundationY.com', '123', FALSE, NULL);

INSERT INTO foundation_student(foundation_id, student_id) VALUES (1, 1);
INSERT INTO foundation_student(foundation_id, student_id) VALUES (1, 2);
INSERT INTO foundation_student(foundation_id, student_id) VALUES (1, 3);
INSERT INTO foundation_student(foundation_id, student_id) VALUES (2, 4);
INSERT INTO foundation_student(foundation_id, student_id) VALUES (2, 5);

INSERT INTO student_groups(student_id, group_id) VALUES (1, 1);
INSERT INTO student_groups(student_id, group_id) VALUES (1, 1);
INSERT INTO student_groups(student_id, group_id) VALUES (2, 1);
INSERT INTO student_groups(student_id, group_id) VALUES (3, 1);
INSERT INTO student_groups(student_id, group_id) VALUES (4, 1);
