ALTER TABLE pharmacies
    ALTER COLUMN name SET DATA TYPE varchar(255),
ALTER COLUMN medicine_link_template SET DATA TYPE varchar(255);

ALTER TABLE medicines
    ALTER COLUMN title SET DATA TYPE varchar(255);