# springMVC 
springMVC-learning

### Demo1 summary :
* __controller__:
  * ``controller``是可以处理页面的请求底层封装了``servlet``
  * ```java
    @Controller //controller注解表示控制器
    public Class DemoController
    {
        //设置请求映射
        @RequestMapping("/")
        public String index()
        {
          //返回值是请求页面名提供给Thymeleaf解析从而显示请求页面
          return "index";
        }
    } 
    
    ```
* __springMVC.xml__:
  * 用来配置Thymeleaf视图解析器
  * ```javascript 
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <!--扫描组件 别忘了增加xsi:schemaLocation。。。 -->
    <context:component-scan base-package="com.xcs.springmvc.Controller" />

    <!--配置Thymeleaf视图解析器-->
    <bean id="viewResolver" class="org.thymeleaf.spring6.view.ThymeleafViewResolver">
        <property name="order" value="1"/>
        <property name="characterEncoding" value="UTF-8"/>
        <property name="templateEngine">
            <bean class="org.thymeleaf.spring6.SpringTemplateEngine">
                <property name="templateResolver">
                    <bean class="org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver">
                        <!--视图前缀-->
                        <!--value 表示要解析的html页面所在的路径-->
                        <property name="prefix" value="/WEB-INF/templates/"/>
                        <!--视图后缀-->
                        <property name="suffix" value=".html"/>
                        <property name="templateMode" value="HTML"/>
                        <property name="characterEncoding" value="UTF-8"/>
                    </bean>
                </property>
            </bean>
        </property>
    </bean>
    </beans>

    ```
 * __web.xml__:
    * 用来配置servlet的请求作用域
    * ```javascript
      <web-app>
      <display-name>Archetype Created Web Application</display-name>
      <servlet>
      <servlet-name>SpringMVC</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <!--
        init-param:
        它的作用是指定Spring MVC框架的配置文件的位置。
        在这个配置中，contextConfigLocation是Spring MVC框架中DispatcherServlet的初始化参数之一，
        它指定了Spring应用上下文的配置文件的位置.contextConfigLocation参数的值被设置为classpath:springMVC.xml，
        这意味着Spring MVC将会在类路径下寻找名为springMVC.xml的配置文件作为Spring应用上下文的配置文件。
        这个配置文件通常包含了Spring MVC框架相关的Bean定义、拦截器配置、视图解析器等等。
      -->
      <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springMVC.xml</param-value>
      </init-param>
      <!--将DispatcherServlet 在服务器启动前,提前解析提升请求速度-->
      <load-on-startup>1</load-on-startup>
      </servlet>
      <servlet-mapping>
      <servlet-name>SpringMVC</servlet-name>
      <url-pattern>/</url-pattern>
      </servlet-mapping>
      </web-app> 
      ```
## Demo2 summary :
* ### 请求报错问题
---
  1. 如果请求没有跟任何的request Method的value参数匹配就会报404
  2. 如果请求方式匹配不上就会报405
  3. 如果请求参数匹配不成功就会报400
  4. 如果请求头匹配不成功就会报404

* ### @RequestMapping 参数问题
---
  1. __@RequestMapping注解的value值__
     * @RequestMapping注解的value属性通过请求的请求地址匹配请求映射
     * @RequestMapping注解的value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求
     * @RequestMapping注解的value属性必须设置，至少通过请求地址匹配请求映射
     * ```xml
       <a th:href="@{/testRequestMapping}">测试@RequestMapping的value属性-->/testRequestMapping </a><br/>
       <a th:href="@{/test}">测试@ReuqestMapping的value属性-->/test</a>
       ```
     * ```java
        @RequestMapping(
            value = { "/testRequestMapping", "/test" }
        )
        public String testRequestMapping(){
             return "success";
        }
       ```
  2. ### @RequestMapping注解的method属性
  ---
  * @RequestMapping注解的method属性通过请求的请求方式（get或post）匹配请求映射
  * @RequestMapping注解的method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配多种请求方式的请求
     ```xml
       <a th:href="@{/test}">测试@RequestMapping的value属性-->/test</a><br>
       <form th:action="@{/test}" method="post">
       <input type="submit">
       </form>
     ```
     ```java
     @RequestMapping(
           value = {"/testRequestMapping", "/test"},
           method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String testRequestMapping(){
           return "success";
    }
     ```
  3. ### @RequestMapping注解的params属性
