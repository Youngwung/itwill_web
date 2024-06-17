/**
 * detail.jsp에 포함.
 */

document.addEventListener("DOMContentLoaded", () => {
	// btnToggleComment 버튼 요소를 찾음. 댓글 보기 버튼임
	const btnToggleComment = document.querySelector("button#btnToggleComment");

	// collapseComments div 요소를 부트스트랩의 Collapse 객체로 생성.
	const bsCollapse = new bootstrap.Collapse("div#collapseComments", {
		toggle: false,
	});
	// 첫 번째 아규먼트는 문자열, 두 번째 아규먼트는 객체. JSON 아님. JSON은 전체가 문자열이고 속성 이름도 문자열.

	// 댓글 토글 버튼에 클릭 이벤트 리스너를 등록
	btnToggleComment.addEventListener("click", () => {
		bsCollapse.toggle();

		if (btnToggleComment.innerHTML === "댓글 보기") {
			btnToggleComment.innerHTML = "댓글 감추기";
		} else {
			btnToggleComment.innerHTML = "댓글 보기";
		}
	});

	// 버튼 btnRegisterComment 요소를 찾음.
	const btnRegisterComment = document.querySelector(
		"button#btnRegisterComment"
	);

	// 클릭 이벤트 리스너 등록
	btnRegisterComment.addEventListener("click", registerComment);
	// 익명함수로 만들 지 않고 함수를 하나 만듦. => 코드가 길어서 가독성이 안좋아 질것을 우려

	// 댓글 등록 이벤트 리스너 콜백(함수):
	function registerComment() {
		// 댓글 등록할 떄 필요한 필드들을 모두 찾아야함.
		// CreateDto를 참고해서 요소를 찾아야함
		// 찾아야될거: postId, ctext, username

		// 댓글이 달릴 포스트 번호를 찾음.
		const postId = document.querySelector("input#id").value;
		// 댓글 내용을 찾음.
		const ctext = document.querySelector("textarea#ctext").value;
		// 댓글 작성자 아이디를 찾음
		const username = document.querySelector("input#username").value;

		console.log(postId, ctext, username);
	}
});
