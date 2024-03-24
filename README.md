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
