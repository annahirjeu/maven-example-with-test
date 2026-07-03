//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 *
 * @author aminoiu
 * @since 7/3/2026
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleException(NullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                500,
                "Null Pointer Exception",
                ex.getMessage()
        );
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                300,
                "Internal Server Error 121365",
                ex.getMessage()
        );
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
}