---
  * @RequestMapping注解的params属性通过请求的请求参数匹配请求映射
  * @RequestMapping注解的params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
        
  *     "param"：要求请求映射所匹配的请求必须携带param请求参数
        "!param"：要求请求映射所匹配的请求必须不能携带param请求参数
        "param=value"：要求请求映射所匹配的请求必须携带param请求参数且param=value
        "param!=value"：要求请求映射所匹配的请求必须携带param请求参数但是param!=value
  * ```xml
    <a th:href="@{/test(username='admin',password=123456)">测试@RequestMapping的params属性-->/test</a><br>
    ```
  * ```java
    @RequestMapping(
        value = {"/testRequestMapping", "/test"}
        ,method = {RequestMethod.GET, RequestMethod.POST}
        ,params = {"username","password!=123456"}
    )
    public String testRequestMapping(){
        return "success";
    }
    ```
    > 注：若当前请求满足@RequestMapping注解的value和method属性，但是不满足params属性，此时页面回报错400：Parameter conditions "username, password!=123456" not met for actual request parameters: username={admin}, password={123456}
  4. ### SpringMVC支持路径中的占位符 (重点)
     *     原始方式：/deleteUser?id=1
           rest方式：/deleteUser/1
           SpringMVC路径中的占位符常用于RESTful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，在通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参
     * ```xml
        <a th:href="@{/testRest/1/admin}">测试路径中的占位符-->/testRest</a><br>    
       ```
     * ```java
        @RequestMapping("/testRest/{id}/{username}")
        public String testRest(@PathVariable("id") String id, @PathVariable("username") String username){
              System.out.println("id:"+id+",username:"+username);
              return "success";
        }
        //最终输出的内容为-->id:1,username:admin
       ```  
* ### @RequestParam
---
  * @RequestParam是将请求参数和控制器方法的形参创建映射关系
  * @RequestParam注解一共有三个属性:
    1. value: 指定为形参赋值的请求参数的参数名
    2. required: 设置是否必须传输此请求参数, 默认值为true 若参数为true时候, 则当前请求必须传输value指定的请求参数, 若没有传输该请求参数, 且没有默认设置defaultValue属性, 则会页面报错404:
    Required String parameter 'xxx' is not present; 若设置为false, 则当前请求不是必须传输value所指定的请求参数, 若没有传输, 则注解
    所标识的参数值为null
    3. defaultValue: 不管required属性值为true或false, 当value所指定的请求参数没有传输或传输的值为''时,则使用默认值作为形式参数

* ### Post请求出现乱码问题解决方案
---
```xml
    <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceResponseEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
    </filter>
```
## RESTful:

---
1. ### RESTful 简介:
---
REST：Representational State Transfer，表现层资源状态转移。

__a>资源__

资源是一种看待服务器的方式，即，将服务器看作是由很多离散的资源组成。每个资源是服务器上一个可命名的抽象概念。因为资源是一个抽象的概念，所以它不仅仅能代表服务器文件系统中的一个文件、数据库中的一张表等等具体的东西，可以将资源设计的要多抽象有多抽象，只要想象力允许而且客户端应用开发者能够理解。与面向对象设计类似，资源是以名词为核心来组织的，首先关注的是名词。一个资源可以由一个或多个URI来标识。URI既是资源的名称，也是资源在Web上的地址。对某个资源感兴趣的客户端应用，可以通过资源的URI与其进行交互。

__b>资源的表述__

资源的表述是一段对于资源在某个特定时刻的状态的描述。可以在客户端-服务器端之间转移（交换）。资源的表述可以有多种格式，例如HTML/XML/JSON/纯文本/图片/视频/音频等等。资源的表述格式可以通过协商机制来确定。请求-响应方向的表述通常使用不同的格式。

__c>状态转移__

状态转移说的是：在客户端和服务器端之间转移（transfer）代表资源状态的表述。通过转移和操作资源的表述，来间接实现操作资源的目的。

---
2. ### RESTful 实现:
---
具体说，就是 HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。

它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。

REST 风格提倡 URL 地址使用统一的风格设计，从前到后各个单词使用斜杠分开，不使用问号键值对方式携带请求参数，而是将要发送给服务器的数据作为 URL 地址的一部分，以保证整体风格的一致性。

性。

|   操作	    |        传统方式         |        REST风格        |
|:--------:|:-------------------:|:--------------------:|
|   查询操作   |  	getUserById?id=1  |  	user/1-->get请求方式   |
|  保存操作	   |      saveUser	      |   user-->post请求方式    |
|  删除操作	   |  deleteUser?id=1	   | user/1-->delete请求方式  |
|  更新操作	   |     updateUser	     |    user-->put请求方式    |

3. ### HiddenHttpMethodFilter

---
由于浏览器只支持发送get和post方式的请求，那么该如何发送put和delete请求呢？

SpringMVC 提供了 HiddenHttpMethodFilter 帮助我们将 POST 请求转换为 DELETE 或 PUT 请求

HiddenHttpMethodFilter 处理put和delete请求的条件：

a>当前请求的请求方式必须为post

b>当前请求必须传输请求参数_method

满足以上条件，HiddenHttpMethodFilter 过滤器就会将当前请求的请求方式转换为请求参数_method的值，因此请求参数_method的值才是最终的请求方式

在web.xml中注册HiddenHttpMethodFilter
```xml
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
> 注:
> 
> 前为止，SpringMVC中提供了两个过滤器：CharacterEncodingFilter和HiddenHttpMethodFilter  
>
> 在web.xml中注册时，必须先注册CharacterEncodingFilter，再注册HiddenHttpMethodFilter
>
> 原因：
>
>  * ``在 CharacterEncodingFilter 中通过 request.setCharacterEncoding(encoding) 方法设置字符集的``
>  * ``request.setCharacterEncoding(encoding) 方法要求前面不能有任何获取请求参数的操作``
>  * ``而 HiddenHttpMethodFilter 恰恰有一个获取请求方式的操作``
>  * `` String paramValue = request.getParameter(this.methodParam); ``

