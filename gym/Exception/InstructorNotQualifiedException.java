package gym.Exception;

public class InstructorNotQualifiedException extends Exception {
    @Override
    public String getMessage(){
        return "Error: Instructor is not qualified to conduct this session type.";
    }
}
