<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form>
	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<!-- Title -->

		문항 : <input type="text" name="que_text3" id="que_text3"
			style="width: 60%; margin: 0 0 0 5%;" class="input-hv form-control"
			maxlength="100">
	</div>

	<div class="mb-20 mb-md-10" style="margin: 5%;">
		성향 : <input type="text" id="ten_id3" name="ten_id2"
			class="input-md form-control" style="margin: 0 0 0 5%;">
	</div>

	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<input type="text" id="radio_example1" name="radio_example1"
			placeholder="전혀 그렇지 않다.">
		<div style="display: inline-block" id="field_radio">
			<input type="radio" checked="">
		</div>
		<input type="text" id="radio_example2" name="radio_example2"
			placeholder="매우 그렇다.">
	</div>
	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<button type="button" onclick="add_radiobox();">RadioBox 추가</button>
		</br>
		</br>
		<button type="button" onclick="window.location.reload()">리셋</button>
	</div>
	<div class="mb-20 mb-md-10" style="margin: 5%;">
		<button type="button" onclick="radio_choice();">문항 생성 완료</button>

	</div>

</form>
