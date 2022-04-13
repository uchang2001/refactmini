package com.one.miniproject.controller;


import com.one.miniproject.dto.GoodDto;
import com.one.miniproject.dto.ResponseDto;
import com.one.miniproject.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GoodController {

    private final GoodService goodService;

    // 좋아요 기능
    @PostMapping("/api/posts/like")
    public ResponseDto goodCheck(@RequestBody GoodDto goodDto){


        return goodService.goodCheck(goodDto);
    }
}