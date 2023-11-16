package com.ssafy.enjoytrip_springboot.trip.query.service;

import com.ssafy.enjoytrip_springboot.trip.common.dto.AttractionInfoDto;
import com.ssafy.enjoytrip_springboot.trip.common.dto.AttractionRequest;
import com.ssafy.enjoytrip_springboot.trip.common.dto.GugunDto;
import com.ssafy.enjoytrip_springboot.trip.common.dto.SidoDto;

import com.ssafy.enjoytrip_springboot.trip.query.mapper.AttractionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionDao dao;

    @Override
    public List<AttractionInfoDto> attractionList(AttractionRequest request) {
        return dao.attractionList(request);
    }

    @Override
    public List<AttractionInfoDto> searchByTitle(AttractionRequest request) {
        return dao.searchByTitle(request);
    }

    @Override
    public List<SidoDto> stateList() {
        return dao.stateList();
    }

    @Override
    public List<GugunDto> cityList(int stateCode) {
        return dao.cityList(stateCode);
    }
}

