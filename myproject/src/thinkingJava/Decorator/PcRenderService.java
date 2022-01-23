package thinkingJava.Decorator;

/**
 * Created by 15151 on 2019/5/7.
 */
public class PcRenderService implements RenderService{
    @Override
    public void render() {
        System.out.println("pc页面渲染");
    }
}
