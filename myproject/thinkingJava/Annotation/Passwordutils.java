package thinkingJava.Annotation;

/**
 * Created by 15151 on 2019/2/19.
 */
public class Passwordutils {
    @UseCase(id = 1, description = "always true")
    public boolean isRight() {
        return true;
    }

    @UseCase(id = 2)
    private void say() {
        System.out.println("hahah");
    }
}
