package thinkingJava.enumTest;

/**
 * Created by 15151 on 2019/3/5.
 */
public enum CtLanguage {
    //枚举类必须定义再属性和其他方法的前面
    EngLish,Chinese("this is chinese");

    private String des;
    private Test[] tests;
    //无论构造器是否私有都一样，因为枚举构造器智能再内部使用
    private CtLanguage() {
    }
     CtLanguage(String desc) {
        this.des = desc;
    }
    public String getDes(){
        return this.des;
    }

}
