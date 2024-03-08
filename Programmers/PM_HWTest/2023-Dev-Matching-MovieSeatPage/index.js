const emailInput = document.getElementById("email");
const pwInput = document.getElementById("password");
const loginBtn = document.getElementById("theaterLoginBtn");
const adultBtn = document.getElementById("adultBtn");
const youthBtn = document.getElementById("youthBtn");
const theaterSeat = document.getElementById("theaterSeat");
const handicapCheckbox = document.getElementById("checkHandicap");

function init(){
    initRender();
    //이벤트 리스너 붙이기
    loginBtn.addEventListener("click", validCheck);
    for(let i=0; i<=8; i++) {
        adultBtn.children[i].addEventListener("click", (event)=>personCount(event, "adult"));
        youthBtn.children[i].addEventListener("click", (event)=>personCount(event, "youth"));
    }
}

function validCheck(){
    let alertMsg = "로그인 성공!";

    // 이메일 유효성 검사
    if(emailInput.value == "") alertMsg = "이메일 혹은 비밀번호가 입력되지 않았습니다.";
    else{
        const emailValid = /^[.0-9a-zA-Z]*[a-zA-z]+[.0-9a-zA-Z]*@[a-z\d._-]*[a-z]+[a-z\d._-]*.co$/;
        if(!emailValid.test(emailInput.value)) alertMsg = "이메일 형식이 올바르지 않습니다.";
    }
    // 비밀번호 유효성 검사
    if(pwInput.value == "") alertMsg = "이메일 혹은 비밀번호가 입력되지 않았습니다.";
    else{
        const pwValid = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@~])[a-zA-Z\d!@~]+$/;
        if(!pwValid.test(pwInput.value)) alertMsg = "비밀번호는 영문, 숫자, 특수문자를 모두 포함해야 합니다.";
        if(pwInput.value.length<8 || pwInput.value.length >20) alertMsg = "비밀번호는 최소 8자 이상, 최대 20자 이하로 구성해야 합니다.";
    }
    // 알림창 띄우기
    window.alert(alertMsg);
}

let adultCnt = 0;
let youthCnt = 0;

function initRender(){
    //초기 선택 상태
    adultBtn.children[0].classList.add('toggle');
    youthBtn.children[0].classList.add('toggle');
    handicapCheckbox.setAttribute('disabled', true);
}

function personCount(event, flag){
    //인원 선택 반영
    if(flag == "adult") {
        adultBtn.children[adultCnt].classList.remove('toggle');
        adultCnt=Number(event.target.innerText);
        adultBtn.children[adultCnt].classList.add('toggle');
    }
    else {
        youthBtn.children[youthCnt].classList.remove('toggle');
        youthCnt=Number(event.target.innerText);
        youthBtn.children[youthCnt].classList.add('toggle');
    } 
    //좌석, 장애인 체크박스 활성화 관리
    if(adultCnt+youthCnt == 0){
        for(let i=0; i<39; i++) theaterSeat.children[i].classList.add('disabled');
        handicapCheckbox.setAttribute('disabled', true);
    }else{
        for(let i=0; i<39; i++) theaterSeat.children[i].classList.remove('disabled');
        handicapCheckbox.removeAttribute('disabled');
    }
    //
}


init();