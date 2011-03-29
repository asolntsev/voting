CREATE INDEX idx_date ON vote(date);
CREATE INDEX idx_county_date ON vote (countyId, date);