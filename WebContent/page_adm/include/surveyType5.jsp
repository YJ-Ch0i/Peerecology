<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<form>
	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<!-- Title -->

		문항 : <input type="text" name="que_text5" id="que_text5"
			style="width: 60%; margin: 0 0 0 5%;" class="input-hv form-control"
			maxlength="100">
     </div>
			<div class="mb-20 mb-md-10" style="margin: 5%;">
		성향 : <input type="text" id="ten_id5" name="ten_id5"
			class="input-md form-control" style="margin: 0 0 0 5%;">
	  </div>
	  <div class="mb-20 mb-md-10" style="margin: 5%;">
			정답 처리 단어 : <input type="text"
			name="answer_text" id="answer_text"
			style="width: 60%; margin: 0 0 0 5%;" class="input-hv form-control"
			maxlength="100">

	    </div>

	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<button type="button" onclick="essay_question();">문항 생성 완료</button>

	</div>

</form>
