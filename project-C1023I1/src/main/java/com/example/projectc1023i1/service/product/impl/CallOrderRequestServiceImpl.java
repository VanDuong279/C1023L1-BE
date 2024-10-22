package com.example.projectc1023i1.service.product.impl;

import com.example.projectc1023i1.model.product.CallOrderRequest;
import com.example.projectc1023i1.repository.product.CallOrderRequestRepository;
import com.example.projectc1023i1.service.product.CallOrderRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallOrderRequestServiceImpl implements CallOrderRequestService {
    @Autowired
    private CallOrderRequestRepository callOrderRequestRepository;
    @Override
    public void save(CallOrderRequest callOrderRequest) {
        callOrderRequestRepository.save(callOrderRequest);
    }
}
