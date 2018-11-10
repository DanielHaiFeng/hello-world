function userSearch(value,name){
	var obj = {};
	obj[name] = value;
	$("#utGrid").datagrid('load', obj);
}

function caud() {
	$("#aud").window("close");
}

function ceud(){
	$("#eud").window("close");
}

function addUser(){
    $('#userDialog').dialog({
        title: '新增用户',
        width: 400,
        height: 200,
        closed: false,
        cache: false,
        modal: true,
        buttons: [{
        	text: '保存',
            handler: function () {
                $('#userFrm').form('submit');
            }
		},{
        	text: '取消',
            handler: function () {
                $('#userDialog').dialog('close');
            }
		}]
    });
    $('#userFrm').form({
        url: rootPath + '/createUser',
		onSubmit: function () {
            if(!$('#userFrm').form('validate')){
				return false;
            }
        },
        success: function (data) {
            var result = JSON.parse(data);
            $('#userDialog').dialog('close');
            $("#utGrid").datagrid("reload");
            $.messager.alert("提示", result.message, "info");
        }
	});
	$('#userFrm').form("clear");
}

function editUser() {
	var rows = $('#utGrid').datagrid('getChecked');
	if(rows.length){
		if(rows.length!=1){
			$.messager.alert("编辑用户", "编辑用户时只能选择一行！", "warning");
			$("#utGrid").datagrid("clearChecked");
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
	var rows = $('#utGrid').datagrid('getChecked');
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
						if(data.result==0){
							$.messager.alert("结果", data.msg, "info");
							var parent$ = self.parent.$;      //找到父级DOM  
				            parent$('#tabs').tabs('close','权限管理');
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
		url : rootPath+'/getUsers',// 加载的URL
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
			field: 'loginname',
			title: '登录名',
			width: 100
		}, {
			field: 'upwd',
			hidden: true
		}, {
			field: 'name',
			title: '姓名',
			width: 100,
		}, {
			field : 'cellphone',
			title : '手机号',
			width : 100
		}, {
			field : 'address',
			title : '地址',
			width : 100
		}, {
			field: 'remark',
			title: '备注',
			width: 100
		}]],
		toolbar : '#userToolBar',
		queryParams : {
		}
	});

    $('#addUser').form({
        url:rootPath+'/insertUser',
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
            if(rd.result!=1){
                $("#utGrid").datagrid("reload");
                var parent$ = self.parent.$;      //找到父级DOM
                parent$('#tabs').tabs('close','权限管理');
                $('#aud').window('close');
            }
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
	    	if(rd.result!=1){
	    		$("#utGrid").datagrid("reload");
		    	$('#eud').window('close');
	    	}
		}
	});
})