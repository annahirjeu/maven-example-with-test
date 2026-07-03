//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example.exception;

import java.time.LocalDateTime;

/**
 *
 * @author aminoiu
 * @since 7/3/2026
 */
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
