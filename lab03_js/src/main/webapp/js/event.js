/**
 * 
 */
const btn = document.querySelector('button#btnInput');
let html = '';

btn.addEventListener('click', (e) => {
    // console.log(e); //-> PointerEvent 객체.
    const itemInput = document.querySelector('input#itemInput');
    const output = document.querySelector('ul#itemList');
    html += `<li>${itemInput.value}</li>`
    output.innerHTML = html;
    // itemInput에 써져있는 내용을 output에 출력
    itemInput.value = '';
    // 출력 후 itemInput을 비워줌.
});

// TODO: input#itemInput2 요소에 'keydown' 이벤트 리스너를 등록:
// 엔터키가 눌렸을 때, input에 입력된 내용을 ul#itemList2의 리스트 아이템으로 추가.
let html2 = '';
document.querySelector('#itemInput2').addEventListener('keydown', (e) => {
    if (e.key =='Enter'){
        const itemInput2 = document.querySelector('#itemInput2');
        const itemList2 = document.querySelector('#itemList2');
        html2 += `<li>${itemInput2.value}</li>`;
        itemList2.innerHTML = html2
        itemInput2.value = '';
    }
});

// TODO: input#username 요소에 'change' 이벤트 리스너를 등록:
// input에 입력된 내용이 바뀔 때마다 div를 입력 내용으로 덮어씀.\
document.querySelector('#username').addEventListener('change',(e) => {
    const username = document.querySelector('#username');
    const output = document.querySelector('#output');
    output.innerHTML = `<p>${username.value}</p>`;
});
// 보다 change같은 핸들러
document.querySelector('#username2').addEventListener('keydown',(e) => {
    const username2 = document.querySelector('#username2');
    const output2 = document.querySelector('#output2');
    console.log('b');
    output2.innerHTML = `<p>${username2.value}</p>`;
});


// TODO: img#bulb 요소에 'mouseenter' 이벤트 리스너를 등록:
// img의 src를 'images/bulb_on.gif'로 변경.
document.querySelector('img#bulb').addEventListener('mouseenter', (e) => {
    const bulb = document.querySelector('img#bulb');
    bulb.src = 'images/bulb_on.gif';
});

// TODO: img#bulb 요소에 'mouseleave' 이벤트 리스너를 등록:
// img의 src를 'images/bulb_off.gif'로 변경.
document.querySelector('img#bulb').addEventListener('mouseleave', (e) => {
    const bulb = document.querySelector('img#bulb');
    bulb.src = 'images/bulb_off.gif';
});