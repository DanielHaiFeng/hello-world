function userSearch(value,name){
	var obj = {};
	obj[name] = value;
	$("#utGrid").datagrid('load', obj);
}

function addUser(){
    $('#userFrm').form("clear");
    $('#userFrm').form({
        url: rootPath + '/createUser',
        onSubmit: function () {
            if(!$('#userFrm').form('validate')){
                $.messager.progress('close');
                return false;
            }
        },
        success: function (data) {
            $.messager.progress('close');
            var result = JSON.parse(data);
            $('#userDialog').dialog('close');
            $("#utGrid").datagrid("reload");
            showInfo(result.message);
        }
    });
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
                $.messager.progress();
                $('#userFrm').form('submit');
            }
		},{
        	text: '取消',
            handler: function () {
                $('#userDialog').dialog('close');
            }
		}]
    });
}

function editUser() {
	var rows = $('#utGrid').datagrid('getChecked');
	if(rows.length){
		if(rows.length!=1){
            showInfo('编辑用户时只能选择一行');
			$("#utGrid").datagrid("clearChecked");
		}else{
            $('#userFrm').form('clear');
            $('#userDialog').dialog({
                title: '编辑用户',
                width: 400,
                height: 200,
                closed: false,
                cache: false,
                modal: true,
                buttons: [{
                    text: '保存',
                    handler: function () {
                        $.messager.progress();
                        $('#userFrm').form('submit');
                    }
                },{
                    text: '取消',
                    handler: function () {
                        $('#userDialog').dialog('close');
                    }
                }],
                onOpen: function () {
                    $('#userFrm #loginname').val(rows[0].loginname);
                    $('#userFrm #loginname').validatebox({
                        editable: false
                    });
                    $('#userFrm #uid').val(rows[0].uid);
                    $('#userFrm #name').val(rows[0].name);
                    $('#userFrm #cellphone').val(rows[0].cellphone);
                    $('#userFrm #address').val(rows[0].address);
                    $('#userFrm #remark').val(rows[0].remark);
                }
            });
            $('#userFrm').form({
                url: rootPath + '/editUser',
                onSubmit: function () {
                    if(!$('#userFrm').form('validate')){
                        $.messager.progress('close');
                        return false;
                    }
                },
                success: function (data) {
                    $.messager.progress('close');
                    var result = JSON.parse(data);
                    $('#userDialog').dialog('close');
                    $("#utGrid").datagrid("reload");
                    showInfo(result.message);
                }
            });
		}
	}else{
        showInfo('请选择要编辑的用户');
	}
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
                    type: "POST",
                    dataType: "JSON",
                    contentType:'application/json;charset=UTF-8',//关键是要加上这行
                    traditional:true,//这使json格式的字符不会被转码
                    url : rootPath+'/deleteUser',
                    cache: false,
                    data: JSON.stringify(uids),
                    success : function(data) {
                        $.messager.progress('close');
                        $("#utGrid").datagrid("reload");
                        showInfo(data.message);
                    }
                });
			}
		});
	} else {
		showInfo('请选择要删除的用户');
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
			field : 'uid',
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
})