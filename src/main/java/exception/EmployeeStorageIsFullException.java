package pro.sky.skyproEmployeeBookTestSpringMockito.exception;

public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException(String message){
        super(message);
    }
}
