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
			// 포스트에 달려 있는 모든 댓글 목록 보여줌.
			getAllComments();
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
	// 익명함수로 만들지 않고 함수를 하나 만듦. => 코드가 길어서 가독성이 안 좋아질 것을 우려

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

		// 댓글 내용, 댓글 작성자가 비어 있는 지 체크
		if (ctext === "" || username === "") {
			alert("댓글 내용과 작성자는 반드시 입력하세요.");
			return; // 이벤트 리스너를 종료
		}

		// Ajax 요청에서 보낼 데이터 객체를 생성
		/* const data = {
			postId: postId,
			ctext: ctext,
			username: username,
		}; */
		const data = { postId, ctext, username };
		// 자바스크립트 신 문법
		console.log(data);

		// POST 방식의 Ajax 요청을 보냄. 응답 성공/실패 콜백을 등록
		axios
			.post("../api/comment", data)
			// 여기에 적혀져 있는 경로는 URL 주소임.
			// 현재 주소는 http://localhost:8080/spring2/post/detail
			// 현재 폴더가 post라고 생각하면 됨.
			// spring2/api/comment를 요청주소로 사용함.
			// 우리가 PostMapping으로 설정한 주소임.
			// 같이 보낼 데이터(유저가 작성한 데이터)를 주소 옆에 나열 해줌.
			.then((response) => {
				// 성공했을 떄 응답(response)할 콜백함수
				// 컨트롤러가 리턴한 응답이 ok이면 여기에 옴.
				// console.log(response);
				console.log(response.data);
				// => RestController에서 보낸 응답 데이터
				// 이 데이터는 서비스의 cretae메서드의 리턴값.
				// 성공했을 때는 result = 1
				if (response.data === 1) {
					alert("댓글 1개 등록 성공");
					document.querySelector("textarea#ctext").value = "";
					document.querySelector("input#username").value = "";
					// 댓글 등록 시 input과 textarea를 비움.

					// 댓글 등록 성공 시 댓글 목록을 불러옴
					getAllComments();
				}
			})
			.catch((error) => {
				// 실패(error)했을 때 실행할 콜백함수.
				// 컨트롤러가 리턴한 응답이 error이면 여기에 옴.
				// 지금은 error를 리턴한 적이 없으므로 여기에 올 일이 없음.
				console.log(error);
			});
	}

	// 포스트에 달려 있는 모든 댓글 목록 가져오기
	function getAllComments() {
		// 댓글 목록을 요청하기위한 포스트 번호
		const postId = document.querySelector("input#id").value;

		// 댓글 목록을 요청하기 위한 REST API URI
		const uri = `../api/comment/all/${postId}`;

		// Ajax 요청을 보냄.
		axios
			.get(uri)
			.then((response) => {
				console.log(response.data);
				// 댓글 목록을 HTML로 작성 -> div#comments 영역에 출력.
				makeCommentElements(response.data);
			})
			.catch((error) => {
				console.log(error);
			});
	}

	// 댓글 목록(댓글 객체들의 배열)을 아규먼트로 전달받아서 HTML을 작성,.
	function makeCommentElements(data) {
		// 댓글 목록 HTML이 삽입될 div
		const divComments = document.querySelector('div#comments');

		// 댓글 목록 HTML 코드
		let htmlStr = '';
		for (let comment of data) {
			/*
				JS 반복문 종류
				for (let i; i < 10; i++)
				for(let x of list) => 원소꺼내기
				for(let i in list) => 인덱스 꺼내기
			*/
			const modifiedTime = new Date(comment.modifiedTime).toLocaleString();
			// 자바스크립트의 날짜타입(Date)으로 변환하고 문자열로 변환(toLocaleString)

			htmlStr += `
				<div class="card card-body my-1">
					<div style="font-size: 0.75rem">
						<span>${comment.id}</span>
						<span class="fw-bold">${comment.username}</span>
						<span class="text-secondary">${modifiedTime}</span> 
					</div>
					<div>${comment.ctext}</div>
					<div>
						<button class="btnDeleteComment btn btn-outline-danger btn-sm"
							data-id="${comment.id}">삭제</button>
						<button class="btnModifyComment btn btn-outline-primary btn-sm"
							data-id="${comment.id}">수정</button>
					</div>
				</div>
			`;
			// modifiedTime은 바로 위에서 자바스크립트에서 쓸 수 있게 변환한 변수를 사용!
		}
		// 작성된 HTML 코드를 div 영역에 삽입.
		divComments.innerHTML = htmlStr;

		// 모든 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
		const btnDeletes = document.querySelectorAll('button.btnDeleteComment');
		for (let btn of btnDeletes) {
			btn.addEventListener('click', deleteComment);
		}

		// TODO: 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 설정.

	}

	function deleteComment(event) {
		// 이벤트 리스너 콜백의 아규먼트 event 객체는 target 속성을 가지고 있음.
		console.log(event.target);
		// 이벤트가 발생한 요소(타겟)를 알아낼 수 있음.

		const id = event.target.getAttribute('data-id');
		// HTML 요소의 속성 값 찾기
		// 우리가 data-id라고 만든 속성의 값을 가져오는 코드

		// 삭제 여부 확인
		const result = confirm('댓글을 삭제하시겠습니까?');
		if (!result) { // 사용자가 취소를 눌렀을 때.
			return;
		}

		// Ajax 요청을 보낼 REST API URI
		const uri = `../api/comment/${id}`;

		// Ajax 요청을 보냄.
		axios
			.delete(uri)
			.then((response) => {
				console.log(response.data);
				alert(`댓글(${id}) 삭제 성공`);
				getAllComments(); // 댓글 목록 갱신
			})
			.catch((error) => {
				console.log(error);
			});
	}

});
