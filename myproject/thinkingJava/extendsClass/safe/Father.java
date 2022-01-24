package thinkingJava.extendsClass.safe;

/**
 * Created by 15151 on 2019/4/14.
 */
public class Father {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        //保证数据安全  疑问是子类继承的话会改变父类的I值吗
        if (i>0){
            this.i = i;
        }
    }

}
