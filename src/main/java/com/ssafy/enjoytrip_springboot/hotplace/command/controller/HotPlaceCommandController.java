package com.ssafy.enjoytrip_springboot.hotplace.command.controller;

import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import com.ssafy.enjoytrip_springboot.common.s3.Dto.UploadFile;
import com.ssafy.enjoytrip_springboot.common.s3.service.FileUpload;
import com.ssafy.enjoytrip_springboot.hotplace.command.dto.request.HotPlaceModifyRequest;
import com.ssafy.enjoytrip_springboot.hotplace.command.dto.request.HotPlaceRegistRequest;
import com.ssafy.enjoytrip_springboot.hotplace.command.service.HotPlaceCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hotplace")
@RequiredArgsConstructor
public class HotPlaceCommandController {


    private final HotPlaceCommandService hotPlaceCommandService;

    @PostMapping("/")
    public ResponseEntity<?> registerHotPlace(@RequestBody HotPlaceRegistRequest request) {

        // Assuming you have a method in your service to handle the registration
        Long id = hotPlaceCommandService.registerHotPlace(request.parse());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", id);

        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(resultMap)
                .msg("HotPlace 등록이 정상적으로 완료되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotPlace(@PathVariable Long id) throws Exception {
        hotPlaceCommandService.deleteHotPlace(id);
        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .msg("정상적으로 삭제되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }


    @PutMapping("/modify")
    public ResponseEntity<?> modifyHotPlace(@RequestBody HotPlaceModifyRequest request) throws Exception {
        Long id = hotPlaceCommandService.modifyHotPlace(request.parse());

        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(id)
                .msg("HotPlace 수정이 정상적으로 완료되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }
}
