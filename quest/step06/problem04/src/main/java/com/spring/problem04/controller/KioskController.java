package com.spring.problem04.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.problem04.dto.MenuItem;

// TODO 1: 필요한 import를 추가하라.
//   import org.springframework.stereotype.Controller;
//   import org.springframework.ui.Model;
//   import org.springframework.web.bind.annotation.GetMapping;
//   import org.springframework.web.bind.annotation.RequestMapping;
//   import org.springframework.web.bind.annotation.RequestParam;
//   import java.util.List;
//   import com.spring.problem04.dto.MenuItem;

// TODO 2: @Controller 어노테이션을 추가하라.
//         → Spring MVC가 이 클래스를 요청 처리 Handler로 인식한다.

// TODO 3: @RequestMapping("/kiosk")를 클래스 레벨에 추가하라.
//         → 이 클래스의 모든 메서드는 /kiosk 로 시작하는 URL을 처리한다.
@RequestMapping("/kiosk")
@Controller
public class KioskController {

    // TODO 4: GET /kiosk/home 요청을 처리하는 메서드를 작성하라.
    //   - @GetMapping("/home") 추가
    //   - Model 데이터 추가 없음
    //   - return "kiosk/home"  → /WEB-INF/views/kiosk/home.jsp
	@GetMapping("/home")
	public String home() {
		return "kiosk/home";
	}

    // TODO 5: GET /kiosk/menu 요청을 처리하는 메서드를 작성하라.
    //   - @GetMapping("/menu") 추가
    //   - Model 파라미터 추가
    //   - menuList 생성: MenuItem 3개
    //       new MenuItem("불고기버거", 8500)
    //       new MenuItem("치킨버거", 9000)
    //       new MenuItem("새우버거", 8000)
    //   - model.addAttribute("menuList", menuList)
    //   - return "kiosk/menu"  → /WEB-INF/views/kiosk/menu.jsp
	@GetMapping("/menu")
	public ModelAndView menu(ModelAndView view) {
		List<MenuItem> list = List.of(new MenuItem("불고기버거", 8500),
				new MenuItem("치킨버거", 9000),
				new MenuItem("새우버거", 8000));
		view.addObject("menuList", list);
		view.setViewName("kiosk/menu");
		return view;
	}

    // TODO 6: GET /kiosk/order?menu=...&qty=... 요청을 처리하는 메서드를 작성하라.
    //   - @GetMapping("/order") 추가
    //   - @RequestParam String menu 파라미터 추가
    //   - @RequestParam(defaultValue = "1") int qty 파라미터 추가
    //   - Model 파라미터 추가
    //   - 메뉴별 가격을 조회하는 Map 또는 switch를 사용한다.
    //       Map<String, Integer> prices = new HashMap<>();
    //       prices.put("불고기버거", 8500);
    //       prices.put("치킨버거", 9000);
    //       prices.put("새우버거", 8000);
    //   - int unitPrice = prices.getOrDefault(menu, 0);
    //   - model.addAttribute("orderMenu", menu)
    //   - model.addAttribute("orderQty", qty)
    //   - model.addAttribute("totalPrice", unitPrice * qty)
    //   - return "kiosk/order"  → /WEB-INF/views/kiosk/order.jsp
	@GetMapping("/order")
	public ModelAndView order(ModelAndView view, String menu, int qty) {
		Map<String, Integer> prices = new HashMap<>();
		prices.put("불고기버거", 8500);
		prices.put("치킨버거", 9000);
		prices.put("새우버거", 8000);
		
		view.addObject("orderMenu",menu);
		view.addObject("orderQty", qty);
		int unitPrice = prices.getOrDefault(menu, 0);
		view.addObject("totalPrice", unitPrice  * qty);
		
		view.setViewName("kiosk/order");
		return view;
	}
	
	@GetMapping("/register/view")
	public String regView() {
		return "kiosk/form";
	}
	
	@PostMapping("/register")
	public void register(MenuItem item) {
		System.out.println(item);
	}
}






