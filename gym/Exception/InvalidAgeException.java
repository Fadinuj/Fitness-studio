package gym.Exception;

public class InvalidAgeException extends Exception {
    @Override
    public String getMessage(){
        return "Error: Client must be at least 18 years old to register";
    }
}
