const memberList = JSON.parse(localStorage.getItem("memberList"));
let flag = false;

const loginForm = document.getElementById("login");

loginForm.addEventListener("click", () => {
  const id = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  if (id === "" || password === "") {
    alert("회원정보를 정확히 입력해 주세요");
    return;
  }

  for (const member of memberList) {
    if (member.email === id && member.password === password) {
      window.location.href = "../index.html";
    	
      alert("환영합니다, " + member.name + "님");
      localStorage.setItem("isLoggedIn", "true");
      localStorage.setItem("currentMember", JSON.stringify(member));
      return;
    }
  }
  alert("없는 회원이거나 비밀번호가 틀립니다");
});
