package com.spring.problem04.service;

import com.spring.problem04.entity.Product;
import com.spring.problem04.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// TODO 7: @Service 선언
public class ProductService {

    // TODO 8: ProductRepository 필드 선언 + 생성자 주입

    // TODO 9: findAll() — 전체 목록 반환
    public List<Product> findAll() {
        return null;
    }

    // TODO 10: findById(Long id) — Optional<Product> 반환
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    // TODO 11: save(Product product) — 저장 후 반환
    public Product save(Product product) {
        return null;
    }

    // TODO 12: update(Long id, Product form) — name, price, description 수정 후 저장·반환
    //          findById로 찾고, 없으면 IllegalArgumentException 발생
    public Product update(Long id, Product form) {
        return null;
    }

    // TODO 13: delete(Long id) — 삭제
    public void delete(Long id) {

    }
}
