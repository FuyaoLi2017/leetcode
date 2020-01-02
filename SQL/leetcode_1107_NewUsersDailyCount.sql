/*
Write an SQL query that reports for every date within at most 90 days from today,
the number of users that logged in for the first time on that date. Assume today is 2019-06-30.
*/
# Write your MySQL query statement below
select login_date, count(user_id) as user_count
from
(select user_id, min(activity_date) as login_date
from Traffic
where activity = 'login'
group by user_id) t
where datediff('2019-06-30', login_date) <= 90
group by login_date
