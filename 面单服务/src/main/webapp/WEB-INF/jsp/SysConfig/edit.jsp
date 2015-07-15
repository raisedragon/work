<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/commons/base.jsp" %>
<script type="text/javascript" src="${ctx}/js/jquery.form/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation-1.13.1/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">


<script type="text/javascript">
	//prepare the form when the DOM is ready 
	$(document).ready(function() { 
	    var options = { 
	        beforeSubmit:  showRequest,  // pre-submit callback 
	        success:       showResponse,  // post-submit callback 
	 		type:"post",
	 		dataType:"json"
	        // other available options: 
	        //url:       url         // override for form's 'action' attribute 
	        //type:      type        // 'get' or 'post', override for form's 'method' attribute 
	        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
	        //clearForm: true        // clear all form fields after successful submit 
	        //resetForm: true        // reset the form after successful submit 
	 
	        // $.ajax options can be used here too, for example: 
	        //timeout:   3000 
	    }; 
	    
	    
		var rules = {
					 name: {
						required: true
					 }
			 	};
		var messages= {
			 		name:{
			 			required: "The field is required."
			 		}
			 };
	    $('#edit-form').validate({
			rules:rules,
			messages:messages,
			submitHandler: function(form) {
				$(".panel-message").remove();
				$(form).ajaxSubmit(options); 
			}
		});
	    
	    //save edit.
	    //first invoke jquery.validate to validate , then jquery.form to ajax submit
		$("[name=btn-save]").click(function(){
			$('#edit-form').submit();
		}); 
		
		//back to list
		$("[name=btn-back]").click(function(){
			window.location.href=__ctx+"/SysConfig/list.do";
		}); 
	   
	});
	// pre-submit callback 
	function showRequest(formData, jqForm, options) { 
	    // formData is an array; here we use $.param to convert it to a string to display it 
	    // but the form plugin does this for you automatically when it submits the data 
// 	    var queryString = $.param(formData); 
	 
	    // jqForm is a jQuery object encapsulating the form element.  To access the 
	    // DOM element for the form do this: 
	    // var formElement = jqForm[0]; 
	 
// 	    alert('About to submit: \n\n' + queryString); 
	 
	    // here we could return false to prevent the form from being submitted; 
	    // returning anything other than false will allow the form submit to continue 
	    return true; 
	} 
	 
	// post-submit callback 
	function showResponse(responseText, statusText, xhr, $form)  { 
	    // for normal html responses, the first argument to the success callback 
	    // is the XMLHttpRequest object's responseText property 
	 
	    // if the ajaxForm method was passed an Options Object with the dataType 
	    // property set to 'xml' then the first argument to the success callback 
	    // is the XMLHttpRequest object's responseXML property 
	 
	    // if the ajaxForm method was passed an Options Object with the dataType 
	    // property set to 'json' then the first argument to the success callback 
	    // is the json data object returned by the server 
	    
		var msgArea = $(".panel-message");
		if(msgArea.length==0){
 			msgArea=$('<div class="panel-message"></div>');
 			msgArea.appendTo(".panel-frame");
 		}
		for(var key in responseText.successes){
		   	var message = responseText.successes[key];
		   	$('<div class="success"></div>').text(message).appendTo(msgArea);
		}
	    for(var key in responseText.infos){
	    	var message = responseText.infos[key];
	    	$('<div class="info"></div>').text(message).appendTo(msgArea);
	    }
	    for(var key in responseText.warns){
	    	var message = responseText.warns[key];
	    	$('<div class="warning"></div>').text(message).appendTo(msgArea);
	    }
	    for(var key in responseText.errors){
	    	var message = responseText.errors[key];
	    	$('<div class="error"></div>').text(message).appendTo(msgArea);
	    }
 		
	};
	
	
</script>
<style type="text/css">
</style>
</head>
<body>
	<div class="panel-frame">
		<div class="panel-toolbar">
			<ul class="button-group">
			    <li><a href="#" name="btn-save" class="button primary pill">Save</a></li>
			    <li><a href="#" name="btn-back" class="button primary pill">Back</a></li>
			</ul>
		</div>
		<div style="clear: both"></div>
		<div class="panel-main">
			<form id="edit-form" action="save.do" >
			
					<table  id="config" class="table-form" border="0" cellspacing="0" cellpadding="0">
						<tr style="display: none">
							<th>Id:</th>
							<td><input name="id" value="${sysConfig.id}"/></td>
						</tr>
						<tr>
							<th>Name:</th>
							<td><input name="name" value="${sysConfig.name}" /></td>
						</tr>
						<tr>
							<th>Value:</th>
							<td><input name="value" value="${sysConfig.value}"/></td>
						</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>