ALTER TABLE prices ADD
    FOREIGN KEY (medicine_id) REFERENCES medicines(id);

ALTER TABLE prices ADD
    FOREIGN KEY (pharmacy_id) REFERENCES pharmacies(id);