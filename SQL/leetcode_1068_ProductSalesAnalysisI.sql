/*
Table: Sales

+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| sale_id     | int   |
| product_id  | int   |
| year        | int   |
| quantity    | int   |
| price       | int   |
+-------------+-------+
(sale_id, year) is the primary key of this table.
product_id is a foreign key to Product table.
Note that the price is per unit.
Table: Product

+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
+--------------+---------+
product_id is the primary key of this table.


Write an SQL query that reports all product names of the products in the Sales table along with their selling year and price.

SQL CROSS JOIN will return all records where each row from the first table is
 combined with each row from the second table. Which also mean CROSS JOIN
 returns the Cartesian product of the sets of rows from the joined tables.
*/
# Write your MySQL query statement below
Select p.product_name, s.year, s.price
from Sales as s cross join Product as p
where p.product_id = s.product_id;
