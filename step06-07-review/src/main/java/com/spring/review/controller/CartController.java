package com.spring.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.review.dto.CartDTO;
import com.spring.review.dto.ProductDTO;

/*
 * [복습 포인트] GET vs POST
 * - @GetMapping  → HTML 폼을 사용자에게 보여줄 때 (조회)
 * - @PostMapping → 사용자가 폼을 제출했을 때 (처리)
 *
 * 같은 URL이라도 HTTP 메서드가 다르면 다른 메서드가 처리한다.
 *   GET  /cart → 폼 화면 표시
 *   POST /cart → 폼 데이터 처리 후 결과 표시
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private final List<ProductDTO> productList = List.of(
            new ProductDTO(1L, "에스프레소", "커피", 2500, "진한 에스프레소 샷"),
            new ProductDTO(2L, "아이스 아메리카노", "커피", 3500, "시원한 아이스 아메리카노"),
            new ProductDTO(3L, "카페라떼", "커피", 4000, "부드러운 밀크폼"),
            new ProductDTO(4L, "딸기 에이드", "음료", 4500, "새콤달콤 딸기 에이드"),
            new ProductDTO(5L, "크로플", "디저트", 5000, "바삭한 크로플")
    );

    /*
     * GET /cart/new?productId=2
     * - 장바구니 담기 폼 화면을 반환한다.
     * - productId는 쿼리스트링으로 전달받아 기본 선택 메뉴를 설정한다.
     */
    @GetMapping("/new")
    public ModelAndView cartForm(long productId, ModelAndView view) {
        view.addObject("products", productList);
        view.addObject("selectedProductId", productId);
        view.setViewName("cart/form");
        return view;
    }

    /*
     * POST /cart
     * - 폼 제출 요청을 처리한다.
     *
     * [복습 포인트] DTO 자동 바인딩
     * - CartDTO를 파라미터로 선언하면 Spring이 폼의 name 속성과 DTO 필드를 자동으로 매칭한다.
     * - 조건: DTO에 기본 생성자(@NoArgsConstructor)와 setter(@Setter)가 있어야 한다.
     *   form의 <input name="customerName"> → CartDTO.setCustomerName(값) 자동 호출
     *   form의 <input name="quantity">     → CartDTO.setQuantity(값)      자동 호출
     */
    @PostMapping
    public ModelAndView addToCart(CartDTO cart, ModelAndView view) {
        ProductDTO product = productList.stream()
                .filter(p -> p.getId() == cart.getProductId())
                .findFirst()
                .orElse(new ProductDTO(0L, "알 수 없는 상품", "기타", 0, ""));

        view.addObject("cart", cart);
        view.addObject("product", product);
        view.addObject("totalPrice", product.getPrice() * cart.getQuantity());
        view.setViewName("cart/result");
        return view;
    }
}
