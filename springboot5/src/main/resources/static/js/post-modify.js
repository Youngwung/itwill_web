/**
 * /post/modify.html 파일에 포함
 * 포스트 업데이트, 삭제 버튼 이벤트 처리.
 */

// 버튼들을 찾는 코드를 작성할 건데 버튼들이 로딩되기 전에 찾으려고하면 에러가 뜸.
// DOMContentLoaded 이벤트 리스너를 써서 안정적으로 태그들이 로딩된 후 찾으려고 하는거임,
document.addEventListener('DOMContentLoaded', () => {
	const id = document.querySelector('#id').value;
	// 삭제 버튼을 찾고, 클릭 이벤트 리스너를 설정.
	// 컨펌 창 한번 띄우셈.
	const btnDelete = document.querySelector('#btnDelete');
	console.log(btnDelete);
	btnDelete.addEventListener('click', () => {
		let result = confirm('정말 삭제하시겠습니까?');
		if (result) {
			location.href = `delete?id=${id}`
		}
	});

	// 업데이트 버튼을 찾고, 클릭 이벤트 리스너를 설정.
	// 입력창이 비어있는 지 검사하셈.
	// 컨펌 창 한번 띄우셈.
	const btnUpdate = document.querySelector('#btnUpdate');
	console.log(btnUpdate);
	btnUpdate.addEventListener('click', () => {
		let title = document.querySelector('#title').value.trim;
		let content = document.querySelector('#content').value.trim;
		// trim => 문자열 시작과 끝 부분에 있는 모든 공백을 제거하는 메서드.
		// 공백만 들어있는 제목과 내용을 빈 문자열로 만들기 위함.
		if (title === '' || content === '') {
			alert('제목과 내용을 입력해주세요.');
			return;
		} else {
			let result = confirm('정말 수정하시겠습니까?')
			if (result) {
				const updateForm = document.querySelector('#updateForm');
				updateForm.method = "POST";
				updateForm.action = "update";
				updateForm.submit();
			}
		}
	});
});