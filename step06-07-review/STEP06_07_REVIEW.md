# Spring Step06~07 복습 정리

이 문서는 `step06-mvc-basic`과 `step07-mvc-form`에서 배운 Spring MVC 핵심 개념을 수업 후 다시 따라가기 좋게 정리한 복습 노트입니다. 마지막에는 전체 흐름을 한 번에 확인할 수 있는 총정리 예제 `step06-07-review`를 추가했습니다.

## 1. Step06: Spring MVC 기본 구조

### 핵심 개념

- Spring MVC는 웹 요청을 `DispatcherServlet` 하나가 먼저 받고, 적합한 컨트롤러로 넘겨주는 구조다.
- 컨트롤러가 반환한 뷰 이름을 `ViewResolver`가 실제 JSP 파일 경로로 변환한다.
- `Model`에 데이터를 담아 JSP에 전달하면 EL(`${...}`)로 꺼내 쓸 수 있다.

### 요청 처리 흐름

```
브라우저 → DispatcherServlet → HandlerMapping → @Controller → ViewResolver → JSP
```

### web.xml: DispatcherServlet 등록

```xml
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring/dispatcher-context.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

- `load-on-startup>1`: 서버 시작 시 DispatcherServlet을 미리 생성한다.
- `url-pattern>/`: 모든 요청을 DispatcherServlet이 받는다 (단, `.jsp` 직접 접근 제외).

### web.xml: 한글 인코딩 필터

```xml
<filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

### dispatcher-context.xml: MVC 설정

```xml
<!-- 컨트롤러 자동 등록 -->
<context:component-scan base-package="com.spring.controller"/>

<!-- 어노테이션 기반 MVC 활성화 -->
<mvc:annotation-driven/>

<!-- ViewResolver: prefix + 뷰이름 + suffix = 실제 JSP 경로 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/views/"/>
    <property name="suffix" value=".jsp"/>
</bean>
```

### @Controller, @RequestMapping, Model

```java
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello(Model model) {
        model.addAttribute("greeting", "안녕하세요!");
        return "hello";   // → /WEB-INF/views/hello.jsp
    }

    @GetMapping("/greet")
    public String greet(String name, Model model) {  // 쿼리스트링 name 자동 바인딩
        model.addAttribute("message", name + "님, 반갑습니다.");
        return "greet";
    }
}
```

| 어노테이션 | 역할 |
|---|---|
| `@Controller` | Spring이 이 클래스를 컨트롤러로 인식하고 Bean으로 등록 |
| `@RequestMapping("/hello")` | 클래스 전체에 기본 URL prefix 지정 |
| `@GetMapping` | GET 요청 처리 (`@RequestMapping(method=GET)` 축약) |
| `Model` | 뷰에 전달할 데이터를 담는 컨테이너 (내부적으로 request scope에 저장) |

### @RequestParam vs 파라미터 직접 바인딩

```java
// 명시적 방식 - 파라미터 이름, 기본값을 직접 설정 가능
@GetMapping("/greet")
public String greet(@RequestParam(name = "name", defaultValue = "손님") String name, Model model) { ... }

// 단순 바인딩 - 쿼리스트링 파라미터 이름과 변수명이 같으면 자동 바인딩
@GetMapping("/greet")
public String greet(String name, Model model) { ... }
```

### 복습 질문

- DispatcherServlet이 없으면 어떤 일이 일어나는가?
- `ViewResolver`가 하는 일을 한 문장으로 설명하라.
- `Model`에 담은 데이터는 JSP에서 어떻게 꺼내 쓰는가?
- `@RequestMapping("/hello")`와 `@GetMapping("/greet")`가 합쳐지면 실제 URL은 무엇인가?

---

## 2. Step07: 폼 처리, PathVariable, ModelAndView

### 핵심 개념

- GET 요청은 데이터 조회, POST 요청은 데이터 전송(폼 제출)에 사용한다.
- `@PathVariable`은 URL 경로의 일부를 변수로 받는다.
- `ModelAndView`는 데이터와 뷰 이름을 하나의 객체로 묶어서 반환한다.
- DTO 클래스를 파라미터로 선언하면 폼 데이터가 자동으로 바인딩된다.

### @GetMapping vs @PostMapping

```java
@GetMapping("/new")          // GET /orders/new → 폼 화면 보여주기
public String orderForm(...) { ... }

@PostMapping                 // POST /orders → 폼 데이터 처리
public String order(...) { ... }
```

### @PathVariable: URL 경로에서 값 추출

```java
@GetMapping("/{menuId}")
public ModelAndView detail(@PathVariable("menuId") long menuId, ModelAndView view) {
    // URL: /menus/2  → menuId = 2
    ...
}
```

- `{menuId}`는 URL 경로의 변수 자리다.
- `@PathVariable("menuId")`로 해당 값을 자바 변수에 받는다.

### ModelAndView: 데이터와 뷰를 한 번에

```java
@GetMapping
public ModelAndView list(ModelAndView view) {
    view.addObject("menus", menuList);  // model.addAttribute와 같은 역할
    view.setViewName("menus/list");     // return "menus/list"과 같은 역할
    return view;
}
```

| 방식 | 데이터 추가 | 뷰 이름 지정 |
|---|---|---|
| `Model` + `String` 반환 | `model.addAttribute(k, v)` | `return "뷰이름"` |
| `ModelAndView` 반환 | `view.addObject(k, v)` | `view.setViewName("뷰이름")` |

### DTO 자동 바인딩

폼의 `name` 속성과 DTO의 필드명(setter)이 같으면 Spring이 자동으로 채워준다.

