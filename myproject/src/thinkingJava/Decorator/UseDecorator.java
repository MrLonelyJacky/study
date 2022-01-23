package thinkingJava.Decorator;

/**
 * Created by 15151 on 2019/5/8.
 */
public class UseDecorator {
    public static void main(String[] args) {
        RenderService renderService = new PcRenderService();
        renderService = new CacheDecorator(renderService);
        renderService = new CdnDecorator(renderService);
        renderService.render();
        RenderService renderService1 = new CdnDecorator(new CacheDecorator(new MRenderServcie()));
    }
}
