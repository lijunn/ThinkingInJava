package com.lijun.learn.pattern.structure.proxy.dynamicproxy.gpproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author : LiJun
 * @date : 2020-04-01 10:57
 * 手动实现JDK 代理
 *
 * 动态生成代理对象，重写被代理对象的接口的方法，然后在这些方法中反射调用被代理对象
 **/
public class GPProxy {

    public static final String ln = "\n";


    public static Object newProxyInstance(GPClassLoader classLoader, Class<?>[] interfaces, GPInvocationHandler handler) {
        try {
            //1.生成源码字符串
            String src = getSourceStr(interfaces);

            //2.将源码写为 java 文件
            String filePath = GPProxy.class.getResource("").getPath();
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            //3、把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manage.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null, manage, null, null, null, iterable);
            task.call();
            manage.close();

            //4.加载 class 文件
            //4、编译生成的.class文件加载到JVM中来
            Class proxyClass =  classLoader.findClass("$Proxy0");

            //使用当前应用的类加载器
            // Class proxyClass =  GPProxy.class.getClassLoader().loadClass(GPClassLoader.class.getPackage().getName() + ".$Proxy0");

            Constructor c = proxyClass.getConstructor(GPInvocationHandler.class);
            f.delete();

            //5、返回字节码重组以后的新的代理对象
            return c.newInstance(handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成源码
     * @param interfaces i
     * @return
     */
    private static String getSourceStr(Class<?>[] interfaces) {

        StringBuffer sb = new StringBuffer();
        Class<?> aClass = interfaces[0];

        sb.append(GPProxy.class.getPackage()+";"+ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("import "+GPInvocationHandler.class.getName()+";" + ln);
        sb.append("public class $Proxy0 implements " + aClass.getName() + " {" + ln);
        sb.append("private GPInvocationHandler handler;" + ln);
        sb.append("public $Proxy0(GPInvocationHandler handler){" + ln);
        sb.append("this.handler = handler;" + ln);
        sb.append("}");

        for (Method method : aClass.getMethods()) {
            Class<?>[] params = method.getParameterTypes();

            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValues = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();

            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " + paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");
                if (i < params.length - 1) {
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }


            sb.append("public " + method.getReturnType().getSimpleName() + " " + method.getName() + " (" + paramNames.toString() + " ) {" + ln);
            sb.append("try{" + ln);

            sb.append("Method m = "+interfaces[0].getName()+".class.getMethod(\"" + method.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + ln);
            sb.append("Object obj = handler.invoke(this,m,new Object[]{" + paramValues.toString() + "} );" + ln);
            sb.append(hasReturnValue(method.getReturnType()) ? "return ("+method.getReturnType().getSimpleName()+")obj;" : "return;" + ln);
            sb.append("}catch(Error e){");
            sb.append("throw new UndeclaredThrowableException(e);" + ln);
            sb.append("}catch(Throwable e){" + ln);
            sb.append("throw new UndeclaredThrowableException(e);" + ln);
            sb.append("}");

            sb.append("}" + ln);
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * 判断是否有返回值
     * @param clazz
     * @return
     */
    private static boolean hasReturnValue(Class<?> clazz) {
        return clazz != void.class;
    }


    private static String toLowerFirstCase(String src) {
        char[] chars = src.toCharArray();
        //int 参数特殊处理
        if (chars[0] == 'i') {
            chars[0] = 'a';
        } else {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

}
