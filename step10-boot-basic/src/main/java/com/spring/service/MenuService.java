package com.spring.service;

import com.spring.dto.MenuDTO;
import com.spring.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /** 전체 목록 */
    public List<MenuDTO> findAll() {
        return menuRepository.findAll();
    }

    /** 판매 가능 메뉴만 */
    public List<MenuDTO> findAvailable() {
        return menuRepository.findByAvailable();
    }

    /** 카테고리별 조회 */
    public List<MenuDTO> findByCategory(String category) {
        return menuRepository.findByCategory(category);
    }

    /** 이름 검색 */
    public List<MenuDTO> search(String keyword) {
        return menuRepository.findByNameContaining(keyword);
    }

    /** 단건 조회 */
    public Optional<MenuDTO> findById(Long id) {
        return menuRepository.findById(id);
    }

    /** 등록 */
    public MenuDTO save(MenuDTO menuDTO) {
        return menuRepository.save(menuDTO);
    }

    /** 수정 */
    public MenuDTO update(Long id, MenuDTO form) {
        MenuDTO item = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다. id=" + id));
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setCategory(form.getCategory());
        item.setDescription(form.getDescription());
        item.setAvailable(form.isAvailable());
        return menuRepository.save(item);
    }

    /** 삭제 */
    public void delete(Long id) {
        menuRepository.deleteById(id);
    }

}
