package thinkingJava.Decorator;

/**
 * Created by 15151 on 2019/5/7.
 */
public class MRenderServcie implements RenderService{
    @Override
    public void render() {
        System.out.println("移动端渲染页面");
    }
}
