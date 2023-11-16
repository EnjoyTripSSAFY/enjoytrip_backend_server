package com.ssafy.enjoytrip_springboot.trip.model.dao;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

import com.ssafy.enjoytrip_springboot.trip.common.dto.AttractionInfoDto;
import com.ssafy.enjoytrip_springboot.trip.common.dto.AttractionRequest;
import com.ssafy.enjoytrip_springboot.trip.common.dto.GugunDto;
import com.ssafy.enjoytrip_springboot.trip.common.dto.SidoDto;
import com.ssafy.enjoytrip_springboot.trip.query.mapper.AttractionDao;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class AttractionDaoTest {

    @Autowired
    private AttractionDao dao;

    @Test
    @DisplayName("관광지 목록(전체): 전국 모든 타입")
    void All_attractionList(){
        AttractionRequest dto = AttractionRequest.builder().build();

        List<AttractionInfoDto> result = dao.attractionList(dto);

        assertThat(result.size()).isEqualTo(20);
    }

    @Test
    @DisplayName("관광지 목록(전체): 서울 모든 타입")
    void state_attractionList(){
        AttractionRequest dto = AttractionRequest.builder()
                .areaCode(1)
                .build();


        List<AttractionInfoDto> result = dao.attractionList(dto);

        Set<Integer> collect = result.stream()
                .filter(a -> a.getSidoCode() == 1)
                .map(AttractionInfoDto::getSidoCode)
                .collect(Collectors.toSet());

        assertThat(collect).hasSize(1);

    }

    @Test
    @DisplayName("관광지 목록(전체): 전국 음식점")
    void type_attractionList(){
        AttractionRequest dto = AttractionRequest.builder()
                .contentTypeId(39)
                .pageNo(601)
                .build();

        List<AttractionInfoDto> result = dao.attractionList(dto);

        Set<Integer> collect = result.stream()
                .filter(a -> a.getContentTypeId() == 39)
                .map(AttractionInfoDto::getContentTypeId)
                .collect(Collectors.toSet());

        assertThat(collect).hasSize(1);

//        assertThat(result.size()).isEqualTo(12017);
    }

    @Test
    @DisplayName("관광지 목록(전체): 서울 음식점")
    void state_type_attractionList(){
        AttractionRequest dto = AttractionRequest.builder()
                .contentTypeId(39)
                .areaCode(1)
                .build();


        List<AttractionInfoDto> result = dao.attractionList(dto);
        // 총 2074 건

        Set<Tuple> collect = result.stream()
                .filter(a -> a.getContentTypeId() == 39 && a.getSidoCode() == 1)
                .map(a -> new Tuple(a.getContentTypeId(), a.getSidoCode()))
                .collect(Collectors.toSet());

        assertThat(collect).hasSize(1);

    }

    @Test
    @DisplayName("관광지 이름으로 검색: 타이틀 : 공백 , 시도 : 전국")
    void All_titleIsBlack_attractionList_2(){
        AttractionRequest request = AttractionRequest.builder().word("").build();

        List<AttractionInfoDto> result = dao.searchByTitle(request);

        result.forEach(c -> c.toString());

        assertThat(result.size()).isEqualTo(20);

    }

    @Test
    @DisplayName("관광지 이름으로 검색: 타이틀 : null , 시도 : 전국")
    void All_titleIsNull_attractionList_2(){
        AttractionRequest request = AttractionRequest.builder().word(null).build();


        List<AttractionInfoDto> result = dao.searchByTitle(request);

        assertThat(result.size()).isEqualTo(20);
    }

    @Test
    @DisplayName("관광지 이름으로 검색: 타이틀 : 공백 , 시도 : 서울")
    void state_titleIsBlack_attractionList_2(){
        AttractionRequest request = AttractionRequest.builder().word("").areaCode(1).build();


        List<AttractionInfoDto> result = dao.searchByTitle(request);

        long actual = result.stream()
                .filter(c -> c.getAddr1().contains("서울"))
                .count();

        assertThat(actual).isEqualTo(20);
    }

    @Test
    @DisplayName("관광지 이름으로 검색: 타이틀 : null , 시도 : 서울")
    void state_titleIsNull_attractionList_2(){
        AttractionRequest request = AttractionRequest.builder().word(null).areaCode(1).build();

        List<AttractionInfoDto> result = dao.searchByTitle(request);

        long actual = result.stream()
                .filter(c -> c.getAddr1().contains("서울"))
                .count();

        assertThat(actual).isEqualTo(20);

    }

    @Test
    @DisplayName("관광지 이름으로 검색: 타이틀 : 바다 포함 , 시도 : 전국")
    void title_StateAll_attractionList(){
        AttractionRequest request = AttractionRequest.builder().word("바다").build();

        List<AttractionInfoDto> result = dao.searchByTitle(request);

        result.forEach(c -> System.out.println(c.toString()));

        long actual = result.stream()
                .filter(c -> c.getTitle().contains("바다"))
                .count();

        assertThat(actual).isEqualTo(20);
    }

    @Test
    @DisplayName("관광지 이름으로 검색: 타이틀 : 바다 포함 , 시도 : 부산")
    void title_State_attractionList(){
        AttractionRequest request = AttractionRequest.builder().word("바다").areaCode(6).build();

        List<AttractionInfoDto> result = dao.searchByTitle(request);

        long actual = result.stream()
                .filter(c -> c.getAddr1().contains("부산"))
                .filter(c -> c.getTitle().contains("바다"))
                .count();

        assertThat(actual).isEqualTo(5);

        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("광역 단위")
    void get_state(){
        List<SidoDto> sidoDtos = dao.stateList();
        assertThat(sidoDtos).hasSize(17);
    }

    @Test
    @DisplayName("시도 단위")
    void get_city(){
        List<GugunDto> gugunDtos = dao.cityList(1);
        assertThat(gugunDtos).hasSize(25);
    }
}