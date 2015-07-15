<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/commons/base.jsp" %>
<script type="text/javascript" src="${ctx}/js/DataTables-1.10.2/js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/DataTables-1.10.2/css/jquery.dataTables.css">
<script src="${ctx}/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
<link rel="stylesheet" href="${ctx}/js/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui-1.10.4.custom.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<script type="text/javascript">
	var table;
	$(document).ready(function() {
		table = $('#configs').dataTable({
	    	"processing": true,
	        "serverSide": true,
	        "ajax":{
	        	"url": __ctx+"/SysConfig/listAjax.do",
	        	"type": "POST"
	        	},
	      	"bSort":false,
	      	"sDom": '<"top">rt<"bottom"lp><i><"clear">'
	    } );
		
		
		
		///search record (press enter)
		 $('input.column_filter').bind( 'keypress', function (even) {
		 	if(event.keyCode == "13")    
            {
		 		 $('input.column_filter').each(function(){
					 var columnIdx = $(this).attr("data-column");
			 		 table.api()
			            .column( columnIdx )
			            .search( $(this).val() );
				 });
				 table.api().ajax.reload();
            }
	    } );
		
		////search record (click button)
		$('[name="btn-search"]').click(function(){
			 $('input.column_filter').each(function(){
				 var columnIdx = $(this).attr("data-column");
		 		 table.api()
		            .column( columnIdx )
		            .search( $(this).val() );
			 });
			 table.api().ajax.reload();
		});
		
		 ///edit record
	    $("body").delegate("#configs tbody [name=edit]",'click', function () {
			var _this = $(this);
	         window.location.href=__ctx+"/SysConfig/edit.do?configId="+_this.attr("configId");
	     });
	    
	    ///Delete record
	    $("body").delegate("#configs tbody [name=delete]",'click', function () {
			var _this = $(this);
	        var configId = _this.attr("configId");
	        $( "#dialog" ).dialog({
	            resizable: false,
	            modal: true,
	            buttons:[{
	            	 	text:"Yes",
	            	 	click:function() {
	            	 		deleteRecord(configId);
	               		 	$( this ).dialog( "close" );
	            	  	}
	            	},{
	            		 text:"No",
		            	 click:function() {
		               		 $( this ).dialog( "close" );
		            	  }
	             	}]
	          });
	     });
	    
	    $("[name=btn-add]").click(function(){
	    	window.location.href=__ctx+"/SysConfig/edit.do";
	    });
	} );
	
	function nameSearchValue(){
		return $("#col0_filter").val();
	};
	
	function filterColumn ( i ) {
	    $('#configs').DataTable().column( i ).search( 
	        $('#col'+i+'_filter').val()
	    ).draw();
	};
	
	
	function deleteRecord(id){
		$.post(__ctx+"/SysConfig/delete.do",{
			id:id
		},function(result){
			table.api().ajax.reload();
			showLeftFloatMessage(result);
		});
	};
	

	
	function showLeftFloatMessage(responseText){
		var msgArea = $(".panel-message.panel-message-left-float");
		if(msgArea.length==0){
 			msgArea=$('<div class="panel-message panel-message-left-float"></div>');
 			msgArea.appendTo(".panel-frame");
 		}
		
		var construct_message = function(message,cssClass){
		   	var msgdiv = $('<div></div>').addClass(cssClass).text(message).appendTo(msgArea);
		   	msgdiv.bind("dblclick",function(){
		   		$(this).fadeOut(1000,function(){
			   		$(this).remove();
		   		});
		   	});
		   	setTimeout(function(){
		   		msgdiv.fadeOut(1000,function(){
			   		$(this).remove();
		   		});
		   	},5000);
		}
		
		for(var key in responseText.successes){
		   	var message = responseText.successes[key];
		   	construct_message(message,"success");
		}
	    for(var key in responseText.infos){
	    	var message = responseText.infos[key];
	    	construct_message(message,"info");
	    }
	    for(var key in responseText.warns){
	    	var message = responseText.warns[key];
	    	construct_message(message,"warning");
	    }
	    for(var key in responseText.errors){
	    	var message = responseText.errors[key];
	    	construct_message(message,"error");
	    }
	}
</script>

</head>
<body>
	<div class="panel-frame">
		<div class="panel-toolbar">
			<ul class="button-group">
			    <li><a href="#" name="btn-add" class="button primary pill">Add</a></li>
			    <li><a href="#" name="btn-search" class="button pill">Search</a></li>
			</ul>
		</div>
		<div style="clear: both;"></div>
		<div class="panel-main">
			
			<table>
				<tr>
					<th>name:</th>
					<td ><input class="column_filter" name="name" id="col0_filter" data-column="0"/></td>
					<th>value:</th>
					<td><input class="column_filter" name="value" id="col1_filter" data-column="1"/></td>
				</tr>
				
			</table>
		
			<table id="configs" class="display" cellspacing="0" width="100%">
				<thead>
					 <tr>
			            <th>name</th>
			            <th>value</th>
			            <th>manage</th>
			        </tr>
				</thead>
<!-- 				<tfoot> -->
<!-- 					 <tr> -->
<!-- 			            <th>name</th> -->
<!-- 			            <th>value</th> -->
<!-- 			            <th>manage</th> -->
<!-- 			        </tr> -->
<!-- 				</tfoot> -->
			</table>
		</div>
	</div>
	
	<div style="display: none">
	    <div id="dialog" title="delete confirm">
		  <p>
		  	Are you sure to delete this record?
		  </p>
		</div>
		
		<div class="panel-message-container" style="display: none">
			<div class="panel-message dialog-error">
				<div class="content error"></div>
			</div>
			<div class="panel-message dialog-success">
				<div class="content success"></div>
			</div>
		</div>
	</div>    
	
<!-- 	<div style="position: absolute;position: 0"></div> -->
	
<!-- 	<div class="panel-message-container" style="width: 15%;position:absolute;top: 11%;right: 2px"> -->
<!-- 			<div class="panel-message dialog-error"> -->
<!-- 				<div class="content error"> -->
<!-- 					错了 -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="panel-message dialog-success"> -->
<!-- 				<div class="content success"> -->
<!-- 					对了 -->
<!-- 					顺茜大本营 械顺茜大本营 械顺茜大本营 械顺茜大本营 械顺茜大本营 械顺茜大本营 械顺茜大本营 械 -->
<!-- 					模压  -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 	</div> -->
</body>
</html>