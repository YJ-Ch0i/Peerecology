<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form>
	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<!-- Title -->

		문항 : <input type="text" name="que_text4" id="que_text4"
			style="width: 60%; margin: 0 0 0 5%;" class="input-hv form-control"
			maxlength="100">
	</div>

	<div class="mb-20 mb-md-10" style="margin: 5%;">
		성향 : <input type="text" id="ten_id4" name="ten_id3"
			class="input-md form-control" style="margin: 0 0 0 5%;">
	</div>
	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<button type="button" onclick="add_exampleChecked();">선택지 추가</button>
		</br> 1번 선택지 : <input type="text" name="example2_1" id="example2_1"
			value=""> <input type="radio" name="example_answer"
			id="example_answer" value="1">

		<div id="field_example2"></div>
		</br>
		</br>
		<button type="button" onclick="window.location.reload()">리셋</button>

	</div>

	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<button type="button" onclick="multiple_answer_choice();">문항
			생성 완료</button>

	</div>

</form>
