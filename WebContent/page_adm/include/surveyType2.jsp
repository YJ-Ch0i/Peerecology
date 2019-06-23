<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form>
	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<!-- Title -->

		문항 : <input type="text" name="que_text2" id="que_text2"
			style="width: 60%; margin: 0 0 0 5%;" class="input-hv form-control"
			maxlength="100">
	</div>

	<div class="mb-20 mb-md-10" style="margin: 5%;">
		성향 : <input type="text" id="ten_id2" name="ten_id1"
			class="input-md form-control" style="margin: 0 0 0 5%;">
	</div>
	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<button type="button" onclick="add_example();">선택지 추가</button>
		</br> 1번 선택지 : <input type="text" name="example1_1" id="example1_1"
			value="">

		<div id="field_example"></div>
		</br>
		</br>
		<button type="button" onclick="window.location.reload()">리셋</button>

	</div>

	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<button type="button" onclick="multiple_choice();">문항 생성 완료</button>

	</div>

</form>
