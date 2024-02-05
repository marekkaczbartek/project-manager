# Project Manager Backend
### The application uses Java 21 with Spring Framework and Postres database

## 1. To run the application, type into terminal: 

- `mvnw clean package -DskipTests`
- `docker compose -f ./docker-compose.yaml up`

**The application runs on a port 8089**

## 2. Endpoints
### 2.1. Users
- **GET** `/users`
  - return all users


- **POST** `/users`
  - create a new user


- **GET** `/users/{userId}/projects`
  - return all projects of a user


### 2.2. Projects
- **GET** `/project` 
  - return all projects


- **GET** `/project/{id}` 
  - return a project by ID


- **GET** `/project/{id}/users` 
  - return all users in a project


- **POST** `/project/{id}/user`
  - add user to a project

### 2.3 Tasks
- **GET** `/projects/{id}/tasks`
  - return all tasks assigned in a project


- **POST** `projects/{id}/task`
  - add a task to a project


- **PUT** `/project/{projectId}/task/{taskId}/assign/{devId}`
  - assign a task to a developer
  - **Important**: a developer is an entity that represents a user in a project.  
  There can be many developer instances connected to one user instance


- **PUT** `/project/{projectId}/task/{taskId}/state`
  - change a state of a task


- **PUT** `/project/{projectId}/task/{taskId}/date`
  - change a date range of a task
