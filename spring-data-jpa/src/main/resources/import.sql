insert into book (id, title, authors, status) values (1, 'First book', 'Jan Kowalski', 'FREE');
insert into book (id, title, authors, status) values (2, 'Second book', 'Zbigniew Nowak', 'FREE');
insert into book (id, title, authors, status) values (3, 'Third book', 'Janusz Jankowski', 'FREE');
insert into book (id, title, authors, status) values (4, 'Thousand Ways to Die', 'Tomasz Mazurek', 'FREE');
insert into book (id, title, authors, status) values (5, 'Thousand Ways to Die', 'Tomasz Mazurek', 'FREE');
insert into book (id, title, authors, status) values (6, 'Another book', 'Tomasz Mazurek', 'FREE');


insert into userentity (id, username, password, enable) values (1, 'admin', 'admin', true);
insert into userentity (id, username, password, enable) values (2, 'password', 'password', true);
insert into userentity (id, username, password, enable) values (3, 'jacek', 'jacek', true);
insert into userentity (id, username, password, enable) values (4, 'Ktos', 'haslo', true);

insert into userroles (username, role) values ('jacek', 'ROLE_ADMIN');
insert into userroles (username, role) values ('Ktos', 'ROLE_ADMIN');
insert into userroles (username, role) values ('admin', 'ROLE_USER');




