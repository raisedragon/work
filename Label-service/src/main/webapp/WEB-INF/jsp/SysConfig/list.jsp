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
	        	}
	    } );
		
		
		
		
		 $('input.column_filter').bind( 'keypress', function (even) {
		 	if(event.keyCode == "13")    
            {
		 		 table.api()
		            .column( 0 )
		            .search( $(this).val() ).draw();
            }
	    } );
		

	    $("body").delegate("#configs tbody [configId]",'click', function () {
			var _this = $(this);
	        // alert(_this.attr("configId"));
	         window.location.href=__ctx+"/SysConfig/edit.do?configId="+_this.attr("configId");
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
</script>

</head>
<body>
	<div class="panel-frame">
		<div class="panel-toolbar">
			<a href="#" name="btn-add" class="button  pill">Add</a>
		</div>
		<div style="clear: both"></div>
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
				<tfoot>
					 <tr>
			            <th>name</th>
			            <th>value</th>
			            <th>manage</th>
			        </tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>