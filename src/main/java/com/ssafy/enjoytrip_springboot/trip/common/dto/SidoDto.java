package com.ssafy.enjoytrip_springboot.trip.common.dto;

public class SidoDto {

	private int sidoCode;
	private String sidoName;

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	@Override
	public String toString() {
		return "SidoDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}

	public static RegionResponse parseToResponse (SidoDto sido){
		return RegionResponse.builder()
				.code(sido.getSidoCode())
				.name(sido.getSidoName())
				.build();
	}

}
