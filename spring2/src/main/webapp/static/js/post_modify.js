/**
 * modify.jsp에 포함.
 */

document.addEventListener("DOMContentLoaded", () => {
	// 콜백함수 안에서 선언한 변수는 지역변수라 오류를 줄일 수 있음.

	// 필요한 요소들을 찾음.
	const modifyForm = document.querySelector("form#modifyForm");
	const inputId = document.querySelector("input#id");
	const inputTitle = document.querySelector("input#title");
	const textContent = document.querySelector("textarea#content");

	// 버튼 요소들을 찾음.
	const btnDelete = document.querySelector("button#btnDelete");
	const btnUpdate = document.querySelector("button#btnUpdate");

	// 삭제 버튼의 클릭 이벤트 리스너:
	btnDelete.addEventListener("click", () => {
		const result = confirm("정말 삭제하시겠습니까?");
		// console.log(result); // 예: true, 아니오: false
		if (result) {
			// 사용자가 [확인]을 선택했을 때
			// GET 방식의 delete 요청을 서버로 보냄.
			location.href = `delete?id=${inputId.value}`;
		}
	});

	// 업데이트 버튼의 클릭 이벤트 리스너:
	btnUpdate.addEventListener("click", () => {
		// 제목과 내용이 비어있는 지 체크:
		if (inputTitle.value === "" || textContent.value === "") {
			alert("제목과 내용은 반드시 입력하세요.");
			return; // 메서드 종료
		}
		// 업데이트 내용을 저장 확인:
		const result = confirm("변경 내용을 저장하시겠습니까?");
		if (result) {
			modifyForm.action = "update"; // 요청 주소
			modifyForm.method = "POST"; // 요청 방식
			modifyForm.submit(); // 폼 양식 데이터 제출(서버로 요청 보냄)
		}
	});
});
