package thinkingJava.Decorator;

/**
 * Created by 15151 on 2019/5/7.
 */
public abstract class AbstractDecorator implements RenderService{
    protected RenderService renderService;//具体的被装饰者

    public AbstractDecorator(RenderService renderService) {
        this.renderService = renderService;
    }

}
