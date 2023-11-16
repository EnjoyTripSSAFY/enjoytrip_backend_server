package com.ssafy.enjoytrip_springboot.trip.query.mapper;


import com.ssafy.enjoytrip_springboot.trip.common.dto.AttractionInfoDto;
import com.ssafy.enjoytrip_springboot.trip.common.dto.AttractionRequest;
import com.ssafy.enjoytrip_springboot.trip.common.dto.GugunDto;
import com.ssafy.enjoytrip_springboot.trip.common.dto.SidoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionDao {

	List<AttractionInfoDto> attractionList(AttractionRequest request);

	List<AttractionInfoDto> searchByTitle(AttractionRequest request);

	List<SidoDto> stateList();

	List<GugunDto> cityList(int stateCode);

}
