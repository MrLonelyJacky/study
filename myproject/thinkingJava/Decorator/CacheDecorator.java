package thinkingJava.Decorator;

/**
 * Created by 15151 on 2019/5/7.
 * redis缓存装饰器
 */
public class CacheDecorator extends AbstractDecorator {

    public CacheDecorator(RenderService renderService) {
        super(renderService);
    }

    @Override
    public void render() {
        renderService.render();
        redisHandler();
    }

    private void redisHandler(){
        System.out.println("开启redis缓存数据");
    }
}
