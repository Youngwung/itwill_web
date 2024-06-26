/**
 * /user/signup.jsp 파일에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {
  let useridChecked = false; // 사용자 아이디 중복 체크 결과. true: 사용할 수 있는 아이디.
  let passwordChecked = false; // 비밀번호 필드 작성 여부 체크.
  let emailChecked = false; // 이메일 필드 작성 여부 체크.
	let emailDubChecked = false; // 이메일 중복 여부 체크
  
  const inputUserid = document.querySelector('input#userid');
  inputUserid.addEventListener('change', checkUserid);
  
  const inputPassword = document.querySelector('input#password');
  inputPassword.addEventListener('change', checkPassword);
  
  const inputEmail = document.querySelector('input#email');
  inputEmail.addEventListener('change', checkEmail);
  
  /* -------------------- 함수 선언 -------------------- */
  
  // 회원 가입 버튼 활성화/비활성화
  function changeButtonState() {
      const btnSignup = document.querySelector('button#btnSignup');
  
      if (useridChecked && passwordChecked && emailChecked && emailDubChecked) {
          // 버튼의 class 속성 값들 중 'disabled'를 제거. => 버튼 활성화
          btnSignup.classList.remove('disabled');
      } else {
          // 버튼의 class 속성에 'disabled'를 추가. => 버튼 비활성화
          btnSignup.classList.add('disabled');
      }
  }
  
  // userid 입력 필드의 change 이벤트 리스너
  function checkUserid(event) {
      // 중복 아이디 체크 Ajax 요청을 보내고, 응답을 받았을 때 처리.
			const userid = event.target.value; // inputUserid.value
			console.log(userid);

			const uri= `./checkid?userid=${userid}`; // 아이디 중복 체크 REST API URI
			axios
			.get(uri)
			.then((response) => {
				const checkUseridResult = document.querySelector('div#checkUseridResult');
				if(response.data ==='Y') {
					useridChecked = true;
					checkUseridResult.innerHTML = '멋진 아이디입니다.';
					checkUseridResult.classList.add('text-success');
					checkUseridResult.classList.remove('text-danger');
				} else {
					useridChecked = false;
					checkUseridResult.innerHTML = '사용 불가능한 아이디입니다.';
					checkUseridResult.classList.add('text-danger');
					checkUseridResult.classList.remove('text-success');
				}

				changeButtonState(); // 버튼 활성화 여부를 변경.
				// 아직 비밀번호와 이메일이 true로 바뀌는 메서드를 부르지 않았음
			})
			.catch((error) => {console.log(error)});
  }
  
  // 비밀번호 입력 필드의 change 이벤트 리스너
  function checkPassword(event) {
		if(event.target.value ==="") {
			passwordChecked = false;
		} else {
			passwordChecked = true;
		}
		changeButtonState(); // 버튼의 활성화/비활성화 상태를 변경.
  }
  
  // 이메일 입력 필드의 change 이벤트 리스너
  function checkEmail(event) {
		const checkEmailResult = document.querySelector('div#checkEmailResult');
		// 필요 시 정규표현식 검사를 진행하셈.
		if(event.target.value == '') {
			emailChecked=false;
			checkEmailResult.innerHTML="이메일이 빈칸입니다.";
			checkEmailResult.classList.add("text-danger");
			checkEmailResult.classList.remove("text-success");
			return;
		} else {
			emailChecked=true;
			checkEmailResult.innerHTML="";
			checkEmailResult.classList.add("text-success");
			checkEmailResult.classList.remove("text-danger");
		}

		// 이메일 중복 체크
		const email = event.target.value; // 입력한 이메일
		const uri = `./checkEmail?email=${email}`;
		axios
		.get(uri)
		.then((response)=> {
			if (response.data === 'Y') {
				emailDubChecked = true;
				console.log(response.data);
				checkEmailResult.innerHTML="사용 가능한 이메일 입니다.";
				checkEmailResult.classList.add("text-success");
				checkEmailResult.classList.remove("text-danger");
			} else {
				emailDubChecked = false;
				console.log(response.data);
				checkEmailResult.innerHTML="이미 사용 중인 이메일 입니다.";
				checkEmailResult.classList.add("text-danger");
				checkEmailResult.classList.remove("text-success");
			}
			changeButtonState(); // 버튼의 활성화/비활성화 상태를 변경
		})
		.catch((error) => {console.log(error);} );

  }
  
});