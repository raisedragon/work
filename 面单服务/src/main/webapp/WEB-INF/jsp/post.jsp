<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("[name=commit]").click(function(){
			var mydata = $("[name=input]").val();
			 $.ajax({  
			        type : 'POST',  
			        contentType : 'application/json',  
			        url : '/label/getLabel.do',
			        data : mydata
			    }).done(function(data){
			    	$("[name=output]").text(data);
			    });  
		});
	});
</script>
</head>
<body>
	<textarea name="input" rows="20" cols="100"></textarea>
	<br/>
	<button name="commit">commit</button>
	<br/>
	<p name="output"></p>
</body>
</html>