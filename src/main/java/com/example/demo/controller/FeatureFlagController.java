package com.example.demo.controller;
import io.split.client.SplitClient;
import io.split.client.api.SplitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureFlagController {

    @Autowired
    private SplitClient splitClient;

    @GetMapping("/feature")
    public String getFeature() {
        SplitResult result = splitClient.getTreatmentWithConfig("user-key", "your_feature_flag_name");
        if ("on".equals(result.treatment())) {
            return "Feature is ON";
        } else {
            return "Feature is OFF";
        }
    }
}