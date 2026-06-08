package com.spring.repository;

import com.spring.dto.MenuDTO;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MenuRepository {

    private final Map<Long, MenuDTO> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    /** 저장 (신규: id 자동 부여, 수정: 덮어쓰기) */
    public MenuDTO save(MenuDTO item) {
        if (item.getId() == null) {
            item.setId(sequence.getAndIncrement());
        }
        store.put(item.getId(), item);
        return item;
    }

    /** 전체 목록 */
    public List<MenuDTO> findAll() {
        return new ArrayList<>(store.values());
    }

    /** 단건 조회 */
    public Optional<MenuDTO> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /** 카테고리 필터 */
    public List<MenuDTO> findByCategory(String category) {
        return store.values().stream()
                .filter(m -> category.equals(m.getCategory()))
                .collect(Collectors.toList());
    }

    /** 판매 가능 메뉴만 */
    public List<MenuDTO> findByAvailable() {
        return store.values().stream()
                .filter(MenuDTO::isAvailable)
                .collect(Collectors.toList());
    }

    /** 이름 검색 */
    public List<MenuDTO> findByNameContaining(String keyword) {
        return store.values().stream()
                .filter(m -> m.getName().contains(keyword))
                .collect(Collectors.toList());
    }

    /** 삭제 */
    public void deleteById(Long id) {
        store.remove(id);
    }
}
