Job Portal Backend System
Project Overview
This project implements a robust backend for a Job Portal application using Spring Boot, Spring Data JPA, and MySQL. It provides RESTful APIs for three primary user roles—Admin, Employer, and Job Seeker—allowing them to perform tasks such as job postings, job applications, user management, and more.

Features
User Roles and Functionalities
Admin:

View and delete users.
Employer:

Post and manage jobs.
View and update the status of job applications.
Add reviews to posted jobs.
Job Seeker:

View available jobs and apply.
Upload resumes.
View their job application statuses.
Technologies Used
Backend: Java, Spring Boot
Database: MySQL
ORM: Spring Data JPA
Security: Spring Security
Mapping: DTOs with manual mapping or MapStruct
Testing: Postman
API Endpoints
Method	Endpoint	Description	Role
POST	/api/auth/register	Register new users	All Users
GET	/api/users	List users	Admin Only
DELETE	/api/users/{id}	Delete user	Admin Only
POST	/api/jobs	Create job posts	Employer/Admin
GET	/api/jobs	Retrieve job listings with filters	All Users
GET	/api/applications/job/{jobId}	View applications for specific job	Employer/Admin
PUT	/api/applications/{applicationId}/status	Update application status	Employer Only
POST	/api/applications	Apply for a job	Job Seeker Only
POST	/api/resumes/upload	Upload resume	Job Seeker Only
GET	/api/applications/user/{userId}	View own job applications	Job Seeker Only
POST	/api/reviews	Add review for a job	Employer Only
GET	/api/reviews/job/{jobId}	Get reviews for a job	All Users
Installation and Setup
1. Clone the Repository:
bash
Copy
Edit
git clone <repository-url>
2. Set up Database:
Create a MySQL database named job_portal.
Update application.properties with your database credentials:
properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/job_portal  
spring.datasource.username=root  
spring.datasource.password=yourpassword  
3. Run the Application:
bash
Copy
Edit
./mvnw spring-boot:run  
4. Test the APIs:
Use Postman to test the provided endpoints.
Import the Postman collection provided separately (Job Portal API Collection.postman_collection.json).
Contribution Guidelines
Fork the repository.
Create a new branch for your feature:
bash
Copy
Edit
git checkout -b feature-name  
Commit changes and push to your branch.
Submit a pull request with a detailed description of your changes.
Author
Alessia Toli
