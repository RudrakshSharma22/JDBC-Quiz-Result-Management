# JDBC-Quiz-Result-Management

## Project Overview
A Java JDBC application connected to Oracle Database to manage quiz results and analyze student scores.

## Features
- Insert student quiz results into the database.
- Find the highest quiz score.
- Display quiz attempts with scores above 80%.
- Calculate student score percentages.

## Technologies Used
- Java
- JDBC
- Oracle Database 26ai Free
- Oracle JDBC Driver
- Visual Studio Code

## Database Table
Table name: `QUIZ_RESULTS`

| Column | Description |
|---|---|
| STUDENT_ID | Student ID |
| QUIZ_NAME | Quiz name |
| TOTAL_QUESTIONS | Total questions |
| SCORE | Marks obtained |

## SQL Table Creation

```sql
CREATE TABLE QUIZ_RESULTS (
    STUDENT_ID NUMBER,
    QUIZ_NAME VARCHAR2(50),
    TOTAL_QUESTIONS NUMBER,
    SCORE NUMBER
);
```

## How to Run
1. Install Java JDK and Oracle Database.
2. Download the Oracle JDBC driver.
3. Configure the JDBC driver in your Java project.
4. Set your database connection credentials locally.
5. Run `oracle.java`.

## Security
Never upload your actual database password or other private credentials to GitHub.

## Author
Java JDBC Quiz Application Project
