const searchEmail = document.getElementById('search-email');
const searchName = document.getElementById('search-name');

const searchBtn = document.getElementById('searchId');

searchBtn.addEventListener('click', (e) => { 
    console.log(1);
    e.preventDefault();
    const email = searchEmail.value;
    const name = searchName.value;
    const memberList = JSON.parse(localStorage.getItem('memberList'));
    let password = '';
    for (let i = 0; i < memberList.length; i++) { 
        if (memberList[i].email === email && memberList[i].name === name) { 
            password = memberList[i].password;
        }
    }

    if (password === '') {
        alert('적절한 회원을 찾지 못했습니다');
        window.location.reload();
    }
    else { 
        const questionContainer = document.getElementById('question-container');
        questionContainer.classList.add('d-none');
        const answerContainer = document.getElementById('answer-container');
        const foundPassword = document.getElementById('found-password');
        foundPassword.innerHTML = password;
        answerContainer.classList.remove('d-none');
    }
})