```java
// HTML 폼
<input type="text" name="customerName">
<input type="number" name="quantity">

// DTO
public class OrderDTO {
    private String customerName;  // name="customerName"과 매칭
    private int quantity;          // name="quantity"와 매칭
    // Lombok @Getter @Setter 필수
}

// 컨트롤러
@PostMapping
public ModelAndView order(OrderDTO order, ModelAndView view) {
    // order.getCustomerName(), order.getQuantity() 이미 채워져 있음
}
```

### JSTL 핵심 태그

```jsp
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- 반복 -->
<c:forEach var="menu" items="${menus}">
    <li>${menu.name} - ${menu.price}원</li>
</c:forEach>

<!-- 조건 분기 -->
<c:choose>
    <c:when test="${empty menus}">
        <p>목록이 없습니다.</p>
    </c:when>
    <c:otherwise>
        <!-- 있을 때 내용 -->
    </c:otherwise>
</c:choose>

<!-- 삼항 연산자 (selected 처리 등) -->
${item.id == selectedId ? 'selected' : ''}
```

### 폼에서 컨텍스트 경로 처리

```jsp
<!-- 하드코딩은 배포 환경이 바뀌면 깨진다 -->
<form action="/orders" method="post">

<!-- contextPath를 동적으로 참조하는 올바른 방식 -->
<form action="${pageContext.request.contextPath}/orders" method="post">
```

### 복습 질문

- `@GetMapping`과 `@PostMapping`의 차이는 무엇인가?
- `@PathVariable`과 `@RequestParam`은 각각 URL의 어느 부분을 읽는가?
- `ModelAndView`를 쓰는 이유는 무엇인가?
- DTO 자동 바인딩이 동작하려면 DTO에 무엇이 있어야 하는가?
- JSTL을 쓰려면 JSP 상단에 무엇을 추가해야 하는가?

---

## 3. 오늘 내용 한 장 요약

| 주제 | 한 줄 정리 |
|---|---|
| DispatcherServlet | 모든 웹 요청을 받아 적절한 컨트롤러로 위임하는 Front Controller |
| ViewResolver | 컨트롤러가 반환한 뷰 이름을 실제 JSP 파일 경로로 변환 |
| `@Controller` | 이 클래스가 요청을 처리하는 컨트롤러임을 선언 |
| `@RequestMapping` | URL과 컨트롤러(메서드)를 연결 |
| `Model` | 컨트롤러 → JSP로 데이터를 전달하는 컨테이너 |
| `ModelAndView` | 데이터와 뷰 이름을 하나의 객체로 묶어서 반환 |
| `@PathVariable` | URL 경로 `/menus/{id}`에서 `{id}` 값을 메서드 파라미터로 추출 |
| `@PostMapping` | HTML form의 `method="post"` 요청을 처리 |
| DTO 바인딩 | form의 `name`과 DTO 필드명이 같으면 Spring이 자동으로 값을 채움 |
| JSTL | JSP에서 반복/조건 등을 태그로 처리 (`c:forEach`, `c:choose`) |
| `contextPath` | `${pageContext.request.contextPath}`로 배포 경로 독립적인 링크 생성 |

---

## 4. 총정리 예제 실행 안내

새로 추가한 예제 위치:

```
step06-07-review/
```

주요 파일:

```
step06-07-review/src/main/webapp/WEB-INF/web.xml
step06-07-review/src/main/webapp/WEB-INF/spring/dispatcher-context.xml
step06-07-review/src/main/java/com/spring/review/controller/HomeController.java
step06-07-review/src/main/java/com/spring/review/controller/ProductController.java
step06-07-review/src/main/java/com/spring/review/controller/CartController.java
step06-07-review/src/main/java/com/spring/review/dto/ProductDTO.java
step06-07-review/src/main/java/com/spring/review/dto/CartDTO.java
```

예제에서 확인할 수 있는 내용:

- DispatcherServlet 등록과 ViewResolver 설정
- `@Controller`, `@RequestMapping`, `@GetMapping`
- `Model`에 데이터 담아 JSP에서 EL로 출력
- `@RequestParam`과 파라미터 직접 바인딩 비교
- `ModelAndView` 사용법
- `@PathVariable`로 URL 경로 값 추출
- `@PostMapping`으로 폼 제출 처리
- DTO 자동 바인딩
- JSTL `c:forEach`, `c:choose`, 삼항식

실행 흐름:

1. 서버 시작 시 `dispatcher-context.xml`을 읽고 컨트롤러 Bean이 생성된다.
2. `http://localhost:8080/step06-07-review/`에 접속하면 `HomeController`가 홈 화면을 반환한다.
3. 상품 목록 `/products`에서 JSTL로 목록이 출력되고, 키워드 검색이 가능하다.
4. 상품명 클릭 시 `/products/{id}`로 이동해 `@PathVariable`이 동작한다.
5. "장바구니 담기" 버튼을 누르면 `/cart/new?productId=...`로 이동해 GET 폼 화면이 표시된다.
6. 폼 제출 시 POST `/cart`로 요청이 가고, DTO가 자동 바인딩되어 결과 화면에 표시된다.

---

## 5. 스스로 점검하기

- `web.xml`만 보고 DispatcherServlet이 어떤 URL을 처리하는지 설명할 수 있는가?
- `dispatcher-context.xml`에서 `<mvc:annotation-driven/>`이 없으면 어떤 일이 생기는가?
- 뷰 이름 `"products/list"`가 실제 JSP 경로로 어떻게 변환되는가?
- URL `/products/3`에서 `3`을 컨트롤러 메서드 파라미터로 받으려면 어떻게 작성하는가?
- 폼의 `<input name="quantity">`가 `OrderDTO`의 `int quantity` 필드에 바인딩되려면 무엇이 필요한가?
- `${pageContext.request.contextPath}`를 쓰는 이유를 설명하라.
