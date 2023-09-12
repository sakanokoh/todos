package sn.ept.git.seminaire.cicd.exceptions.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import sn.ept.git.seminaire.cicd.exceptions.ForbiddenException;
import sn.ept.git.seminaire.cicd.exceptions.InvalidException;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.exceptions.message.ErrorMessage;


public class ExceptionHandlerTestHelper extends RestResponseEntityExceptionHandler {

    public ResponseEntity<ErrorMessage> testNotFoundExceptionHandler(ItemNotFoundException ex, WebRequest request) {
        return notFound(ex, request);
    }

    public ResponseEntity<ErrorMessage> testConflictExceptionHandler(ItemExistsException ex, WebRequest request) {
        return conflict(ex, request);
    }

    public ResponseEntity<ErrorMessage> testBadRequestExceptionHandler(InvalidException ex, WebRequest request) {
        return badRequest(ex, request);
    }

    public ResponseEntity<ErrorMessage> testPermissionDeniedExceptionHandler(ForbiddenException ex, WebRequest request) {
        return permissionDenied(ex, request);
    }

    public ResponseEntity<ErrorMessage> testInternalErrorExceptionHandler(Exception ex, WebRequest request) {
        return internalError(ex, request);
    }
}
