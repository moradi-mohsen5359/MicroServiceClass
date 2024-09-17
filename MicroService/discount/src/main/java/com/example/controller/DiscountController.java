package com.example.controller;

import com.example.dto.DiscountResponseDTO;
import com.example.entity.Discount;
import com.example.repository.DiscountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupons")
public class DiscountController {

    private final DiscountRepository discountRepository;

    public DiscountController(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @GetMapping("/{couponName}")
    public DiscountResponseDTO getDiscount(@PathVariable("couponName") String couponName) {
        Discount discount = discountRepository.findByCouponName(couponName);
        DiscountResponseDTO discountResponseDTO = new DiscountResponseDTO();
        BeanUtils.copyProperties(discount, discountResponseDTO);
        return discountResponseDTO;
    }

    @PostMapping
    public void createDiscount(@RequestBody Discount discount) {
        discountRepository.save(discount);
    }
}
