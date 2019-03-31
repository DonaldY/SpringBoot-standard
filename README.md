## Spring Boot Standard

---
学习和总结 Spring Boot中 良好的配置和优雅的代码。欢迎讨论和PR。


## 大纲
### 配置
#### 注解配置
@RestController 标志此Controller提供RestAPI

@RequestMapping 及其变体。映射http请求url到java方法

@RequestParam 映射请求参数到java方法的参数

@PageableDefault 指定分页参数的默认值

@PathVariable 映射url片段到Java方法的参数

@JsonView 控制输出json输出内容
- 使用接口来声明多个视图
- 在值对象的get方法上指定视图
- 在Controller方法上指定视图

@GetMapping

@RequestBody 映射请求体到Java方法的参数

@Valid 注解和 BindingResult 验证请求参数的合法性并处理校验结果

##### 接受参数，对应生成多个实体
```
@PostMapping(value = "/create")
public ServerResponse create(@RequestAttribute Integer userId, @Valid @RequestBody Map<String, Object> models) {
    Project project = JSON.parseObject(JSON.toJSONString(models),  Project.class);
    Enterprise enterprise = JSON.parseObject(JSON.toJSONString(models), Enterprise.class);
}
```

### RESTful API 错误处理
> Spring Boot 中默认的错误处理机制
> 自定义异常处理

#### 处理异常 Handler
要处理的异常可以拿取抛出的异常，当然必须是Spring中抛出的异常，例如这种就不是：java.lang.NumberFormatException
```
@GetMapping(value = "/{id:\\d+}")

// 当正则不匹配时候，例如 /aaa ，那么就是路径不对，访问不到资源，就会报404
// 这种异常仍可以捕获
```


#### REST 封装结果

### 实体类（POJO、DTO、BO、VO）

#### VO
mybatis 可以返回 map，controller层可以将map封装进ModelAndView中

#### 若校验一个实体，可抽象出一个DTO（或者直接使用model，视情况而定）
才用 JSR-303 注解标记在属性上进行校验

https://blog.csdn.net/fanfan4569/article/details/83656626

### 插件
1. mybatis generator

2. pagehelper



## mybatis

### 动态查询
1. `where 1=1`
```
<select id="selectById" resultType="">
    SELECT * FROM xxx
    WHERE 1 = 1
    <if test="a != null">
      AND a = 1
    </if>
    <if test="b != null">
      AND b = 2
    </if>
</select>
```

2. `<trim prefixOverrides="and">` （推荐）
```
<select id="selectById" resultType="">
    SELECT * FROM xxx
    <where>
        <trim prefixOverrides="and">
           <if test="a != null">
             AND a = 1
           </if>
           <if test="b != null">
             AND b = 1
           </if>
        </trim>
    </where>
</select>
```

### 一对多情况下，一方存入对象中
```
<resultMap id="" type="">
    <association property="" javaType="" resultMap="" />
    <association property="" javaType="" resultMap="" />
</resultMap>
```

### 插入返回值

### `collection` 用法

### `association` 用法 

### 工具集
Spring
1. StringUtils
2. ObjectUtils
3. NumberUtils

### 异常状态

### 事务处理
1. 同时处理多个表



## 上传文件
### OSS 上传


## 跨域问题
1. 注解 : `@CrossOrigin`
2. 过滤器
```
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }
}
```
3. `public class WebConfig implements WebMvcConfigurer `
```
// 跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*");
```

## 消息
### Future


## `Optional``



## Reactive

## Lambda

## 时区问题

数据库时间与本机时间对应不上。

原数据库设置为 CST

```java
public class Application {
    
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("CST"));
        SpringApplication.run(Application.class, args);
    }
}
```

或者

可以试试在配置文件中添加：

`spring.jackson.time-zone=GMT+8`


### RestTemplate