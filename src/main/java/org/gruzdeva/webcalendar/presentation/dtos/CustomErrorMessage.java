package org.gruzdeva.webcalendar.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class CustomErrorMessage {
    private String error = "An error has occurred.";
    @JsonIgnore
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public CustomErrorMessage() {}

    public CustomErrorMessage(String message, HttpStatus status) {
        error = message;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
