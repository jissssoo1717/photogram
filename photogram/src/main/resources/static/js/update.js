// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault(); // 폼태그 액션 막기
	let data = $("#profileUpdate").serialize();

	console.log(data);
	
	// 데이터 응답
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data: data,
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json"
	}).done(res=>{ // HttpStatus 상태코드 200번 대
		console.log("성공", res);
		location.href=`/user/${userId}`;
	}).fail(error=>{ // HttpStatus 상태코드 200번 대가 아닐 때
	 	alert(JSON.stringify(error.responseJSON.data));
	});
}