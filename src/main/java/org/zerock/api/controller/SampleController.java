package org.zerock.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

    @Tag(name = "Sample GET doA")
    @GetMapping("/doA")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<String>doA(){
        return Arrays.asList("AAA","BBB","CCC");
    }

    @Tag(name = "Sample GET doB")
    @GetMapping("/doB")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<String>doB(){
        return Arrays.asList("AdminAAA","AdminBBB","AdminCCC");
    }

}
