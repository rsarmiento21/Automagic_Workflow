/*/// TABLES ///*/
/* Table BOARD_USER */
CREATE TABLE board_user (
  bu_id INT,
  bu_username VARCHAR2(4000) UNIQUE,
  bu_password VARCHAR2(4000),
  PRIMARY KEY (bu_id)
);

/* Table BOARD */
CREATE TABLE board (
  bd_id INT,
  owner_id INT,
  bd_name VARCHAR2(4000),
  bd_startdare TIMESTAMP,
  PRIMARY KEY (bd_id),
  FOREIGN KEY (owner_id) REFERENCES board_user (bu_id)
);

/* Lookup Table BOARD_ROLE */
CREATE TABLE board_role (
  br_id INT,
  br_name VARCHAR2(4000) UNIQUE,
  PRIMARY KEY (br_id)
);

/* Join Table BOARD_USER_ROLE */
CREATE TABLE board_user_role (
  bd_id INT,
  bu_id INT,
  br_id INT,
  PRIMARY KEY (bd_id, bu_id),
  FOREIGN KEY (bd_id) REFERENCES board (bd_id),
  FOREIGN KEY (bu_id) REFERENCES board_user (bu_id),
  FOREIGN KEY (br_id) REFERENCES board_role (br_id)
);

/* Table SWIM_LANE */
CREATE TABLE swim_lane (
  sl_id INT,
  bd_id INT,
  sl_name VARCHAR2(4000),
  sl_order INT,
  PRIMARY KEY (sl_id),
  FOREIGN KEY (bd_id) REFERENCES board (bd_id)
);

/* Table STORY */
CREATE TABLE story (
  st_id INT,
  sl_id INT,
  st_title VARCHAR2(4000),
  st_desc VARCHAR2(4000),
  st_points INT,
  st_completed TIMESTAMP DEFAULT NULL,
  st_order INT,
  PRIMARY KEY (st_id),
  FOREIGN KEY (sl_id) REFERENCES swim_lane (sl_id)
);

/* Table TASK */
CREATE TABLE task (
  tsk_id INT,
  st_id INT,
  tsk_name VARCHAR2(4000),
  tsk_completed NUMBER(1) DEFAULT 0,
  tsk_order INT,
  PRIMARY KEY (tsk_id),
  FOREIGN KEY (st_id) REFERENCES story (st_id)
);



/*/// SEQUENCES ///*/
CREATE SEQUENCE board_user_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE board_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE swim_lane_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE story_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE task_seq
START WITH 1
INCREMENT BY 1;


/*/// DML ///*/
/* Filling BOARD_ROLE */
INSERT INTO board_role VALUES (1, 'Editor');
INSERT INTO board_role VALUES (2, 'Verifier');