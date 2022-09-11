# SQL Exercises
Utilizing the sample database 'dvdrental' in pgAdmin4 with postgreSQL 14

1. Identify the top 10 customers and their email so we can reward them
'''
SELECT first_name, last_name, email,
		SUM(amount) AS total_amount_spent
FROM customer AS c
INNER JOIN payment AS p
	ON p.customer_id = c.customer_id
GROUP BY first_name, last_name, email
ORDER BY total_amount_spent DESC
LIMIT 10;
'''

Output:
"first_name"|	"last_name"	|"email"	|"total_amount_spent"
:---: | :---: | :---: | :---:
"Eleanor"	|"Hunt"|	"eleanor.hunt@sakilacustomer.org"	|211.55
"Karl"	|"Seal"	|"karl.seal@sakilacustomer.org"|	208.58
"Marion"|	"Snyder"	|"marion.snyder@sakilacustomer.org"	|194.61
"Rhonda"|	"Kennedy"|	"rhonda.kennedy@sakilacustomer.org"	|191.62
"Clara"|	"Shaw"	|"clara.shaw@sakilacustomer.org"	|189.60
"Tommy"	|"Collazo"|	"tommy.collazo@sakilacustomer.org"	|183.63
"Ana"	|"Bradley"	|"ana.bradley@sakilacustomer.org"	|167.67
"Curtis"|	"Irby"|	"curtis.irby@sakilacustomer.org"	|167.62
"Marcia"|	"Dean"|	"marcia.dean@sakilacustomer.org"	|166.61
"Mike"|	"Way"|	"mike.way@sakilacustomer.org"	|162.67


2. Identify the bottom 10 customers and their emails
'''
SELECT first_name, last_name, email,
		SUM(amount) AS total_amount_spent
FROM customer AS c
INNER JOIN payment AS p
	ON p.customer_id = c.customer_id
GROUP BY first_name, last_name, email
ORDER BY total_amount_spent ASC
LIMIT 10;
'''

Output:
"first_name"|	"last_name"	|"email"	|"total_amount_spent"
:---: | :---: | :---: | :---:
"Brian"	|"Wyman"	|"brian.wyman@sakilacustomer.org"	|27.93
"Leona"	|"Obrien"|	"leona.obrien@sakilacustomer.org"	|32.90
"Caroline"|	"Bowman"	|"caroline.bowman@sakilacustomer.org"	|37.87
"Anthony"	|"Schwab"|	"anthony.schwab@sakilacustomer.org"	|47.85
"Tiffany"|	"Jordan"|	"tiffany.jordan@sakilacustomer.org"|	49.88
"Kirk"	|"Stclair"	|"kirk.stclair@sakilacustomer.org"	|50.83
"Bobbie"	|"Craig"|	"bobbie.craig@sakilacustomer.org"|	52.81
"Jo"|	"Fowler"	|"jo.fowler@sakilacustomer.org"|	54.85
"Penny"	|"Neal"	|"penny.neal@sakilacustomer.org"|	56.84
"Johnny"|	"Turpin"	|"johnny.turpin@sakilacustomer.org"	|57.81


3. What are the most profitable movie genres (ratings)? 
'''
SELECT name AS genre,
		COUNT(i.inventory_id) AS total_demand,
		SUM(amount) AS total_sales
FROM category AS cat
INNER JOIN film_category AS fc
	ON cat.category_id = fc.category_id
INNER JOIN film AS f
	ON f.film_id = fc.film_id
INNER JOIN inventory as i
	ON i.film_id = f.film_id
INNER JOIN rental as r
	ON r.inventory_id = i.inventory_id
INNER JOIN payment as p
	ON p.customer_id = r.customer_id
GROUP BY genre
ORDER BY total_sales DESC;
'''

Output:
"genre"	|"total_demand"	|"total_sales"
:---: | :---: | :---:
"Sports"	|29664	|125547.36
"Animation"|	29705	|124971.95
"Action"	|28144	|118562.56
"Family"	|28013	|118222.87
"Sci-Fi"	|27671	|116128.29
"Drama"	|26624	|111073.76
"Documentary"|	26658	|111022.42
"Foreign"|	26214	|110213.86
"Games"	|24636	|104538.64
"New"	|23872	|100529.28
"Comedy"	|23632	|100146.68
"Classics"	|23740	|99714.60
"Children"	|23868	|99686.32
"Horror"	|21093	|89445.07
"Travel"	|21020	|88557.80
"Music"	|20761	|87185.39


4. How many rented movies were returned late, early, and on time?
'''

'''


5. What is the customer base in the countries where we have a presence?
'''

'''


6. Which country is the most profitable for the business?
'''

'''


7. What is the average rental rate per movie genre (rating)?
'''

'''
