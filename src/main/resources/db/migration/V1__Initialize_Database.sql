CREATE TABLE foundation
(
    id       SERIAL PRIMARY KEY NOT NULL,
    customer VARCHAR(255) NOT NULL,
    created  TIMESTAMP(3) NOT NULL,
    updated  TIMESTAMP(3) NOT NULL,
    deleted  BOOLEAN      NOT NULL,
    name     VARCHAR(255) NOT NULL
);

CREATE TABLE student
(
    id              SERIAL PRIMARY KEY NOT NULL,
    customer        VARCHAR(255) NOT NULL,
    created         TIMESTAMP(3) NOT NULL,
    updated         TIMESTAMP(3) NOT NULL,
    deleted         BOOLEAN      NOT NULL,
    number          VARCHAR(255),
    name            VARCHAR(255) NOT NULL,
    mail            VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL
);

CREATE TABLE exam
(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(255) NOT NULL,
    customer    VARCHAR(255) NOT NULL,
    created     TIMESTAMP(3) NOT NULL,
    updated     TIMESTAMP(3) NOT NULL,
    deleted     BOOLEAN      NOT NULL,
    start_date  TIMESTAMP(3) NOT NULL,
    end_date    TIMESTAMP(3) NOT NULL
);

CREATE TABLE question
(
    id                   SERIAL PRIMARY KEY NOT NULL,
    customer             VARCHAR(255) NOT NULL,
    created              TIMESTAMP(3) NOT NULL,
    updated              TIMESTAMP(3) NOT NULL,
    deleted              BOOLEAN      NOT NULL,
    exam_id              BIGINT       NOT NULL,
    text                 VARCHAR(255) NOT NULL,
    answers              VARCHAR(255) NOT NULL,
    correct_answer_index INTEGER      NOT NULL
);

ALTER TABLE question
    ADD CONSTRAINT FK_QUESTION_EXAM_ID FOREIGN KEY (exam_id) REFERENCES exam (id);

CREATE TABLE exam_login
(
    id         SERIAL PRIMARY KEY NOT NULL,
    customer   VARCHAR(255)     NOT NULL,
    created    TIMESTAMP(3)     NOT NULL,
    updated    TIMESTAMP(3)     NOT NULL,
    deleted    BOOLEAN          NOT NULL,
    exam_id    BIGINT           NOT NULL,
    student_id BIGINT           NOT NULL,
    answers    VARCHAR(255)     NOT NULL,
    score      DOUBLE PRECISION NOT NULL,
    login_date TIMESTAMP(3)     NOT NULL
);

ALTER TABLE exam_login
    ADD CONSTRAINT FK_EXAM_LOGIN_EXAM_ID FOREIGN KEY (exam_id) REFERENCES exam (id);

ALTER TABLE exam_login
    ADD CONSTRAINT FK_EXAM_LOGIN_STUDENT_ID FOREIGN KEY (student_id) REFERENCES student (id);
