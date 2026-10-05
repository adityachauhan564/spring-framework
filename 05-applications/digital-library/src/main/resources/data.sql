-- Sample data for the default (H2) profile. The ids are fixed here, so the README's curl commands work.
-- The identity columns then continue counting after them, so new rows get new ids.
insert into library_author (author_id, author_name, author_address) values (1, 'J.K. Rowling', 'Edinburgh');
insert into library_author (author_id, author_name, author_address) values (2, 'R.K. Narayan', 'Mysore');
alter table library_author alter column author_id restart with 3;

insert into library_book (book_id, book_name, publication_year, book_price, book_edition, book_category, author_id, creation_time, updation_time)
values (1, 'Harry Potter and the Philosopher''s Stone', '1997', 499.0, '1st', 'FANTASY', 1, current_timestamp, current_timestamp);
insert into library_book (book_id, book_name, publication_year, book_price, book_edition, book_category, author_id, creation_time, updation_time)
values (2, 'Harry Potter and the Chamber of Secrets', '1998', 549.0, '1st', 'FANTASY', 1, current_timestamp, current_timestamp);
insert into library_book (book_id, book_name, publication_year, book_price, book_edition, book_category, author_id, creation_time, updation_time)
values (3, 'Malgudi Days', '1943', 299.0, '2nd', 'FICTION', 2, current_timestamp, current_timestamp);
alter table library_book alter column book_id restart with 4;

insert into library_user (user_id, user_name, user_mobile_no, user_email_id) values (1, 'asha', '9876543210', 'asha@example.com');
alter table library_user alter column user_id restart with 2;
