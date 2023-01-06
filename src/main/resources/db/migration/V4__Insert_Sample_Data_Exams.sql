INSERT INTO exam (name, customer, created, updated, deleted, duration, start_date, end_date)
    VALUES ('Sınav 1', 'foundationX', NOW(), NOW(), FALSE, 30, NOW(), NOW());
INSERT INTO exam (name, customer, created, updated, deleted, duration, start_date, end_date)
    VALUES ('Sınav 2', 'foundationY', NOW(), NOW(), FALSE, 30, NOW(), NOW());

INSERT INTO foundation_exams(foundation_id, exam_id) VALUES (1, 1);
INSERT INTO foundation_exams(foundation_id, exam_id) VALUES (2, 2);

INSERT INTO exam_students(exam_id, student_id) VALUES (1, 1);
INSERT INTO exam_students(exam_id, student_id) VALUES (1, 2);
INSERT INTO exam_students(exam_id, student_id) VALUES (1, 3);
INSERT INTO exam_students(exam_id, student_id) VALUES (2, 4);
INSERT INTO exam_students(exam_id, student_id) VALUES (2, 5);
