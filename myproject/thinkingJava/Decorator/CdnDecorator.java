package thinkingJava.Decorator;

import java.io.*;

/**
 * Created by 15151 on 2019/5/7.
 */
public class CdnDecorator extends AbstractDecorator {
    public CdnDecorator(RenderService renderService) {
        super(renderService);
    }

    @Override
    public void render() {
        renderService.render();
        cdnHandler();
    }

    private void cdnHandler() {
        System.out.println("开启cdn缓存");
    }

    public static void main(String[] args) throws FileNotFoundException {
       FileInputStream fileInputStream = new FileInputStream("ss");
       BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

    }
}
