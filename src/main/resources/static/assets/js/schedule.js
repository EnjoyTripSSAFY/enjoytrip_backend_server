var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
var moveLine; // 선이 그려지고 있을때 마우스 움직임에 따라 그려질 선 객체 입니다
var clickLine; // 마우스로 클릭한 좌표로 그려질 선 객체입니다
var distanceOverlay; // 선의 거리정보를 표시할 커스텀오버레이 입니다
var dots = {}; // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.

/**
 * 검색 이후 담은 마커 표시할 오버레이 관련
 */
var markers = [];

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

const serviceKey =
  "DK%2FFytTR2Wt3CAaU4fbr8Fkm3c1VpiCQRVow9RcBm8z85m3eEbKdKNBzpDOiaGNJDD3bGBSH17JZ9YftGLZNDA%3D%3D";

let areaUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  serviceKey +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

document.getElementById("submitBtn").addEventListener("click", (event) => {
  searchByKeyword();
});

fetch(areaUrl)
  .then((response) => response.json())
  .then((data) => makeCity(data));

//서울, 부산, 대구.... 넣기
function makeCity(data) {
  let sel = document.getElementById("selectArea");
  let areas = data.response.body.items;
  // console.log(areas);
  areas.item.forEach(function (area, i) {
    // console.log(area);
    let opt = document.createElement("option");
    opt.setAttribute("value", area.code);
    opt.appendChild(document.createTextNode(area.name));

    sel.appendChild(opt);
  });
}

