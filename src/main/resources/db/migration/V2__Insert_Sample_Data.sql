-- Add foundation
-- Add examiner
-- Add student
-- add exam
-- add questions
-- add result

INSERT INTO foundation (customer, created, updated, deleted, name) VALUES ('MUMU', NOW(), NOW(), FALSE, 'MY Foundation');
INSERT INTO examiner (customer, created, updated, deleted, name, mail, password, blocked, last_login_date)
    VALUES ('MUMU', NOW(), NOW(), FALSE, 'MY', 'mucahitmustafay@gmail.com', '123', FALSE, NULL);
INSERT INTO student_group (customer, created, updated, deleted, name) VALUES ('MUMU', NOW(), NOW(), FALSE, 'MY Group');
INSERT INTO student (customer, created, updated, deleted, number, group_id, name, mail, password, blocked, last_login_date)
    VALUES ('MUMU', NOW(), NOW(), FALSE, '123', 1, 'MY', 'mucahitmustafa@gmail.com', '123', FALSE, NULL);
INSERT INTO exam (customer, created, updated, deleted, duration, examiner_id, start_date, end_date)
    VALUES ('MUMU', NOW(), NOW(), FALSE, 30, 1, NOW(), NOW());
INSERT INTO question (customer, created, updated, deleted, exam_id, text, answers, correct_answer_index)
    VALUES ('MUMU', NOW(), NOW(), FALSE, 1, '2 + 2 = ?', '2,4,6,8', 1);
INSERT INTO question (customer, created, updated, deleted, exam_id, text, answers, correct_answer_index)
    VALUES ('MUMU', NOW(), NOW(), FALSE, 1, '3 + 0 = ?', '3,6,9,12', 0);
INSERT INTO question (customer, created, updated, deleted, exam_id, text, answers, correct_answer_index)
    VALUES ('MUMU', NOW(), NOW(), FALSE, 1, '4 + 8 = ?', '4,8,12,16', 2);
INSERT INTO question (customer, created, updated, deleted, exam_id, text, answers, correct_answer_index)
VALUES ('MUMU', NOW(), NOW(), FALSE, 1, '4 + 12 = ?', '4,8,12,16', 3);
INSERT INTO exam_result (customer, created, updated, deleted, exam_id, student_id, answers, score, login_date)
    VALUES('MUMU', NOW(), NOW(), FALSE, 1, 1, '1,0,2,3', 100, NOW());

INSERT INTO student_groups(student_id, group_id) VALUES (1, 1);
INSERT INTO student_results(student_id, exam_result_id) VALUES (1, 1);
INSERT INTO examiner_exams(examiner_id, exam_id) VALUES (1, 1);
INSERT INTO exam_students(student_id, exam_id) VALUES (1, 1);
INSERT INTO exam_questions(exam_id, question_id) VALUES (1, 1);
INSERT INTO exam_questions(exam_id, question_id) VALUES (1, 2);
INSERT INTO exam_questions(exam_id, question_id) VALUES (1, 3);
INSERT INTO exam_questions(exam_id, question_id) VALUES (1, 4);
INSERT INTO foundation_managers(foundation_id, manager_id) VALUES (1, 1);
INSERT INTO foundation_examiners(foundation_id, examiner_id) VALUES (1, 1);
INSERT INTO foundation_student(foundation_id, student_id) VALUES (1, 1);
INSERT INTO foundation_exams(foundation_id, exam_id) VALUES (1, 1);
