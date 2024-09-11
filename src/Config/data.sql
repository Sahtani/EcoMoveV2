CREATE TYPE TransportType AS ENUM ('PLANE', 'TRAIN', 'BUS');
CREATE TYPE PartnerStatus AS ENUM ('ACTIVE', 'INACTIVE', 'SUSPENDED');
CREATE TYPE ContractStatus AS ENUM ('ONGOING', 'FINISHED', 'SUSPENDED');
CREATE TYPE TicketStatus AS ENUM ('SOLD', 'CANCELLED', 'PENDING');
CREATE TYPE discounttype AS ENUM  ('PERCENTAGE', 'FIXEDPRICE');
CREATE TYPE OfferStatus AS ENUM ('ACTIVE', 'EXPIRED', 'SUSPENDED');




CREATE TABLE clients (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE Reservation (
    id INTEGER  PRIMARY KEY,
    dateReservation DATE NOT NULL,
    totalPrice DECIMAL(10, 2) NOT NULL,
    status BOOLEAN NOT NULL ,
	 clientId UUID NOT NULL,
    FOREIGN KEY (clientId) REFERENCES Clients(id)
);

CREATE TABLE Cities (
    id INTEGER PRIMARY KEY,
    cityName VARCHAR(255) NOT NULL
);

CREATE TABLE journeys (
    id SERIAL PRIMARY KEY,
    departure_city VARCHAR(255) NOT NULL,
    destination_city VARCHAR(255) NOT NULL
);

CREATE TABLE reservation_ticket (
    reservation_id INT NOT NULL,
    ticket_id UUID NOT NULL,
    PRIMARY KEY (reservation_id, ticket_id),
    FOREIGN KEY (reservation_id) REFERENCES reservation(id),
    FOREIGN KEY (ticket_id) REFERENCES ticket(id)
);

ALTER TABLE ticket
ADD COLUMN journey_id INT;

ALTER TABLE ticket
ADD CONSTRAINT fk_journey
FOREIGN KEY (journey_id) REFERENCES journeys(id);

