let stateSelect = document.querySelector("#state");
let citySelect = document.querySelector("#city");
let typeSelect = document.querySelector("#type");

var markers = [];


// 마커를 생성하고 지도위에 표시하는 함수입니다
function addMarker(position, contents) {
  let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
  let imageSize = new kakao.maps.Size(24, 35);
  let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

  // 마커를 생성합니다
  let marker = new kakao.maps.Marker({
    map: map, // 마커를 표시할 지도
    position: position,
    // title : localTrip["title"],
    image: markerImage // 마커 이미지
  });

  // 인포윈도우 내용 (contents)을 설정합니다
  var iwContent = '<div style="padding:5px; width: 500px; height: 200px;">' + contents + '</div>';

  // 인포윈도우를 생성합니다
  var infowindow = new kakao.maps.InfoWindow({
    content: iwContent,
    removable: true // removeable 속성을 true로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
  });

  // 마커에 클릭이벤트를 등록합니다
  kakao.maps.event.addListener(marker, 'click', function () {
    // 마커 위에 인포윈도우를 표시합니다
    infowindow.open(map, marker);
  });

  // 마커가 지도 위에 표시되도록 설정합니다
  marker.setMap(map);

  // 생성된 마커를 배열에 추가합니다
  markers.push(marker);
}

// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

// "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
function showMarkers() {
  setMarkers(map)
}

// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
function hideMarkers() {
  setMarkers(null);
  markers = [];
}

function createInfoWindows(localTrip) {
  let ret =
      `<h2>${localTrip["title"]}</h2>
          <img src="${localTrip["firstimage"]}" width="100px" height="100px" alt="이미지가 없습니다!"></img>
          <div>전화번호 : ${localTrip["tel"]}</div>
          <div>주소 : ${localTrip["addr1"]} &nbsp; ${localTrip["addr2"]} </div>`
  return ret;
}

async function searchLocalTrip() {
  let curState = stateSelect.options[stateSelect.selectedIndex].value;
  let curCity = curState + citySelect.options[citySelect.selectedIndex].value;
  let curType = typeSelect.options[typeSelect.selectedIndex].value;
  // console.log(curState, curCity, curType);
  // console.log(localStorage.getItem(curState))
  // console.log(localStorage.getItem(curCity == curState ? "" : curCity))
  // console.log(curType);

  curCity = (curState === curCity) ? "" : localStorage.getItem(curCity) === null ? "":localStorage.getItem(curCity);
  curState = localStorage.getItem(curState) == null ? "" : localStorage.getItem(curState);
  curType = curType == null ? "" : curType;

  let localTripData = await getLocalTripData(curState, curCity, curType);
  console.log(localTripData);

  hideMarkers();

  // localTripData가 정상적으로 반환되었는지 확인
  if (localTripData["item"].length > 0) {
    let localTripDatas = localTripData["item"];

    // 지도 중심을 이동 시킵니다
    let bounds = new kakao.maps.LatLngBounds();

    for (const localTrip of localTripDatas) {
      // console.log(localTrip);
      // 마커 이미지의 이미지 크기 입니다
      addMarker(
          new kakao.maps.LatLng(localTrip["mapy"], localTrip["mapx"]),
          createInfoWindows(localTrip)
      );
      bounds.extend(new kakao.maps.LatLng(localTrip["mapy"], localTrip["mapx"]));
    }
    map.setBounds(bounds);
    showMarkers();
  } else {
    alert("해당 위치의 관광지가 없습니다!");
    console.error("데이터가 올바르게 반환되지 않았습니다.");
  }
}

stateSelect.addEventListener('change', function(event) {
  let selectedOption = event.target.options[event.target.selectedIndex];
  // console.log(selectedOption.value);
  setCityOption(selectedOption.value);
  searchLocalTrip();
});

citySelect.addEventListener('change', function(event) {
  let selectedOption = event.target.options[event.target.selectedIndex];
  let curState = stateSelect.options[stateSelect.selectedIndex].value;
  let curCity = curState + citySelect.options[citySelect.selectedIndex].value;
  let curType = typeSelect.options[typeSelect.selectedIndex].value;
  searchLocalTrip();
});

typeSelect.addEventListener('change', function(event) {
  let selectedOption = event.target.options[event.target.selectedIndex];
  // setCityOption(selectedOption.value);
  let curState = stateSelect.options[stateSelect.selectedIndex].value;
  let curCity = curState + citySelect.options[citySelect.selectedIndex].value;
  let curType = typeSelect.options[typeSelect.selectedIndex].value;
  searchLocalTrip();
});

function getURL(baseUrl, queryParams) {
  let queryString = Object.keys(queryParams)
      .map((key) => key + "=" + queryParams[key])
      .join("&");

  let finalUrl = `${baseUrl}?${queryString}`;
  return finalUrl;
}

async function getRequest(finalUrl) {
  try {
    const response = await fetch(finalUrl); // 비동기로 동작하도록 수정
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json(); // 비동기로 동작하도록 수정
    return data["response"]["body"]["items"];
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    return null;
  }
}

function getStateData() {
  let service = "getStateList"
  let baseUrl = "http://localhost:8080/tripapi";


  let queryParams = {
    numOfRows: "100",
    pageNo: "1",
    action : service
  };

  let finalUrl = getURL(baseUrl, queryParams);
  return getRequest(finalUrl);
}

function getCityData(city) {
  let service = "getCityList"
  let baseUrl = "http://localhost:8080/tripapi";

  let queryParams = {
    numOfRows: "100",
    pageNo: "1",
    areaCode:  localStorage.getItem(city),
    action : service
  };

  let finalUrl = getURL(baseUrl, queryParams);
  return getRequest(finalUrl);
}

function getLocalTripData(state,city, type) {
  let service = "getTripList"
  let baseUrl = "http://localhost:8080/tripapi";

  let queryParams = {
    numOfRows: "10",
    pageNo: "1",
    areaCode:  state,
    sigunguCode : city,
    contentTypeId : type,
    action : service
  };

  let finalUrl = getURL(baseUrl, queryParams);
  return getRequest(finalUrl);
}

function appendOption(state, value, selectElem) {
  const optionElement = document.createElement('option');
  optionElement.value = value;
  optionElement.textContent = state;
  selectElem.appendChild(optionElement);
}

async function setStateOption(){
  const local = await getStateData();
  // console.log(local);

  for (const loc of local["item"]) {
    localStorage.setItem(loc.name, loc.code);
  }

  const states = local["item"].map(item => item.name);
  for (const state of states) {
    appendOption(state,state, stateSelect );
  }
}

async function setCityOption(state) {
  while(citySelect.firstChild){
    citySelect.removeChild(citySelect.firstChild);
  }
  appendOption("도시 전체", "" ,citySelect);

  const local = await getCityData(state);
  // console.log(local);

  if (!(local && local["item"])){
    alert("제대로 된 값을 설정해주세요!");
    return;
  }

  for (const loc of local["item"]) {
    localStorage.setItem(stateSelect.options[stateSelect.selectedIndex].value+ loc.name, loc.code);
  }

  const cities = local["item"].map(item => item.name);
  for (const city of cities) {
    appendOption(city, city ,citySelect);
  }
}

setStateOption();
searchLocalTrip();