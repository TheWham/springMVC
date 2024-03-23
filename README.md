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