const areaList = [];
//서울.. 에 따른 강동구, 종로구.. 넣기
document.getElementById("selectArea").addEventListener("click", function () {
  let areaCode = document.getElementById("selectArea").value;
  //   console.log(areaCode);
  if (areaCode !== "0") {
    areaList.length = 0;
    let searchUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=100&MobileOS=ETC&MobileApp=AppTest&areaCode=${areaCode}&_type=json`;

    fetch(searchUrl)
      .then((response) => response.json())
      .then((data) => makeAreaDistrict(data, areaCode));
  }
});

function makeAreaDistrict(data, areaCode) {
  let sel = document.getElementById("selectDistrict");
  sel.innerHTML = "";

  let opt = document.createElement("option");
  opt.setAttribute("value", 0);
  opt.appendChild(document.createTextNode("검색 할 세부지역 선택"));
  areaList.push(areaCode);

  sel.appendChild(opt);

  let areas = data.response.body.items.item;
  areas.forEach(function (area, i) {
    // console.log(area);
    opt = document.createElement("option");
    opt.setAttribute("value", area.code);
    opt.appendChild(document.createTextNode(area.name));
    areaList.push(area.name);

    sel.appendChild(opt);
  });
}

// 키워드 검색을 요청하는 함수입니다
function searchByKeyword() {
  var keyword = document.getElementById("keywordInput").value;
  let areaCode = document.getElementById("selectArea").value;
  let districtCode = document.getElementById("selectDistrict").value;

  if (!areaCode || !districtCode) {
    alert("지역을 다시 선택해주세요.");
    // return false;
  }
  let url = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=1000&MobileOS=ETC&MobileApp=df&areaCode=${areaCode}&sigunguCode=${districtCode}&serviceKey=${serviceKey}&_type=json`;

  // console.log(districtCode);

  if (!keyword) {
    alert("키워드를 입력해주세요!");
    // return false;
  } else {
    deleteSearchList();
    // Fetch API를 사용하여 데이터를 가져옵니다.
    fetch(url)
      .then((response) => response.json())
      .then((data) => filteringKeyword(data, keyword));
  }
}

function filteringKeyword(data, keyword) {
  var resultList = [];
  var element = data.response.body.items.item;
  // console.log(typeof(data.response.body.items.item));
  for (let index = 0; index < element.length; index++) {
    // element = data.response.body.items.item;
    if (element[index].title.indexOf(keyword) != -1) {
      // alert("해당 키워드가 있습니다.");
      makeSearchList(element);
      // resultList.push(element[index]);
      console.log(element[index].title);
      // return index;;
    }
  }
}

function makeSearchList(data) {
  var array = data;
  let searchListElement = document.getElementById("search-List");

  array.forEach((element) => {
    let title = element.title;
    let image = element.firstimage;
    let address = element.addr1;
    let detailAddress = element.addr2;
    let mapx = element.mapx;
    let mapy = element.mapy;

    let imgTdElement = document.createElement("td");
    let imgElement = document.createElement("img");
    imgElement.setAttribute("src", image);
    imgElement.setAttribute("style", "width: 300px; height: 200px;");
    imgElement.setAttribute("alt", image);
    imgTdElement.appendChild(imgElement);
    imgTdElement.setAttribute("class", "col-4");

    let titleElement = document.createElement("td");
    titleElement.appendChild(document.createTextNode(title));
    titleElement.setAttribute("class", "col-2");

    let addressElement = document.createElement("td");
    addressElement.appendChild(document.createTextNode(address + detailAddress));
    addressElement.setAttribute("class", "col-2");

    let xElement = document.createElement("td");
    xElement.appendChild(document.createTextNode(mapx));
    xElement.setAttribute("class", "col-2");

    let yElement = document.createElement("td");
    yElement.appendChild(document.createTextNode(mapy));
    yElement.setAttribute("class", "col-2");

    let tr = document.createElement("tr");

    tr.appendChild(imgTdElement);
    tr.appendChild(titleElement);
    tr.appendChild(addressElement);
    tr.appendChild(xElement);
    tr.appendChild(yElement);
    // article.addEventListener("click", (e) => {});
    searchListElement.appendChild(tr);
  });
}

function deleteSearchList() {
  document.getElementById("searchList").textContent = "";
}

/**
 * 지도에 선 그리기 관련
 */
// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 선 그리기가 시작됩니다 그려진 선이 있으면 지우고 다시 그립니다
kakao.maps.event.addListener(map, "click", function (mouseEvent) {
  // 마우스로 클릭한 위치입니다
  var clickPosition = mouseEvent.latLng;

  // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
  if (!drawingFlag) {
    // 상태를 true로, 선이 그리고있는 상태로 변경합니다
    drawingFlag = true;

    // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
    deleteClickLine();

    // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
    deleteDistnce();

    // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
    deleteCircleDot();

    // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
    clickLine = new kakao.maps.Polyline({
      map: map, // 선을 표시할 지도입니다
      path: [clickPosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
      strokeWeight: 3, // 선의 두께입니다
      strokeColor: "#db4040", // 선의 색깔입니다
      strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
      strokeStyle: "solid", // 선의 스타일입니다
    });

    // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
    moveLine = new kakao.maps.Polyline({
      strokeWeight: 3, // 선의 두께입니다
      strokeColor: "#db4040", // 선의 색깔입니다
      strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
      strokeStyle: "solid", // 선의 스타일입니다
    });

    // 클릭한 지점에 대한 정보를 지도에 표시합니다
    displayCircleDot(clickPosition, 0);
  } else {
    // 선이 그려지고 있는 상태이면

    // 그려지고 있는 선의 좌표 배열을 얻어옵니다
    var path = clickLine.getPath();

    // 좌표 배열에 클릭한 위치를 추가합니다
    path.push(clickPosition);

    // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
    clickLine.setPath(path);

    var distance = Math.round(clickLine.getLength());
    displayCircleDot(clickPosition, distance);
  }
});

// 지도에 마우스무브 이벤트를 등록합니다
// 선을 그리고있는 상태에서 마우스무브 이벤트가 발생하면 그려질 선의 위치를 동적으로 보여주도록 합니다
kakao.maps.event.addListener(map, "mousemove", function (mouseEvent) {
  // 지도 마우스무브 이벤트가 발생했는데 선을 그리고있는 상태이면
  if (drawingFlag) {
    // 마우스 커서의 현재 위치를 얻어옵니다
    var mousePosition = mouseEvent.latLng;

    // 마우스 클릭으로 그려진 선의 좌표 배열을 얻어옵니다
    var path = clickLine.getPath();

    // 마우스 클릭으로 그려진 마지막 좌표와 마우스 커서 위치의 좌표로 선을 표시합니다
    var movepath = [path[path.length - 1], mousePosition];
    moveLine.setPath(movepath);
    moveLine.setMap(map);

    var distance = Math.round(clickLine.getLength() + moveLine.getLength()), // 선의 총 거리를 계산합니다
      content =
        '<div class="dotOverlay distanceInfo">총거리 <span class="number">' +
        distance +
        "</span>m</div>"; // 커스텀오버레이에 추가될 내용입니다

    // 거리정보를 지도에 표시합니다
    showDistance(content, mousePosition);
  }
});

// 지도에 마우스 오른쪽 클릭 이벤트를 등록합니다
// 선을 그리고있는 상태에서 마우스 오른쪽 클릭 이벤트가 발생하면 선 그리기를 종료합니다
kakao.maps.event.addListener(map, "rightclick", function (mouseEvent) {
  // 지도 오른쪽 클릭 이벤트가 발생했는데 선을 그리고있는 상태이면
  if (drawingFlag) {
    // 마우스무브로 그려진 선은 지도에서 제거합니다
    moveLine.setMap(null);
    moveLine = null;

    // 마우스 클릭으로 그린 선의 좌표 배열을 얻어옵니다
    var path = clickLine.getPath();

    // 선을 구성하는 좌표의 개수가 2개 이상이면
    if (path.length > 1) {
      // 마지막 클릭 지점에 대한 거리 정보 커스텀 오버레이를 지웁니다
      if (dots[dots.length - 1].distance) {
        dots[dots.length - 1].distance.setMap(null);
        dots[dots.length - 1].distance = null;
      }

      var distance = Math.round(clickLine.getLength()), // 선의 총 거리를 계산합니다
        content = getTimeHTML(distance); // 커스텀오버레이에 추가될 내용입니다

      // 그려진 선의 거리정보를 지도에 표시합니다
      showDistance(content, path[path.length - 1]);
    } else {
      // 선을 구성하는 좌표의 개수가 1개 이하이면
      // 지도에 표시되고 있는 선과 정보들을 지도에서 제거합니다.
      deleteClickLine();
      deleteCircleDot();
      deleteDistnce();
    }

    // 상태를 false로, 그리지 않고 있는 상태로 변경합니다
    drawingFlag = false;
  }
});

// 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
function deleteClickLine() {
  if (clickLine) {
    clickLine.setMap(null);
    clickLine = null;
  }
}

// 마우스 드래그로 그려지고 있는 선의 총거리 정보를 표시하거
// 마우스 오른쪽 클릭으로 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 생성하고 지도에 표시하는 함수입니다
function showDistance(content, position) {
  if (distanceOverlay) {
    // 커스텀오버레이가 생성된 상태이면

    // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
    distanceOverlay.setPosition(position);
    distanceOverlay.setContent(content);
  } else {
    // 커스텀 오버레이가 생성되지 않은 상태이면

    // 커스텀 오버레이를 생성하고 지도에 표시합니다
    distanceOverlay = new kakao.maps.CustomOverlay({
      map: map, // 커스텀오버레이를 표시할 지도입니다
      content: content, // 커스텀오버레이에 표시할 내용입니다
      position: position, // 커스텀오버레이를 표시할 위치입니다.
      xAnchor: 0,
      yAnchor: 0,
      zIndex: 3,
    });
  }
}

// 그려지고 있는 선의 총거리 정보와
// 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 삭제하는 함수입니다
function deleteDistnce() {
  if (distanceOverlay) {
    distanceOverlay.setMap(null);
    distanceOverlay = null;
  }
}

// 선이 그려지고 있는 상태일 때 지도를 클릭하면 호출하여
// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 표출하는 함수입니다
function displayCircleDot(position, distance) {
  // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
  var circleOverlay = new kakao.maps.CustomOverlay({
    content: '<span class="dot"></span>',
    position: position,
    zIndex: 1,
  });

  // 지도에 표시합니다
  circleOverlay.setMap(map);

  if (distance > 0) {
    // 클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
    var distanceOverlay = new kakao.maps.CustomOverlay({
      content: '<div class="dotOverlay">거리 <span class="number">' + distance + "</span>m</div>",
      position: position,
      yAnchor: 1,
      zIndex: 2,
    });

    // 지도에 표시합니다
    distanceOverlay.setMap(map);
  }

  // 배열에 추가합니다
  dots.push({ circle: circleOverlay, distance: distanceOverlay });
}

// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 지도에서 모두 제거하는 함수입니다
function deleteCircleDot() {
  var i;

  for (i = 0; i < dots.length; i++) {
    if (dots[i].circle) {
      dots[i].circle.setMap(null);
    }

    if (dots[i].distance) {
      dots[i].distance.setMap(null);
    }
  }

  dots = [];
}

// 마우스 우클릭 하여 선 그리기가 종료됐을 때 호출하여
// 그려진 선의 총거리 정보와 거리에 대한 도보, 자전거 시간을 계산하여
// HTML Content를 만들어 리턴하는 함수입니다
function getTimeHTML(distance) {
  // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
  var walkkTime = (distance / 67) | 0;
  var walkHour = "",
    walkMin = "";

  // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
  if (walkkTime > 60) {
    walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + "</span>시간 ";
  }
  walkMin = '<span class="number">' + (walkkTime % 60) + "</span>분";

  // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
  var bycicleTime = (distance / 227) | 0;
  var bycicleHour = "",
    bycicleMin = "";

  // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
  if (bycicleTime > 60) {
    bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + "</span>시간 ";
  }
  bycicleMin = '<span class="number">' + (bycicleTime % 60) + "</span>분";

  // 자동차의 평균 시속은 50km/h 이고 이것을 기준으로 자전거의 분속은 833m/min입니다
  var carTime = (distance / 833) | 0;
  var carHour = "",
    carMin = "";

  // 계산한 자동차 시간이 60분 보다 크면 시간으로 표출합니다
  if (carTime > 60) {
    carHour = '<span class="number">' + Math.floor(carTime / 60) + "</span>시간 ";
  }
  carMin = '<span class="number">' + (carTime % 60) + "</span>분";

  // 거리와 도보 시간, 자전거 시간, 자동차 시간을 가지고 HTML Content를 만들어 리턴합니다
  var content = '<ul class="dotOverlay distanceInfo">';
  content += "    <li>";
  content +=
    '        <span class="label">총거리</span><span class="number">' + distance + "</span>m";
  content += "    </li>";
  content += "    <li>";
  content += '        <span class="label">도보</span>' + walkHour + walkMin;
  content += "    </li>";
  content += "    <li>";
  content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
  content += "    </li>";
  content += "    <li>";
  content += '        <span class="label">자동차</span>' + carHour + carMin;
  content += "    </li>";
  content += "</ul>";

  return content;
}
