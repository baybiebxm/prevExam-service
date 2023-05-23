package sit.int204.previousexam.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponses {
    private String status;
    private String message;
    private String instance;
    private String stackTrace;
    private List<ErrorFiled> errors;

}
