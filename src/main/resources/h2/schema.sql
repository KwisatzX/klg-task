CREATE TABLE people
(
    person_id   BIGINT IDENTITY PRIMARY KEY,
    person_name VARCHAR(30)
);

CREATE TABLE rental_properties
(
    property_id   BIGINT IDENTITY PRIMARY KEY,
    property_name VARCHAR(100) NOT NULL,
    owner_id      BIGINT,
    unit_price    FLOAT,
    surface_area  FLOAT        NOT NULL,
    description   VARCHAR(255),
    FOREIGN KEY (owner_id) REFERENCES people (person_id)
);

CREATE TABLE reservations
(
    reservation_id BIGINT IDENTITY PRIMARY KEY,
    property_id    BIGINT NOT NULL,
    renter_id      BIGINT NOT NULL,
    start_date     DATE   NOT NULL,
    end_date       DATE,
    monthly_cost   FLOAT,
    FOREIGN KEY (property_id) REFERENCES rental_properties (property_id),
    FOREIGN KEY (renter_id) REFERENCES people (person_id)
);