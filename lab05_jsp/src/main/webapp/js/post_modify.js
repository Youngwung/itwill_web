/**
 * /post/modify.jsp에 포함.
 */

// HTML DOM(Document Object Model) 컨텐트 로딩이 끝났을 떄, 이벤트 리스너를 실행.
document.addEventListener("DOMContentLoaded", () => {
	// !DOMContentLoaded는 언제 발생하는 이벤트임?
	// * html태그가 로딩이 다 됐을 때 실행하는 이벤트
	// ? 이 함수를 호출한 태그가 실행됐을 때 익명함수가 실행되는 것 같음

	// form 요소를 찾음:
	const modifyForm = document.querySelector("#modifyForm");

	// 글 번호가 입력된 input#id 요소를 찾음
	const inputId = document.querySelector("input#id");

	// 글 제목이 입력된 input#title 요소를 찾음
	const inputTitle = document.querySelector("input#title");

	// 글 내용이 작성된 textarea#content 요소를 찾음.
	const textareaContent = document.querySelector("textarea#content");

	// 삭제 버튼 찾음:
	const btnDelete = document.querySelector("button#btnDelete");

	// 수정 버튼 찾음:
	const btnUpdate = document.querySelector("button#btnUpdate");

	// 삭제 버튼에 클릭 이벤트 리스너를 설정.
	btnDelete.addEventListener("click", () => {
		const result = confirm("정말 삭제할까요?");
		// console.log(result); confirm()의 리턴 값은 true/false
		if (result) {
			// 삭제(GET 방식) 요청을 서버로 보냄.
			// *자바스크립트에서 ${}는 문자열 템플릿(`` - {물결표시 키})에서 변수를 사용하기 위한 문법
			// * JSP의 EL이랑 헷갈리겠네
			location.href = `delete?id=${inputId.value}`;
			// * 경로를 쓰는 곳임. 위 코드는 상대경로로 표시함. 현재 /post
			// * location.href를 쓰면 해당 주소로 GET방식의 요청을 보내줌.
		}
	});
	// 업데이트 버튼에 클릭 이벤트 리스너를 설정.

	btnUpdate.addEventListener("click", () => {
		// 제목과 내용이 비어있는 지 체크
		const title = inputTitle.value; // input 요소에 입력된 값.
		const content = textareaContent.value;
		if (title === "" || content === "") {
			alert("제목과 내용은 반드시 입력해야 합니다!.");
			return;
		}

		const result = confirm("변경된 내용을 저장할까요?");
		if (result) {
			modifyForm.method = "post"; // 폼 제출 방식 설정.
			modifyForm.action = "update";
			modifyForm.submit();
		}
	});
});
