package org.gruzdeva.webcalendar.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class Message {
    private final String message;
    @JsonIgnore
    private final HttpStatus status;

    public Message(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
