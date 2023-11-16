

const memberList = JSON.parse(localStorage.getItem("memberList")) || [];

document.getElementById("register").addEventListener("click", (e) => {
  const name = document.getElementById("name");
  const email = document.getElementById("email");
  const password = document.getElementById("pwd");
  const confirmPassword = document.getElementById("pwd-confirm");
  console.log(1);
  if (
    name.value === "" ||
    email.value === "" ||
    password.value === "" ||
    confirmPassword === ""
  ) {
    alert("빠진 내용이 없는지 확인해 주세요!");
    return;
  }

  for (let i = 0; i < memberList.length; i++) {
    if (memberList[i].email === email.value) {
      console.log(memberList[i].email);
      console.log(email.value);
      alert("이미 존재하는 이메일입니다!");
      email.value = "";
      return;
    }
  }

  if (password.value !== confirmPassword.value) {
    alert("비밀번호가 일치하지 않습니다!");
    password.value = "";
    confirmPassword.value = "";
    return;
  }

  const newMember = {
    name: name.value,
    email: email.value,
    password: password.value,
  };

  memberList.push(newMember);
  localStorage.setItem("memberList", JSON.stringify(memberList));
  alert("회원가입이 완료되었습니다!");
  window.location.replace("login.html");
});
