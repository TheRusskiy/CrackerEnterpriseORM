INSERT INTO clubs(id, name, stadium)
VALUES(1, 'Manchester United', 'Old Trafford');
INSERT INTO clubs(id, name, stadium)
VALUES(2, 'Arsenal', 'Emirates');
INSERT INTO clubs(id, name, stadium)
VALUES(3, 'Chelsea', 'Stamford Bridge');
INSERT INTO clubs(id, name, stadium)
VALUES(4, 'Liverpool', 'Anfield');
COMMIT;

INSERT INTO players(id, name, position, club_id)
VALUES(1, 'Rooney', 'forward', 1);
INSERT INTO players(id, name, position, club_id)
VALUES(2, 'Giggs', 'forward', 1);
INSERT INTO players(id, name, position, club_id)
VALUES(3, 'Ferdinand', 'defender', 1);
INSERT INTO players(id, name, position, club_id)
VALUES(4, 'Evra', 'defender', 1);
INSERT INTO players(id, name, position, club_id)
VALUES(5, 'Carrick', 'midfielder', 1);
INSERT INTO players(id, name, position, club_id)
VALUES(6, 'David de Gea', 'goalkeeper', 1);

INSERT INTO players(id, name, position, club_id)
VALUES(7, 'Podolski', 'forward', 2);
INSERT INTO players(id, name, position, club_id)
VALUES(8, 'Walcott', 'forward', 2);
INSERT INTO players(id, name, position, club_id)
VALUES(9, 'Diaby', 'defender', 2);
INSERT INTO players(id, name, position, club_id)
VALUES(10, 'Sagna', 'defender', 2);
INSERT INTO players(id, name, position, club_id)
VALUES(11, 'Ramsey', 'midfielder', 2);
INSERT INTO players(id, name, position, club_id)
VALUES(12, 'Szczesny', 'goalkeeper', 2);

INSERT INTO players(id, name, position, club_id)
VALUES(13, 'Ramires', 'midfielder', 3);
INSERT INTO players(id, name, position, club_id)
VALUES(14, 'Torres', 'forward', 3);
INSERT INTO players(id, name, position, club_id)
VALUES(15, 'Cole', 'defender', 3);
INSERT INTO players(id, name, position, club_id)
VALUES(16, 'Ivanovic', 'defender', 3);
INSERT INTO players(id, name, position, club_id)
VALUES(17, 'Lampard', 'midfielder', 3);
INSERT INTO players(id, name, position, club_id)
VALUES(18, 'Cech', 'goalkeeper', 3);

INSERT INTO players(id, name, position, club_id)
VALUES(19, 'Downing', 'midfielder', 4);
INSERT INTO players(id, name, position, club_id)
VALUES(20, 'Suarez', 'forward', 4);
INSERT INTO players(id, name, position, club_id)
VALUES(21, 'Johnson', 'defender', 4);
INSERT INTO players(id, name, position, club_id)
VALUES(22, 'Agger', 'defender', 4);
INSERT INTO players(id, name, position, club_id)
VALUES(23, 'Gerrard', 'midfielder', 4);
INSERT INTO players(id, name, position, club_id)
VALUES(24, 'Reina', 'goalkeeper', 4);
COMMIT;

INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (1, TO_DATE('2000/01/01', 'yyyy/mm/dd'), 1, 2);
INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (2, TO_DATE('2001/01/01', 'yyyy/mm/dd'), 1, 3);
INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (3, TO_DATE('2003/05/03', 'yyyy/mm/dd'), 1, 4);
INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (4, TO_DATE('2004/06/07', 'yyyy/mm/dd'), 2, 3);
INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (5, TO_DATE('2005/05/03', 'yyyy/mm/dd'), 2, 4);
INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (6, TO_DATE('1992/09/07', 'yyyy/mm/dd'), 4, 1);
INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (7, TO_DATE('1998/04/20', 'yyyy/mm/dd'), 4, 2);
INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (8, TO_DATE('2008/11/21', 'yyyy/mm/dd'), 4, 1);
INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (9, TO_DATE('2009/04/25', 'yyyy/mm/dd'), 3, 2);
INSERT INTO matches(id, match_date, home_id, guest_id)
VALUES (10, TO_DATE('2013/09/17', 'yyyy/mm/dd'), 2, 1);
COMMIT;