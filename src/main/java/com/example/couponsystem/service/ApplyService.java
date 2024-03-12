package com.example.couponsystem.service;

import com.example.couponsystem.producer.CouponCreateProducer;
import com.example.couponsystem.repository.CouponCountRedisRepository;
import com.example.couponsystem.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRedisRepository couponCountRedisRepository;
    private final CouponCreateProducer couponCreateProducer;

    public void apply(Long userId) {
        Long count = couponCountRedisRepository.increment();

        if (count > 100) {
            return ;
        }

        couponCreateProducer.create(userId);

    }

}
