package com.one.miniproject.service;

import com.one.miniproject.dto.GoodDto;
import com.one.miniproject.dto.ResponseDto;
import com.one.miniproject.model.Good;
import com.one.miniproject.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class GoodService {


    private final GoodRepository goodRepository;

    // 좋아요 기능
    @Transactional
    public ResponseDto goodCheck(GoodDto goodDto) {

        ResponseDto responseDto = new ResponseDto();
        Long postid = goodDto.getPostid();
        String username = goodDto.getUsername();

        Good good = goodRepository.findByPostidAndAndUsername(postid,username).orElse(null);

        try {
            if(good == null)
            {
                Good savegood = new Good(postid,username);
                goodRepository.save(savegood);
            }
            else
            {
                goodRepository.deleteById(good.getGoodid());
            }

            responseDto.setResult(true);
        } catch (Exception e){
            responseDto.setResult(false);
            responseDto.setErr_msg("좋아요 기능 오류");
        }

        return responseDto;
    }
}