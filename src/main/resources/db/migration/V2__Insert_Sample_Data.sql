INSERT INTO foundation (customer, created, updated, deleted, name) VALUES ('YTÜ', NOW(), NOW(), FALSE, 'YTÜ');

INSERT INTO student (customer, created, updated, deleted, number, name, mail, password)
    VALUES ('YTÜ', NOW(), NOW(), FALSE, '21574055', 'Mücahit Mustafa', 'mucahitmustafay@gmail.com', '123');

INSERT INTO exam (name, customer, created, updated, deleted, start_date, end_date)
    VALUES ('Giriş Sınavı', 'YTÜ', NOW(), NOW(), FALSE, NOW(), '2023-06-01');

INSERT INTO question (customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES ('YTÜ', NOW(), NOW(), FALSE, '2 + 2 = ?', '2###4###6###8', 1, 1);

INSERT INTO question (customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES ('YTÜ', NOW(), NOW(), FALSE, '3 + 0 = ?', '3###6###9###12', 0, 1);

INSERT INTO question (customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES ('YTÜ', NOW(), NOW(), FALSE, '4 + 8 = ?', '4###8###12###16', 2, 1);

INSERT INTO question (customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES ('YTÜ', NOW(), NOW(), FALSE, '4 + 12 = ?', '4###8###12###16', 3, 1);

INSERT INTO question (customer, created, updated, deleted, text, answers, correct_answer_index, exam_id)
    VALUES ('YTÜ', NOW(), NOW(), FALSE, '5 * 6 = ?', '20###30###40###50', 1, 1);
