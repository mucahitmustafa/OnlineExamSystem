INSERT INTO question (id, customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES (1, 'foundationX', NOW(), NOW(), FALSE, '2 + 2 = ?', '2,4,6,8', 1, 1);
INSERT INTO question (id, customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES (2, 'foundationX', NOW(), NOW(), FALSE, '3 + 0 = ?', '3,6,9,12', 0, 1);
INSERT INTO question (id, customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES (3, 'foundationX', NOW(), NOW(), FALSE, '4 + 8 = ?', '4,8,12,16', 2, 1);
INSERT INTO question (id, customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES (4, 'foundationX', NOW(), NOW(), FALSE, '4 + 12 = ?', '4,8,12,16', 3, 1);

INSERT INTO question (id, customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES (5, 'foundationY', NOW(), NOW(), FALSE, '2 * 2 = ?', '2,4,6,8', 1, 2);
INSERT INTO question (id, customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES (6, 'foundationY', NOW(), NOW(), FALSE, '3 * 0 = ?', '3,6,0,12', 2, 2);
INSERT INTO question (id, customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES (7, 'foundationY', NOW(), NOW(), FALSE, '4 * 8 = ?', '4,32,64,72', 1, 2);
INSERT INTO question (id, customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES (8, 'foundationY', NOW(), NOW(), FALSE, '4 * 12 = ?', '48,81,120,160', 0, 2);
