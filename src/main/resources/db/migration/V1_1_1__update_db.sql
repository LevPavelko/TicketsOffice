ALTER TABLE Tickets
    ADD COLUMN customer_id INTEGER,
    ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES Customer(id) ON DELETE CASCADE;

ALTER TABLE Tickets
    ADD COLUMN event_id INTEGER,
    ADD CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES Events(id) ON DELETE CASCADE;

ALTER TABLE Customer
    ADD COLUMN tickets_id INTEGER,
    ADD CONSTRAINT fk_tickets_customer FOREIGN KEY (tickets_id) REFERENCES Tickets(id) ON DELETE CASCADE;

ALTER TABLE Events
    ADD COLUMN tickets_id INTEGER,
    ADD CONSTRAINT fk_tickets_event FOREIGN KEY (tickets_id) REFERENCES Tickets(id) ON DELETE CASCADE;

ALTER TABLE Events
    ADD COLUMN place_id INTEGER,
    ADD CONSTRAINT fk_place FOREIGN KEY (place_id) REFERENCES Placeses(id) ON DELETE CASCADE;