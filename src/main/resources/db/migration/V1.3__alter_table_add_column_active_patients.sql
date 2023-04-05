ALTER TABLE patients ADD COLUMN active TINYINT DEFAULT 1;
UPDATE patients SET active = 1;