/*
SELECT c.id, count(*), extract(doy from v.date)
FROM constituency c, vote v
WHERE c.id = v.constituencyId
GROUP BY c.id, extract(doy from v.date)
*/

DELETE FROM constituency;
INSERT INTO constituency (id, name, population) VALUES(1, 'Haabersti , Kristiine, Põhja-Tallinn', 76189);
INSERT INTO constituency (id, name, population) VALUES(2, 'Kesklinn, Lasnamäe, Pirita', 104478);
INSERT INTO constituency (id, name, population) VALUES(3, 'Mustamäe, Nõmme', 69816);
INSERT INTO constituency (id, name, population) VALUES(4, 'Harju, Rapla', 130270);
INSERT INTO constituency (id, name, population) VALUES(5, 'Hiiu, Lääne, Saare', 58583);
INSERT INTO constituency (id, name, population) VALUES(6, 'Lääne-Viru', 48875);
INSERT INTO constituency (id, name, population) VALUES(7, 'Ida-Viru', 67604);
INSERT INTO constituency (id, name, population) VALUES(8, 'Järva, Viljandi', 70092);
INSERT INTO constituency (id, name, population) VALUES(9, 'Jõgeva, Tartu', 67504);
INSERT INTO constituency (id, name, population) VALUES(10,'Tartu linn', 70968);
INSERT INTO constituency (id, name, population) VALUES(11,'Võru, Valga, Põlva', 79857);
INSERT INTO constituency (id, name, population) VALUES(12,'Pärnu', 69110);

DELETE FROM party;
INSERT INTO party (id, name) VALUES (1, 'Reformierakond');
INSERT INTO party (id, name) VALUES (2, 'Keskerakond');

DELETE FROM participant;
INSERT INTO participant(id, partyId, name, registerNumber, bornPlace) VALUES (1, 1, 'Andrus Ansip', 507, 'Tartu linn');
INSERT INTO participant(id, partyId, name, registerNumber, bornPlace) VALUES (2, 1, 'Urmas Paet', 497, 'Tallinn');
INSERT INTO participant(id, partyId, name, registerNumber, bornPlace) VALUES (3, 2, 'Edgar Savisaar', 609, 'Harjumaa');
INSERT INTO participant(id, partyId, name, registerNumber, bornPlace) VALUES (4, 2, 'Mihhail Stalnuhhin', 664, 'Tartu');
