
* [@JsonView使用](#@JsonView使用)
* [spring参数验证](#spring参数验证)

	* [@Valid，@Validated和BindingResult](#@Valid，@Validated和BindingResult)
	* [自定义验证](#自定义验证)

* [异常处理](#异常处理)
	* [自定义错误页面](#自定义错误页面)
	* [全局异常处理](#全局异常处理)

* [过滤器](#过滤器)
* [拦截器](#拦截器)

> # <a id="@JsonView使用">@JsonView使用</a>

很多时候我们返回给前台的视图数据结构中有很多的字段是不需要的，
比如返回一个用户的信息，这个时候我们需要将用户的敏感数据字段进行隐藏
不对其进行返回，只返回特定的视图结构那么这个时候我们就可以使用@JsonView来实现

**User.java**

```
package com.pl.demo.bean;
import com.fasterxml.jackson.annotation.JsonView;

public class User {
    //1.使用接口来声明多个视图
    public interface SimpleUserInfo {
    }

    public interface DetailUserInfo extends SimpleUserInfo {
    }

    //2.在值对象的get方法/属性上指定视图
    @JsonView(SimpleUserInfo.class)
    private String username;
    @JsonView(DetailUserInfo.class)
    private String password;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

```

**UserController.java**

```
@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    @RequestMapping("/query")
    @JsonView(User.SimpleUserInfo.class)
    public List<User> query(@RequestParam String username) {
        return new ArrayList<User>() {{
            add(new User("张三", "123"));
            add(new User("李四", "456"));
            add(new User("王五", "789"));
            add(new User(username,"xxx"));
        }};
    }
}
```

> # <a id = "spring参数验证"> spring参数验证 </a>

用户登陆时用户名密码都不为空，以前我们的做法是

```
if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
    return xxx;
}
```

如果校验的参数比较多，那么代码就没有什么可读性了；庆幸的是我们使用了框架这些都会有提供校验的方式那么这记录一下
spring的方式


#### <a id="@Valid,@Validated和BindingResult">@Valid,@Validated和BindingResult</a>

* @Valid

是使用hibernate validation的时候使用

* @Validated 

是只用spring  Validator 校验机制使用

* BindingResult

public interface BindingResult extends Errors

在使用@Valid，@Validated校验不满足条件的情况下默认是直接返回不进入方法体，
这个时候如果我想对这次的错误进行日志记录那么我们就可以使用BindingResult

**User.java**

```
public class User {
    @NotBlank(message="用户名不可为空")
    private String username;
    @NotBlank(message="密码不可为空")
    private String password;
    
    //get,set...
}
```


**UserController.java**

```
    @PostMapping("/save")
    public ResultData saveUser(@Valid @RequestBody User user,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){  
             StringBuffer sb = new StringBuffer();  
             for(ObjectError objectError : bindingResult.getAllErrors()){  
                 sb.append(((FieldError)objectError).getField() +" : ").append(objectError.getDefaultMessage());  
             }  
            return xx;
        }
        //...
    }
```

#### <a id="自定义验证">自定义验证</a>

如果默认提供的验证不能满足我们的要求，我们也可以自定验证逻辑

1.创建一个注解类,使用@Constraint标记validatedBy=执行的逻辑类

```
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyValidator.class)
public @interface MyConstraint {
    /**
     * 以下3个属性必须定义的
     * @return
     */
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

2.创建验证逻辑类

需要实现**ConstraintValidator<验证注解类型,验证数据类型>**

验证数据类型：指定了验证数据类型则注解标记指定类型变量上时才会生效，如果要所有都生效可以使用object

在spring容器启动时会将实现了ConstraintValidator接口的类全部转载，也就意味着可以在实现了ConstraintValidator的接口中使用spring容器中的任何东西

```
public class MyValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private UserService service;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("initialize");
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        User user = service.getUserInfo(1);
        System.out.println(user.getUsername());
        System.out.println(s);
        if (s == null){
            return false;
        }
        return true;
    }
}
```

3.使用

```
public class User {
 
    @MyConstraint(message = "username不可以为null")
    private String username;
    private String password;

    //get,set...
}
```

```
@RequestMapping("/insert")
@JsonView(User.DetailUserInfo.class)
public User insertUser(@Validated @RequestBody User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        bindingResult.getAllErrors().forEach(error -> {
            System.out.println(error.getDefaultMessage());
        });
    }
    return userService.insertUser(user);
}
```

4.测试

```
@Test
public void insertUserTest() throws Exception {
    String data = "{\"password\":\"123\"}";
    String result =  mvc.perform((MockMvcRequestBuilders
            .post("/user/insert"))
            .content(data)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    System.out.println(result);
}
```

> # <a id="异常处理">异常处理</a>

#### 默认异常处理

spring-boot根据不同的请求类型返回不同的结果：

浏览器异常：返回一个错误html页面

其他客户端：返回json数据

sprng是通过BasicErrorController来处理错误请求

```
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class BasicErrorController extends AbstractErrorController {
	
	//如果请求头是text/html则返回一个视图
	 @RequestMapping(
        produces = {"text/html"}
    )
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = this.getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request, this.isIncludeStackTrace(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
        return modelAndView == null ? new ModelAndView("error", model) : modelAndView;
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = this.getErrorAttributes(request, this.isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = this.getStatus(request);
        return new ResponseEntity(body, status);
    }

}
```



#### <a id="自定义异常处理"> 自定义错误页面</a>

* ** <a id="自定义错误页面">自定义错误页面</a>**

在resources目录下建立resources/error目录创建对应的错误页面

-resources

---resources

----error

-----500.html

-----404.html

-----等.html

* **<a id="全局异常处理">全局异常处理</a>**

```
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handRuntimeException(RuntimeException e) {
        return new HashMap<String, Object>() {{

            put("error", e.getLocalizedMessage());
        }};
    }
}
```


> # <a id="过滤器">过滤器</a>

### spring-boot中配置拦截器

以前我们配拦截器都是在web.xml中配置但是使用spring boot中没有了web.xml那么我们怎么配置Filter了

* **方法1：**

直接将Filter装配到spring容器中

```
@Component
public class MyFilter implements Filter {
	//...
}
```

在使用很多第三方的Filter时它们没有使用@Component标记装配，那么就要使用如下配置了;

* ** 方法2：**

```
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean RegistrationMyFilter() {
        //创建注册器
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //将filter注册到主注册器中
        MyFilter myFilter = new MyFilter();
        registration.setFilter(myFilter);
        //设置过滤路径
        registration.setUrlPatterns(new ArrayList<String>() {{
            add("/*");
        }});
        return registration;
    }
}
```

Filter所拦截的请求不能获取相关controller的信息因为它也不知道自己处理的时谁

> # <a id="拦截器">拦截器</a>

```
@Component
public class MyInterceptor implements HandlerInterceptor {
  
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
      System.out.println("1.preHandle");
        return true;
    }
    
   
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("2.postHandle");
    }

    /**
     * 不管是否抛出异常都会执行该方法
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("3.afterCompletion");
    }
}
```


```
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }
}
```

拦截器可以获取到相关controller信息但是不能获取spring实际注入的参数

> # <a id="AOP(切面)">AOP(切面)</a>


切面是spring中的一个很重要的概念也是一个基础，简单的理解就是对一段代码进行增强处理;

![输入图片说明](https://gitee.com/uploads/images/2018/0507/222919_9fdbb4fd_966228.png "屏幕截图.png")

| 注解|描述|
| -- | --|
|@Pointcut|切入点|
|@Before|前置通知，在方法执行之前执行|
|@After|后置通知，在方法执行之后执行 |
|@AfterRunning|返回通知，在方法返回结果之后执行|
|@AfterThrowing|异常通知，在方法抛出异常之后|
|@Around|环绕通知，围绕着方法执行|

* **简单的切面实现**

```
@Aspect
@Component
public class MyAspect {
    /**
     * @param pjp 当前切入方法上下文信息
     * @return
     */
    @Around("execution(* com.pl.demo.controller.*.*(..))")
    public Object handlerControllerTest(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("开始切入");
        //获取到切入方法参数信息
        Object[] objects = pjp.getArgs();
        Arrays.stream(objects).forEach(d -> {
            System.out.println(d);
        });
        //相当doFilter
        Object object = pjp.proceed();
        System.out.println("结束");
        return object;
    }
    @Pointcut("execution(* com.pl.demo.controller.*.*(..))")
    public void pointcutTest(){
    }

    @Before("pointcutTest()")
    public void beforeTest() {
        System.out.println("beforeTest");
    }
}
```
切面能很好的获取注入参数在注入之前做一些增强操作，但是无法获取Http请求响应的对象信息


### 执行顺序

![输入图片说明](https://gitee.com/uploads/images/2018/0507/232912_0eaeebc8_966228.png "屏幕截图.png")
