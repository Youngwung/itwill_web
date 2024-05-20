/**
 *
 */

// JSON(JavaScript Object Notation): 자바스크립트 객체 표기법.
// {property: value, ... }

const person = {
  name: "홍길동", // name: property, 저장을 할때 등호(=)가 아니라 콜론(:)을 사용한다.
  age: 16, // property는 쉼표(,)로 구분한다.
  phone: ["010-0000-0000", "02-0000-0000"], // 마지막 property 끝에 쉼표(,)는 있어도 상관 없다. 사실 필요없음.
};
console.log(person); // 출력문: {name: '홍길동', age: 16, phone: Array(2)}

// 객체가 가지고 있는 property 접근: (1) 참조 연산자, (2) 인덱스 연산자
console.log(person.name); // (1) 참조 연산자: object.propertyName
console.log(person["age"]); // (2) 인덱스 연산자: object['propertyName']
console.log(person.phone[0]);
console.log(person["phone"][1]);

// 객체의 프로퍼티 값 변경.
person.age = 17;
console.log(person.age); // 17출력

// 자바스크립트의 객체는 객체가 생성된 이후에 프로퍼티를 추가할 수 있음.
person.email = "hgd@itwill.com";
console.log(person); // email: 'hgd~~'가 추가됨.

// 메서드를 갖는 객체:
const score = {
  html: 100,
  css: 90,
  js: 82,
  sum: function () {
    // 익명함수와 유사함. 등호(=) 대신에 콜론(:)을 사용함.
    return this.html + this.css + this.js;
  },
  min: function () {
    return this.sum() / 3;
  },
};
console.log(score);
console.log(score.sum());
console.log(score.min());

// 자바에 비해 객체를 만들기 쉽지만 재활용이 안됨.
// 새 객체를 생성하고 싶다면 복붙밖에 방법이 없음.
const score10 = {
  html: 90,
  css: 80,
  js: 72,
  sum: function () {
    // 익명함수와 유사함. 등호(=) 대신에 콜론(:)을 사용함.
    return this.html + this.css + this.js;
  },
  min: function () {
    return this.sum() / 3;
  },
};
// 다른 점수를 갖는 객체를 만들기 위해서는 똑같은 코드의 반복. 그래서 나온 문법이 생성자 함수

// 생성자 함수(constructor function): this 키워드를 사용해서 프로퍼티를 선언한 함수.
function Score(html, css, js) {
  // 자바에서 사용한 생성자의 문법과 유사함.
  // 필드(field)
  this.html = html;
  this.css = css;
  this.js = js;

  // 메서드
  this.sum = function () {
    return this.html + this.css + this.js;
  };
  this.min = function () {
    return this.sum() / 3;
  };
}

// 생성자 함수를 사용한 객체 생성: new 생성자함수();
const score1 = new Score(80, 70, 65);
console.log(score1);
console.log(score1.sum());
console.log(score1.min());

const score2 = new Score(); //-> 모든 필드는 undefined가 됨.
console.log(score2); // html, css, js = undefined
console.log(score2.sum()) // undefined + undefined = NaN(Not a Number)

// 자바스크립트 객체는 for-in 구문에서 사용할 수 있음. for-in: 배열에서 인덱스를 주던 구문
// 객체로 사용하면 프로퍼티 이름(name같은거)를 줌.
const student = {
    no: 101,
    name: '오쌤',
    classNo: 10,
};

for (let x in student) {
    console.log(x, ':', student[x]); // student.x =>안됨. student의 x 변수에 접근하겠다는 뜻.
    // 프로퍼티 접근 방법 중 인덱스로 접근 방법을 사용함
    // object['propertyName']
}

// 클래스:
class Rectangle {
    // 생성자: 필드 초기화
    constructor(width, height) { // JS에서는 필드 선언이 필요없음. 생성자 안에서 됨.
        this.width = width;
        this.height = height;
    }

    // 메서드: function 키워드를 사용하지 않음!
    area() {
        return this.width * this.height;
    }
    
    perimeter() {
        return 2 * (this.width + this.height);
    }
}

const rec1 = new Rectangle(2, 3);
console.log('area = ', rec1.area());
console.log('perimeter = ', rec1.perimeter());

const rec2 = new Rectangle();
console.log(rec2);

// 원(Circle) 클래스 선언:
// - 필드: radius. 기본값 0.
// - 메서드: area(넓이), 둘레 길이(perimeter)
class Circle {
    constructor(radius = 0) {
        this.radius = radius;
    }

    area() {
        return Math.PI * this.radius* this.radius;
    }   

    perimether() {
        return 2 * Math.PI * this.radius;
    }
}
const cir1 = new Circle(); //-> 생성자의 default parameter가 사용되는 경우.
console.log('넓이 = ', cir1.area());
console.log('둘레 = ', cir1.perimether());

const cir2 = new Circle(10);
console.log('넓이 = ', cir2.area());
console.log('둘레 = ', cir2.perimether());