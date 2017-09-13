SELECT Country.Name
FROM Country INNER JOIN City ON Country.CountryID=City.CountryID
GROUP BY Country.Name
HAVING SUM(City.Population)>400;
