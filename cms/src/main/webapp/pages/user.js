function caud() {
	$("#aud").window("close");
}

function ceud(){
	$("#eud").window("close");
}

function editUser() {
	var rows = $('#utGrid').datagrid('getSelections');
	if(rows.length){
		if(rows.length!=1){
			$.messager.alert("编辑用户", "编辑用户时只能选择一行！", "warning");
			$("#utGrid").datagrid("clearSelections");
		}else{
			$('#eud').form('clear');
			$('#eud').form({
				onLoadSuccess: function (data) {
					$('#eUrp').val(data.upwd)
	            }
			});
			$('#eud').form('load','/cms/getUser?uid='+rows[0].uid)
			$('#eud').dialog('open');
		}
	}else{
		$.messager.alert("编辑用户", "请选择要编辑的用户！", "warning");
	}
}

function saveEdit(){
	$.messager.progress();
	$('#editUser').form('submit');
}

function saveUser(){
	$.messager.progress();
	$('#addUser').form('submit');
}

function deleteUser() {
	var rows = $('#utGrid').datagrid('getSelections');
	if(rows.length){
		var uids = new Array();
		for(var i=0; i<rows.length; i++){
			uids.push(rows[i].uid);
		}
		$.messager.confirm('系统提示', '您确定要删除选择的用户吗?', function(r) {
			if (r) {
				$.messager.progress();
				$.ajax({
					type:"post",
					url : "/cms/deleteUser",
					cache: false,
					data : JSON.stringify(uids),
					dataType:"json",      
		            contentType:"application/json",
					success : function(data) {
						$.messager.progress('close');
						if(data.result){
							$.messager.alert("结果", data.msg, "info");
							$("#utGrid").datagrid("reload");
						}else{
							$.messager.alert("结果", data.msg, "warning");
						}
						$("#utGrid").datagrid("clearSelections");
					}
				});
			}
		});
	} else {
		$.messager.alert("删除用户", "请选择要删除的用户！", "warning");
	}
}

$(function() {
	$.extend($.fn.validatebox.defaults.rules, {    
	    equals: {    
	        validator: function(value,param){    
	            return value == $(param[0]).val();    
	        },    
	        message: '密码不一致.'
	    }    
	});
	
	$("#utGrid").datagrid({
		url : "/cms/getUsers",// 加载的URL
		idField : "uid",
		pagination : true,// 显示分页
		pageSize : 5,// 分页大小
		pageList : [ 5, 10, 15, 20 ],// 每页的个数
		fit : true,// 自动补全
		fitColumns : true,
		iconCls : "icon-save",// 图标
		title : "用户管理",
		singleSelect : false,
		rownumbers : true,
		pageNumber : 1,
		striped : true,//斑马线效果
		checkOnSelect:true,//单击行不允许选择
		loadMsg:"正在加载信息请稍候...",
		columns : [ [ // 每个列具体内容
		{
			field : 'ck',
			checkbox : true,
			width : 10
		}, {
			field : 'uname',
			title : '用户名',
			width : 100,
		},{
			field : 'upwd',
			title : '密码',
			width : 100,
		}, {
			field : 'cellphone',
			title : '手机号',
			width : 100
		}, {
			field : 'address',
			title : '地址',
			width : 100
		} ] ],
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				$("#aud").window("open");
				$('#addUser').form("clear");
			}
		}, '-', {
			iconCls : 'icon-edit',
			handler : function() {
				editUser();
			},
			text : "编辑"
		},'-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function(){
				deleteUser();
			}
		} ],
		queryParams : {
		}
	});
	
	$('#editUser').form({
		url:'/cms/updateUser',
		onSubmit: function(){   
	        if(!$('#editUser').form('validate')){
	        	$.messager.progress('close');
	        	return false;
	        }
	    },
	    success:function(data){
	    	$.messager.progress('close');
	    	var rd = JSON.parse(data);
	    	$.messager.alert("结果", rd.msg, "info");
	    	$("#utGrid").datagrid("reload");
	    	$('#eud').window('close');
		}
	});
	
	$('#addUser').form({
		url:'/cms/insertUser',
		onSubmit: function(){   
	        if(!$('#addUser').form('validate')){
	        	$.messager.progress('close');
	        	return false;
	        }
	    },
	    success:function(data){
	    	$.messager.progress('close');
	    	var rd = JSON.parse(data);
	    	$.messager.alert("结果", rd.msg, "info");
	    	$("#utGrid").datagrid("reload");
	    	$('#aud').window('close');
		}
	});
})