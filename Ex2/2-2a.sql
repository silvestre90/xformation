SELECT  Country.Name
FROM Country LEFT JOIN (City LEFT JOIN Building ON City.CityID=Building.CityID) ON Country.CountryID=City.CountryID
GROUP BY Country.Name
HAVING COUNT(Building.CityID) =0;
-- task solved using JOINS