package com.ssafy.enjoytrip_springboot.trip.query.service;

import com.ssafy.enjoytrip_springboot.trip.common.dto.AttractionInfoDto;
import com.ssafy.enjoytrip_springboot.trip.common.dto.AttractionRequest;
import com.ssafy.enjoytrip_springboot.trip.common.dto.GugunDto;
import com.ssafy.enjoytrip_springboot.trip.common.dto.SidoDto;

import java.util.List;

public interface AttractionService {

    List<AttractionInfoDto> attractionList(AttractionRequest request);

    List<AttractionInfoDto> searchByTitle(AttractionRequest request);

    List<SidoDto> stateList();

    List<GugunDto> cityList(int stateCode);
}

