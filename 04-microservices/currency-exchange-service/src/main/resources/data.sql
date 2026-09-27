-- Seed rates, loaded after Hibernate creates the currency_exchange table.
-- (No "environment" column: that value is the answering instance's port, computed per request.)
insert into currency_exchange (id, currency_from, currency_to, conversion_multiple) values (10001, 'USD', 'INR', 91);
insert into currency_exchange (id, currency_from, currency_to, conversion_multiple) values (10002, 'EUR', 'INR', 113);
insert into currency_exchange (id, currency_from, currency_to, conversion_multiple) values (10003, 'AUD', 'INR', 25);
