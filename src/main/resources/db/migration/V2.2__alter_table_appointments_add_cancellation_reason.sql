ALTER TABLE appointments ADD COLUMN cancellation_reason VARCHAR(100) DEFAULT NULL;
UPDATE appointments SET cancellation_reason = NULL;