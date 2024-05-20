/**
 * element.html에 포함
 */

// button#btn1인 요소를 찾음:
const btn1 = document.querySelector('button#btn1');
let tgl1 = false;
let tgl2 = false;
let tgl3 = false;
let tgl4 = false;
let tgl5 = false;
// btn1 요소에 클릭 이벤트 리스너를 설정:
btn1.addEventListener('click', function () {
    // document.getElementById(id) 사용. id가 "id1"인 요소 찾음.
    // -> 같은 아이디를 갖는 요소가 여러개가 있을 경우 가장 먼저 찾은 요소를 리턴.
    // -> 쿼리셀렉도 마찬가지임.
    const div1 = document.getElementById('id1');
    // console.log(div1);

    // div1 요소의 바탕색을 변경: 
    // 토글은 내가만듬.
    if (tgl1 == false) {
        div1.style.backgroundColor = 'lime';
        tgl1 = true;
    } else {
        div1.style.backgroundColor = 'white';
        tgl1 = false;
    }
});
// button#btn2인 요소를 찾음.
const btn2 = document.querySelector('#btn2');
// btn2에 클릭 이벤트 리스너를 설정.
btn2.addEventListener('click', function () { 
    const divs = document.getElementsByClassName('c1'); 
    // getElementsByClassName -> HTMLCollection 객체 리턴. (배열이랑 비슷) 차이점: forEach 사용 불가.
    if (tgl2 == false) {
        //  console.log(divs); // 객체를 리턴.
        for (let e of divs) {
            // 이벤트 리스너는 class 속성 값이 "c1"인 요소들의 바탕색을 변경.
            e.style.backgroundColor = 'tomato';
        }
        tgl2 = true;
    } else {
        for (let e of divs) {
            e.style.backgroundColor = 'white';
        }
        tgl2 = false;
    }
})

// button#btn3 요소 찾음:
const btn3 = document.querySelector('button#btn3');

btn3.addEventListener('click', () => {
    // 태그 이름이 div인 모든 요소들을 찾아서 바탕색을 변경
    const div3 = document.getElementsByTagName('div');
    if (tgl3 == false) {
        for (let e of div3) {
            e.style.backgroundColor = 'slateblue';
        }
        tgl3 = true;
    } else {
        for (let e of div3) {
            e.style.backgroundColor = 'white';
        }
        tgl3 = false;
    }
});

// document.querySelector() [가장 먼저 찾은 단 한개의 요소만 찾아줌.]
// document.querySelectorAll() [배열 형태: 모든 요소를 찾아줌]: 
// - CSS 선택자 문법으로 아규먼트를 전달.
// tagName, #id, .class, tagname#id.class
// parent > child
// ancestor descendent
// element: pseudo-selector

const btn4 = document.querySelector('#btn4');

btn4.addEventListener('click', function () {
    const div4 = document.querySelector('#id4');
    if (tgl4 == false) {
        div4.style.backgroundColor = 'lightgray';
        tgl4 = true;
    } else {
        div4.style.backgroundColor = 'white';
        tgl4 = false;
    }
});

const btn5 = document.querySelector('#btn5');

btn5.addEventListener('click', () => {
    const div5 = document.querySelectorAll('.c2');
    // querySelectorAll -> NodeList(배열과 비슷함)를 리턴: forEach문을 사용 가능하다.
    if (tgl5 == false) {
        for (let e of div5) {
            e.style.backgroundColor = 'violet';
            tgl5 = true;
        } 
        
    } else {
        // NodeList는 forEach를 사용 가능하다.
        div5.forEach((e) => e.style.backgroundColor = 'white');
        tgl5 = false;
    }
   
});
