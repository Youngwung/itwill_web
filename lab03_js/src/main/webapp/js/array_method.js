/**
 * array_method.html에 포함.
// console.log('array_method'); //연결테스트

JS array 객체의 함수(메서드)들
 */

const arr = [1, 2, 3];

console.log(arr);

// 배열에 새로운 원소를 배열 끝에 추가.
arr.push(100); // push(): 원본 배열의 끝에 새로운 원소를 추가. 원본 배열이 바뀜.
// arr는 const로 선언했는데 왜 arr가 바뀜?
// => arr는 객체이므로 주소값이 변하지 않으면 문제없음. 배열의 원소는 바뀔 수 있음.
// ==> arr = [1, 2, 3, 100]; 으로 새롭게 초기화 하는 것과 다른 것.
console.log(arr);

let result = arr.concat(200); // 원본 배열을 변경하지 않고, 원소가 추가된 새로운 배열을 리턴.
console.log(arr);
console.log(result);

// 배열의 마지막 원소를 삭제:
arr.pop(); // pop(): 원본 배열의 마지막 원소를 삭제. 원본 배열의 내용이 바뀜
console.log(arr); // [1, 2, 3]

result = arr.slice(0, -1);
// slice(start, end): 배열에서 start 인덱스부터 end 인덱스까지를 잘라낸 새로운 배열을 리턴.
// JS에서 인덱스
// [0,1,2,3,4,5]
// 0 1 2 3 4 5
//-6-5-4-3-2-1 : 음수 인덱스 (자바엔 없음)
// 0부터 -1번인덱스 까지 삭제한 것은 0번 인덱스의 원소(0)는 지우지 않고, 5번 인덱스의 원소(5)는 지운다. 
// pop()과 비슷한 기능이 됨. slice는 원본 배열이 변경되지 않음. 
console.log(arr);
console.log(result);

const arr2 = [10, 100, -1, 90, 780];

// - toSorted(): 배열의 원소들을 문자열로 변환해서 크기 비교를 함.
// - 오름차순 정렬된 "새로운" 배열을 리턴.
// - 원본 배열은 변경되지 않음.
// toSorted(callback): 배열 원소들의 크기 비교를 할 때 사용할 콜백을 아규먼트로 전달.
// 정렬할 때 대소 비교를 (x-y)의 결과가 양수면 x가 크고, 음수면 y가 큰거라고 판별할 수 있게 콜백함수를 만들어줌.
result = arr2.toSorted((x,y) => x - y);
console.log(result);

// sort(): 배열의 원소들을 문자열로 변환해서 크기 비교를 함.
// - 원본 배열의 원소 순서를 오른차순으로 변경. 원본 배열이 변경.
// - sort(callback): 배열 원소들의 크기 비교에서 사용하는 콜백을 아규먼트로 전달.
arr2.sort((x,y) => x - y);
console.log(arr2);


// filter, map, forEach, reduce:
const numbers = [1, 2, 3, 4, 5, 6];

// 배열 numbers의 원소들 중에서 홀수들로만 이루어진 새로운 배열
const odds = [];
for (let idx in numbers) {
    if (numbers[idx] % 2 == 1) {
        odds.push(numbers[idx]);
    }
}
console.log(odds);

// 이거 간단하게 만들어보기 (filter)
// filter: 조건문
// 아규먼트 1개짜리 함수를 아규먼트로 받는 함수.
result = numbers.filter(function (x) {return x%2;}); // 익명함수를 사용한 filter
result = numbers.filter((x) => x % 2); // JS에서 조건문 안의 0은 false, 그 외의 모든 숫자는 true
console.log(result);


// 배열 numbers의 원소들의 제곱을 원소로 갖는 새로운 배열
const squares = [];
for (let x of numbers) {
    squares.push(x * x);
}
console.log(squares);

// 간단하게 만들기(map)

result = numbers.map(function (x) {return x * x;});
result = numbers.map((x) => x * x);
console.log(result);


// 배열 쉽게 다루기(forEach)
// 배열을 다룰 때 사용하는 필수적인 for문을 간단하게 사용할 수 있다.
numbers.forEach((x) => console.log(x));


// 배열 numbers의 모든 원소들의 합계
let sum = 0;
for (let value of numbers) {
    sum += value; // sum = sum + value;
}

console.log(`sum = ${sum}`);

sum = numbers.reduce((acc, cur) => acc + cur, 0);
console.log(`sum = ${sum}`);
// reduce(callback, initialValue)
// 0은 sum = 0 => 초기화값, acc = acc + cur, cur는 배열의 원소, 

// numbers의 모든 원소들의 곱:
let dot = 1;
for (value of numbers) {
    dot *= value;
}
console.log(`dot = ${dot}`);

dot = numbers.reduce((acc, cur) => acc * cur, 1);
console.log(`dot = ${dot}`);

// numbers의 원소들 중에서 짝수들의 합: 2+4+6
result = numbers.filter((x) => x % 2 === 0).reduce((acc, cur) => acc + cur, 0);
console.log(`짝수 합 = ${result}`);
// numbers의 원소들의 제곱의 합: 1 + 4 + 9 + .. + 36
result = numbers.map((x) => (x * x)).reduce((acc, cur) => acc + cur, 0);
console.log(`제곱 합 = ${result}`);
// numbers의 원소들 중에서 짝수들의 제곱의 합: 4 + 16 + 36
result = numbers.filter((x) => x % 2 === 0).map((x) => x * x).reduce((acc, cur) => acc + cur, 0);
console.log(`짝수 제곱 합 = ${result}`);



