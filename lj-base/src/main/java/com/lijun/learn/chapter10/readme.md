### 第十章 内部类

#### 非静态内部类

##### 特性
    
    1.内部类对象只能通过外部类对象来创建，可以调用外部类对象的方法或者使用 .new 语法创建
    2.创建过程：先创建外部类的对象，然后在创建内部类的对象，所以内部类对象会持有外部类的引用

1.内部类为什么可以访问外部类的变量
    
    因为内部类对象创建时持有外部类对象的引用
 
2.内部类的作用

    可以实现多继承，接口实现的多继承只能通过实现接口来完成，而内部类可以同时继承多个类或抽象类
    
    

#### 静态内部类

##### 特性

    1.和正常的类一样，只不过引用的时候要加上外部类的前缀