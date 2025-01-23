# Web Calendar

## Assignment
create a web calendar to store and manage all upcoming events. </br>
Use the Spring Boot framework and create a REST API.</br>
Work with a database using Spring Data JPA and create resources using Spring MVC.

### What did I learn
- How to use H2 database for development
- How to use Jakarta Persistence API(JPA)
  - How to create tables using JPA
  - How to work with JPA repositories
  - Understanding of EntityContext and EntityManager
- Validation with Jakarta Validation API
- That controllers can be mapped instead of writing the same URL prefix for each endpoint
- Improved understanding of LocalDate objects 

### Technologies used
**Language**: Java 17 </br>
**Frameworks**: SpringBoot, Spring Data JPA</br>
**Tools**: Postman, H2

### Event Endpoints
- **Create Event**
  - **URL**: `/events`
  - **Method**: `POST`
  - **Description**: Create a new event.
  - **Request Body**:
    ```json
    {
      "event": "Event Title",
      "date": "2023-12-31"
    }
    ```
  - **Response**: `201 Created`
  - **Edge Cases**:
    - Invalid Date Format: If the date is not in the correct format, the request will fail with a 400 Bad Request.
    - Missing Fields: If required fields (title, date) are missing, the request will fail with a 400 Bad Request.

- **Get Events in time period**
  - **URL**: `/events?start_time={start_date}end_time={end_date}`
  - **Method**: `GET`
  - **Description**: Retrieve all events in between start and end date (inclusive). If no events are found retrieve all existing events.
  - **Response**: `200 OK`
    ```json
    [
      {
        "id": 1,
        "event": "Event Title",
        "date": "2023-12-31"
      }
    ]
    ```
  - **Edge Cases**:
    - Invalid Date Format: If the start or end date is not in the correct format, the request will fail with a 400 Bad Request.
    - No Events Found: If no events exist, the response will be 204 No Content.

- **Get Event by ID**
  - **URL**: `/events/{id}`
  - **Method**: `GET`
  - **Description**: Retrieve an event by its ID.
  - **Response**: `200 OK`
    ```json
    {
      "id": 1,
      "event": "Event Title",
      "date": "2023-12-31"
    }
    ```
  - **Edge Cases**:
    - Invalid ID: If the ID is not a positive number, the request will fail with a 400 Bad Request.
    - Event Not Found: If no event is found with the given ID, the response will be 404 Not Found.

- **Delete Event by ID**
  - **URL**: `/events/{id}`
  - **Method**: `DELETE`
  - **Description**: Delete an event by its ID.
  - **Response**: `200 OK`
    ```json
    {
      "id": 1,
      "event": "Event Title",
      "date": "2023-12-31"
    }
    ```
  - **Edge Cases**:
    - Invalid ID: If the ID is not a positive number, the request will fail with a 400 Bad Request.
    - Event Not Found: If no event is found with the given ID, the response will be 404 Not Found.


## Potential future enhancements
Some of the possible features that would be great to implement in a project of this type
1. User management - allowing different users to have different events;
2. Collaborative event - allow multiple users to refer to the same event;
3. Add additional information for events: description, place, time, etc.
   
