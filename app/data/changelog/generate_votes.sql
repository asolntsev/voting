CREATE OR REPLACE FUNCTION generate_votes(
    votesCount IN INT,
    count OUT int)
AS $$
DECLARE
  votingDate DATE := date '2011-03-11';
  maxId int;
  counties int;
  constituencies int;
  candidates int;
  daysShift int;
  hoursShift int;
BEGIN
  count := 0;

  select COALESCE(max(id), 0) into maxId from vote;
  select count(*)-1 into counties from county;
  select count(*)-1 into constituencies from constituency;
  select count(*)-1 into candidates from candidate;

  FOR i IN 1..votesCount LOOP
    daysShift := round(5*random());
    hoursShift := round(24*random());
    INSERT INTO vote (
                id,
                countyId,
                constituencyId,
                date,
                candidateId)
         VALUES (maxId+i,
                1 + random() * counties,
                1 + random() * constituencies,
                votingDate - (daysShift * interval '1 day') + (hoursShift * interval '1 hour'),
                1+random()*candidates);

    count := count + 1;
  END LOOP;
END;
$$ LANGUAGE plpgsql;

/*
 ::::: EXECUTE ME: :::::
 delete from vote;
 SELECT * FROM generate_votes(600000);
*/