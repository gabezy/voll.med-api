ALTER TABLE appointments ADD COLUMN created_at TIMESTAMP NOT NULL;
UPDATE appointments SET created_at = CURRENT_TIMESTAMP();