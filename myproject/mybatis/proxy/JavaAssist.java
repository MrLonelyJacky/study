package mybatis.proxy;

import org.apache.ibatis.javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaAssist {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassPool classPool = ClassPool.getDefault();
        //指定创建的类
        CtClass ctClass = classPool.makeClass("mybatis.proxy.HeiHeiHei");
        //指定一个属性
        CtField ctFieldHahaha = new CtField(classPool.get("java.lang.String"),"hahaha",ctClass);
        ctFieldHahaha.setModifiers(Modifier.PRIVATE);

        //为属性hahaha添加方法
        ctClass.addMethod(CtNewMethod.getter("getHahaha",ctFieldHahaha));
        ctClass.addMethod(CtNewMethod.setter("setHahaha",ctFieldHahaha));

        ctClass.addField(ctFieldHahaha,CtField .Initializer.constant ("MyName"));
        //无参构造器
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{},ctClass);
        ctConstructor.setBody(" { hahaha =\"MyName\"; }");
        ctClass.addConstructor(ctConstructor);
        //指定execute方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType,"execute",new CtClass[]{},ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        //指定方法中的内容
        ctMethod.setBody("System.out.println(\"execute hahaha\");");
        ctClass.addMethod(ctMethod);
        //指定文件输出路径 class文件
        ctClass.writeFile("E:\\projectFile\\study\\myproject\\");
        //转换为java的class对象 进行反射操作
        Class toClass = ctClass.toClass();
        Object o = toClass.newInstance();
        Method execute = toClass.getMethod("execute");
        Object invoke = execute.invoke(o);

    }

    public void execute(){
        System.out.println("execute hahaha");
    }






}
