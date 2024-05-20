/**
 * for.html에 포함.
 */

// 아이디가 'result'인 HTML 요소를 찾음
 const result = document.getElementById('result');
 // result 요소에 추가할 HTML 코드를 저장하기 위한 문자열 변수.
 let html = '==========1번문제. 2단 출력============= <br/><hr/>';
 
 for (let x = 1; x < 10 ; x++) {
      html += `2 x ${x} = ${2 * x} <br/>`; // esc아래있는 키.
      // ``: 문자열 템플릿
 }
 result.innerHTML += html;
 result.innerHTML += '<hr/>';
 
 // 2단은 냅두고 result에 3단부터 9단까지 문자열을 붙이기.
 html = '===========2번문제. 3단부터 이어붙이기 =========<br/><hr/>';
 for (let y = 3; y < 10; y++) {
    for (let x = 1; x < 10; x++) {
        html += `${y} x ${x} = ${x*y} <br/>`;
    }
    html += '<hr/>';
 }
 result.innerHTML += html;
 
 // break를 사용해서 n단은 nxn까지만 출력하도록하고 n <=9 까지
 
html = '==========3번문제. n단은 n x n까지만 출력========<br/><hr/>';
 for (let y = 2; y < 10; y++) {
    for (let x = 1; x < 10; x++) {
        html += `${y} x ${x} = ${x*y} <br/>`;
        if (x === y) {
            break;
        }
    }
    html += '<hr/>';
 }
 result.innerHTML += html;
 