
let member = JSON.parse(localStorage.getItem("currentMember"));
const user = document.getElementById("name-container");
const email = document.getElementById("email-container");
const password = document.getElementById("password-container");

user.innerHTML = member.name;
email.innerHTML = member.email;
let temp = ``;
for (let i = 0; i < member.password.length; i++) {
  temp += "*";
}
password.innerHTML = temp;

document.getElementById("show-pwd").addEventListener("click", () => {
  member = JSON.parse(localStorage.getItem("currentMember"));
  const isPassword = document
    .getElementById("show-pwd")
    .classList.contains("active");
  if (isPassword === true) {
    password.innerHTML = member.password;
  } else {
    let temp = ``;
    for (let i = 0; i < member.password.length; i++) {
      temp += "*";
    }
    password.innerHTML = temp;
  }
});

document.getElementById("delete").addEventListener("click", () => {
  
  let answer = window.confirm("정말로 탈퇴하시겠습니까?");

  if (answer) { 
    const memberList = JSON.parse(localStorage.getItem("memberList"));
    let temp = [];
    for (let i = 0; i < memberList.length; i++) { 
      if (memberList[i].email!== member.email) {
        temp.push(memberList[i]);
      }
    }
    localStorage.setItem("memberList", JSON.stringify(temp));
    localStorage.setItem("currentMember", null);
    alert("탈퇴가 완료되었습니다! 안녕히 가세요")
    window.location.href = "index.html";
  }
  else {
    return;
  }

  
});

document.getElementById("modify").addEventListener("click", () => {
  
  const modifyBtn = document.getElementById("modify");
  const nameContainer = document.getElementById("name-container");
  const passwordContainer = document.getElementById("password-container");
  const nameModify = document.getElementById("name-modify");
  const passwordModify = document.getElementById("password-modify");
  const showBtn = document.getElementById("show-pwd");
  const modifyInfo = document.getElementById("modify-info");
  //수정하기 시작
  if (modifyBtn.classList.contains("active")) {
    modifyBtn.classList.remove("active");
    nameContainer.classList.remove("d-none");
    passwordContainer.classList.remove("d-none");
    nameModify.classList.add("d-none");
    passwordModify.classList.add("d-none");
    showBtn.classList.remove("d-none");
    
    modifyInfo.classList.add("d-none");
    if (nameModify.value === "") {
      alert("이름을 정확히 입력해주세요");
      
    }
    else if (passwordModify.value === "") {
      alert("비밀번호를 정확히 입력해주세요");
    }
    else { 
      const memberList = JSON.parse(localStorage.getItem("memberList"));
      for (let i = 0; i < memberList.length; i++) { 
        if (memberList[i].email == member.email) {
          memberList[i].name = nameModify.value;
          memberList[i].password = passwordModify.value;
          localStorage.setItem("currentMember", JSON.stringify(memberList[i]));
        }
      }
      alert("수정이 정상적으로 완료되었습니다!");
      window.location.reload();
      localStorage.setItem("memberList", JSON.stringify(memberList));
      user.innerHTML = nameModify.value;

      const isPassword = document
    .getElementById("show-pwd")
    .classList.contains("active");
  if (isPassword === true) {
    password.innerHTML = member.password;
  } else {
    let temp = ``;
    for (let i = 0; i < member.password.length; i++) {
      temp += "*";
    }
    password.innerHTML = temp;
  }
    }    
  }
  //수정 끝
  else {
    modifyBtn.classList.add("active");
    nameContainer.classList.add("d-none");
    passwordContainer.classList.add("d-none");
    nameModify.classList.remove("d-none");
    passwordModify.classList.remove("d-none");
    showBtn.classList.add("d-none");
    nameModify.setAttribute("value", member.name);
    passwordModify.setAttribute("value", member.password);
    modifyInfo.classList.remove("d-none");
    
  }

})