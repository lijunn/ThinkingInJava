package com.lijun.spring.mvcframework.v1;

import com.lijun.spring.mvcframework.annotation.GPAutowired;
import com.lijun.spring.mvcframework.annotation.GPController;
import com.lijun.spring.mvcframework.annotation.GPRequestMapping;
import com.lijun.spring.mvcframework.annotation.GPService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author : LiJun
 * @date : 2020-04-16 11:59
 **/
public class GPDispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    /**扫描的全部类名*/
    private List<String> classNames = new ArrayList<String>();

    /**ioc容器*/
    private Map<String,Object> ioc = new HashMap<String,Object>();

    /**HandlerMapping*/
//    private Map<String,Method> handlerMapping = new HashMap<String, Method>();

    /**HandlerMapping*/
    private List<HandlerMapping> handlerMapping = new ArrayList<HandlerMapping>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //匹配url
        String requestURI = req.getRequestURI();

        for (HandlerMapping handler:handlerMapping){
            if (!requestURI.equals(handler.getUrl())){continue;}
            Method method = handler.getMethod();

            //参数1：url中传入的实参
            Map<String,String[]> urlParams = req.getParameterMap();
            //参数2：方法的形参列表
            Class<?> [] parameterTypes = method.getParameterTypes();
            //参数3：方法中注解参数位置
            Map<Integer, String> paramIndex = handler.getParamIndex();
            //参数4：保存赋值参数
            Object[] paramValues = new Object[parameterTypes.length];

            for (int i = 0;i < parameterTypes.length;i++){
                Class<?> parameterType = parameterTypes[i];
                if (parameterType == HttpServletRequest.class){
                    paramValues[i] = req;
                }else if (parameterType == HttpServletResponse.class){
                    paramValues[i] = resp;
                }else{
                    //根据形参位置获取被注解的参数的名称
                    String annotationParamName = paramIndex.get(i);
                    if (urlParams.containsKey(annotationParamName)){

                        //实参的 param.getValue() 是一个数组，将其转换为字符串
                        String strParam = Arrays.toString(urlParams.get(annotationParamName)).replaceAll("\\[|\\]", " ").trim();

                        //url传入的参数都是String类型的，这里转换为方法参数要进行类型转换
                        paramValues[i] = convert(strParam,parameterType);

                    }else {
                        //没有注解的参数传空
                        paramValues[i] = null;
                    }
                }
            }

            try {
                Object result = method.invoke(handler.getTarget(),paramValues);

                resp.getWriter().write(result.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        //2.类型转换
        //3.正则
    }

    /**
     * 参数类型转换策略
     * @param pa
     * @param parameterType
     * @return
     */
    private Object convert(String pa, Class<?> parameterType) {
        if (parameterType == String.class){
            return String.valueOf(pa);
        }else if (parameterType == Integer.class){
            return Integer.valueOf(pa);
        }else {
            return pa;
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {


        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描相关类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.初始化所有类，并且加入到容器中
        doInstance();

        //4.DI 依赖注入
        doAutowired();

        //5.初始化 HandlerMapping,映射方法
        initHandlerMapping();

        System.out.println("GP framework init .....");
    }

    private void doLoadConfig(String contextConfigLocation) {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != is){is.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doScanner(String scanPackage) {
        //将 com.lijun.spring 转换成 /com/lijun/spring
        //chasspath:/com/lijun/spring
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));

        File classPath = new File(url.getFile());

        for (File file:classPath.listFiles()){
            //判断如果是文件夹则递归
            if (file.isDirectory()){
                doScanner(scanPackage+"."+file.getName());
            }
            //判断是否为 class 文件
            String fileName = file.getName();
            if (fileName.endsWith(".class")){
                String className = scanPackage +"."+fileName.replaceAll(".class","");
                classNames.add(className);
            }
        }
    }


    private void doInstance() {

        for (String className:classNames){
            try {
                Class<?> clazz = Class.forName(className);
                GPController gpController = clazz.getAnnotation(GPController.class);
                GPService gpService = clazz.getAnnotation(GPService.class);
                if (null != gpController){
                    //默认使用类名首字母小写做为beanNamey
                    String beanName = tolowerFirstCase(clazz.getSimpleName());
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);

                }else if (null != gpService){
                    //1.默认使用类名首字母小写做为beanName
                    String beanName = tolowerFirstCase(clazz.getSimpleName());

                    //2.自定义beanName
                    if (!"".equals(gpService.value().trim())){
                        beanName = gpService.value().trim();
                    }

                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);

                    //3.根据类型注入实现类
                    for (Class<?> i:clazz.getInterfaces()){
                        if (ioc.containsKey(i)){
                            throw new Exception("The beanName is exists!!");
                        }
                        //使用接口全限定名做为 beanName
                        ioc.put(i.getName(),instance);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void doAutowired() {


        for (Map.Entry<String,Object> entry:ioc.entrySet()){
            try {
                Class<?> clazz = entry.getValue().getClass();
                //遍历所有字段
                for (Field field:clazz.getDeclaredFields()){
                    GPAutowired gpAutowired = field.getAnnotation(GPAutowired.class);
                    if (null != gpAutowired){
                        String beanName = null;
                        //1.先检查是否指定了 beanName
                        String value = gpAutowired.value().trim();
                        if (!"".equals(value)){
                            beanName = value;
                        }else {
                            //2.默认类名小写查找
                            beanName = tolowerFirstCase(field.getType().getSimpleName());
                            //如果找不到，则说明这个字段是使用类型注入的
                            if (!ioc.containsKey(beanName)){
                                //3.使用类型查找
                                beanName = field.getType().getName();
                            }
                        }
                        //设置访问权限
                        field.setAccessible(true);

                        if (null == ioc.get(beanName)){throw new Exception("not found beanName"+field.getName());}

                        //注入
                        field.set(entry.getValue(),ioc.get(beanName));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void initHandlerMapping() {
        for (Map.Entry<String,Object> entry:ioc.entrySet()){

            Class<?> clazz = entry.getValue().getClass();
            GPRequestMapping classRequestMapping = clazz.getAnnotation(GPRequestMapping.class);
            if (null == classRequestMapping){continue;}

            String baseUrl = classRequestMapping.value().trim();

            //遍历public方法
            for (Method method:clazz.getMethods()){
                GPRequestMapping annotation = method.getAnnotation(GPRequestMapping.class);
                if (null == annotation){continue;}

                String methodUrl = annotation.value().trim();
                String url = "/"+baseUrl+"/"+methodUrl;
                url = url.replaceAll("/+","/");

                handlerMapping.add(new HandlerMapping(url,method,entry.getValue()));
            }
        }
    }


    /**
     * 首字母小写
     * @param str
     * @return
     */
    private String tolowerFirstCase(String str){
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


}
