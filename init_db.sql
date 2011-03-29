CREATE TABLE changelog (
  change_number BIGINT NOT NULL,
  complete_dt TIMESTAMP NOT NULL,
  applied_by character varying(100) NOT NULL,
  description character varying(500) NOT NULL
);

ALTER TABLE changelog ADD CONSTRAINT Pkchangelog PRIMARY KEY (change_number)
;