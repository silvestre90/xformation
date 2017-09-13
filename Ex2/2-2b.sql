SELECT DISTINCT Country.Name
FROM Country
WHERE Country.CountryID NOT IN (SELECT City.CountryID
FROM City INNER JOIN Building ON City.CityID = Building.CityID);
-- nested query