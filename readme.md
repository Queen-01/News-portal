### PROJECT NAME
News Portal
##AUTHOR
Quienzy Ong'eye

## Brief description
NewsPortal is an Organisational news dissemination application.
Users can post news to the general staff or to staff in a specific department.

## User Requirements

The user would like to;

- Create departments
- Create news and categorize then as general or belonging to a specific department
- Add users to a department where one department has many users
- See individual users, their details like position,role and department.
- See department details like name, details and number of employees in the department
- See all users from a specific department and news relating to that department
- Post general news or news relating s a specific department.

## How it works

# Server Side

The application has four model classes;

- User
- News
- DepartmentNews
- Department

DepartmentNews inherits from News.
In the database, model User maps to table users, model Department maps to table departments while model
News and model DepartmentNews map to one table news through single table inheritance.

The API routes;

- GET "/users"
- GET "/departments"
- GET "/users/:id"
- GET "/departments/:id"
- GET "/departments/:id/users"
- GET "/departments/:id/news"
- GET "/news"
- GET "/news/main"
- GET "/news/department"

- POST "/Users/new"
- POST "/Departments/new"
- POST "/News/new"
- POST "/DepartmentNews/new"
## Technology used
- Java
- SparkJava
- Gradle
- JUnit 4
- GitHub
- IntelliJ IDEA Community Edition
- Postgres
- Sql2o
## License
This project is licensed under the MIT (License) Open Source license.