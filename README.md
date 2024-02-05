# Project Manager Backend

## 1. Description
- This project is a simple project management app. It uses **Java 21** and an external **Postres** database.   
- The app enables to create projects, assign users to them and then create tasks that can be assigned to users.  
- A task can be of one of following specializations: **FRONTEND, BACKEND, DEVOPS, UI/UX**. Tasks can be
only assigned to developers of matching specialization.  
- Every task also has a **date range** to complete a task, **estimation** being a fibbonacci number, **description**
and a current **state** being one of the following: **OPEN, IN_PROGRESS, COMPLETED, ON_HOLD, CANCELED, OVERDUE**.  
- **Important note:** a **developer** is an entity that represents a user in a project with a specialization. 
  That means one user can be represented as many developers.
  - e.g. a user John Doe is in two different projects: A and B. In project A, he works on backend,
    in project B he works on frontend. That means a user John Doe is tied to two different developer instances - 
    backend developer in project A and frontend developer in project B.

## 2. Run

To run the application, type in the terminal:
- `mvnw clean package -DskipTests`
- `docker compose -f ./docker-compose.yaml up`

**The application runs on a port 8089**
## 3. Endpoints
### 3.1. Users
- **GET** `/users`
  - return all users


- **POST** `/users`
  - create a new user
  - need to provide body with a first name and a last name


- **GET** `/users/{userId}/projects`
  - return all projects of a user


### 3.2. Projects
- **GET** `/project` 
  - return all projects


- **GET** `/project/{id}` 
  - return a project by ID


- **GET** `/project/{id}/users` 
  - return all users in a project


- **POST** `/project/{id}/user`
  - add user to a project
  - need to provide body with a user ID and a specialization

### 3.3 Tasks
- **GET** `/projects/{id}/tasks`
  - return all tasks assigned in a project
  - **optional**: `spec` parameter, return tasks of given specialization 


- **POST** `projects/{id}/task`
  - add a task to a project
  - need to provide body with a name, a description, IDs of the creator and the assignee, specialization,
    estimation and a date range


- **PUT** `/project/{projectId}/task/{taskId}/assign/{devId}`
  - assign a task to a developer
  - **Important**: a developer is an entity that represents a user in a project.  
  There can be many developer instances connected to one user instance


- **PUT** `/project/{projectId}/task/{taskId}/state`
  - change a state of a task
  - need to provide a new state in the body


- **PUT** `/project/{projectId}/task/{taskId}/date`
  - change a date range of a task
  - need to provide a new date range in the body
