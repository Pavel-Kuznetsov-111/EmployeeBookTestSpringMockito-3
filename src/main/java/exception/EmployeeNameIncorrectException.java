package pro.sky.skyproEmployeeBookTestSpringMockito.exception;

public class EmployeeNameIncorrectException extends RuntimeException{
    public EmployeeNameIncorrectException (String message){
        super(message);
    }
}
