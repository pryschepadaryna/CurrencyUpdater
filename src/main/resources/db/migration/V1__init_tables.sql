-- Adds currency table  to save exchange rate received from API

CREATE TABLE public.currency_rate
(
    id                     BIGSERIAL,
    updated_at             TIMESTAMP,
    latest_external_update TIMESTAMP    NOT NULL,
    nameA            VARCHAR(255) NOT NULL,
    nameB           VARCHAR(255) NOT NULL,
    rate_to_buy            DOUBLE PRECISION,
    rate_to_sell           DOUBLE PRECISION,
    cross_rate             DOUBLE PRECISION,
    PRIMARY KEY (id)
);