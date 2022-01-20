Spring Boot Web Application: Employee Directory
====================================================================

Используемые технологии / инструменты / фреймворки:\
Thymeleaf/ Spring Boot 2/ Spring MVC/ Spring Security/ Spring Data JPA/ MySQL/ Maven

### Другие проекты:
- [Web App: Spring 5 (MVC, AOP) + JSP + Spring Security + Hibernate API](https://github.com/BelousAI/spring-web-crm-demo)
- [RESTful service: Regular Spring REST + native Hibernate API](https://github.com/BelousAI/spring-crm-rest)
- [RESTful service: Spring Boot REST + JPA(Hibernate)](https://github.com/BelousAI/employee-directory-rest-service)

### Функциональность приложения:
- создание новых сотрудников;
- редактирование и удаление существующих;
- регистрация нового пользователя системы;
- аутентификация и авторизация пользователя;
- отображение контента в зависимости от роли пользователя

### Управление доступом на основе ролей:
- **Employee role:** просмотр списка сотрудников;
- **Manager role:** *Employee role* + добавление новых и изменение существующих сотрудников;
- **Admin role:** *Manager role* + удаление сотрудников

### Тестовые учетки пользователей:
1. *john*
   - **user id**: john
   - **password:** fun123
   - **Role(s):** ROLE_EMPLOYEE
2. *mary*
   - **user id:** mary
   - **password:** fun123
   - **Role(s):** ROLE_EMPLOYEE, ROLE_MANAGER
3. *susan*
   - **user id:** susan
   - **password:** fun123
   - **Role(s):** ROLE_EMPLOYEE, ROLE_MANAGER, ROLE_ADMIN