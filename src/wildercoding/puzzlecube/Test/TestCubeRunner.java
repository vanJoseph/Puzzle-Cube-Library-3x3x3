package wildercoding.puzzlecube.Test;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by dwilder1181 on 3/27/2017.
 */
public class TestCubeRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCube.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
