import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result apptest = JUnitCore.runClasses(appTest.class);
        Result createmaptest = JUnitCore.runClasses(CreateMapTest.class);

        for (Failure failure : apptest.getFailures()) {
            System.out.println(failure.toString());
        }

        for (Failure failure : createmaptest.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(apptest.wasSuccessful());
        System.out.println(createmaptest.wasSuccessful());
    }
}  	