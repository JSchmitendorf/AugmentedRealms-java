package io.augmentedrealms.client.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApiExceptionResponse {

    DUPLICATE_USER(HttpStatus.BAD_REQUEST, "NewUser already existed."),
    TOKEN_INVALID(HttpStatus.FORBIDDEN, "Invalid token."),
    PASSWORD_INCORRECT(HttpStatus.BAD_REQUEST, "Password incorrect."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "NewUser not found."),
    REALM_NOT_FOUND(HttpStatus.BAD_REQUEST, "Realm not found."),
    CLOUD_STORAGE_ERROR(HttpStatus.NOT_FOUND,"Cloud storage error."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST,"Email already in the list."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST,"Invalid request."),
    UNSUPPORTED_FILE_FORMAT(HttpStatus.BAD_REQUEST,"Unsupported file format.");

    @JsonIgnore
    private final HttpStatus status;

    private final String message;

    private final LocalDateTime timestamp;

    private String optionalMessage;


    ApiExceptionResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp.toString();
    }

    public String getOptionalMessage() {
        return optionalMessage;
    }

    public ApiExceptionResponse withOptionalMessage(String optionalMessage) {
        this.optionalMessage = optionalMessage;
        return this;
    }
}
