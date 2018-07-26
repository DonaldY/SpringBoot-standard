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


### 插件
1. mybatis generator

2. pagehelper

