package com.spring.controller;

import com.spring.dto.CarDTO;
import com.spring.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/cars")
@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ModelAndView list(ModelAndView view) {
        view.addObject("cars", carService.findAll());
        view.setViewName("list");
        return view;
    }

    @GetMapping("/new")
    public ModelAndView newCar(ModelAndView view) {
        view.addObject("car", new CarDTO());
        view.setViewName("form");
        return view;
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("car") CarDTO car,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes, Model model){
        try{
            if(bindingResult.hasErrors()){
                throw new Exception("입력값이 잘못되었습니다. 다시 확인하여 입력해 주세요.");
            }
             carService.save(car);
        }catch (Exception e){
            e.printStackTrace();
            return "form";
        }

        return "redirect:/cars";
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Integer id, ModelAndView view) {
        view.addObject("car", carService.findById(id));
        view.setViewName("detail");
        return view;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id,
                         RedirectAttributes redirectAttributes) {
        int result = carService.deleteById(id);
        if (result == 1) {
            redirectAttributes.addFlashAttribute(
                    "successMessage", "차량이 성공적으로 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "차량 삭제에 실패했습니다.");
        }
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, ModelAndView view) {
        view.addObject("car", carService.findById(id));
        view.setViewName("edit");
        return view;
    }
}








