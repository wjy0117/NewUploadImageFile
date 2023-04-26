<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>File Form Page</title>
<style type="text/css">
.image {
	display: block;
	width: 100%;
}
</style>
</head>
<body>
	<h1>File Form Page</h1>
	<hr>
	<!-- 이미지 등록하는 서블릿 따로 생성하기. -->
	<!-- 리뷰 이미지 저장 리뷰dB등록(+이미지) -->
	<!-- 파일 업로드를 위해서는 form에 속성을 enctype="multipart/form-data" 라고 부여해야지만 가능하다. -->
	<!-- MultipartRequest -->


	<form method="post" id="img_form" action="UploadPage" enctype="multipart/form-data">
		파일 : <input type="file" id="multi_img" name="file[]"
			onchange="readMultipleImage()" multiple> text : <input
			type="text" id="id_text" name="name_text" value="text">
			<input type="button" id="submitButton" value="File Upload" onclick="submitbtn()">
	</form>
	<script type="text/javascript">
		function readMultipleImage() {
			var files_name = "";
			var multi = document.getElementById("multi_img").files;
			for (var i = 0; i < multi.length; i++) {
				console.log(multi[i].name);
				files_name = files_name + "," + multi[i].name;
			}
			document.getElementById("id_text").value = files_name;
		}

		function submitbtn() {
			document.getElementById("img_form").submit();
			//document.getElementById("test").submit();
		}
	</script>
</body>
</html>