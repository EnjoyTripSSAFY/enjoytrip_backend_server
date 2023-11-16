const current = JSON.parse(localStorage.getItem("currentMember")) || null;

if (current) {
  const before = document.getElementById("beforeLogIn");
  before.classList.add("d-none");
  const after = document.getElementById("afterLogIn");
  after.classList.remove("d-none");
} else {
  const before = document.getElementById("beforeLogIn");
  before.classList.remove("d-none");
  const after = document.getElementById("afterLogIn");
  after.classList.add("d-none");
}

document.getElementById("logout-btn").addEventListener("click", () => {
  localStorage.setItem("currentMember", null);
  window.location.href = 'index.html';
});
