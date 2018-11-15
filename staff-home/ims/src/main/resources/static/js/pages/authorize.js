function userAuthorize() {
	var rows = $('#uaGrid').datagrid('getChecked');
	if (rows.length) {
		if (rows.length != 1) {
			$.messager.alert("用户授权", "授权时只能选择一个用户！", "warning");
			$("#uaGrid").datagrid("clearChecked");
		} else {
			var uname = rows[0].uname;
			$('#uatree').tree({
				checkbox : true,
				url : '/cms/getAuthorizeMenuTree',
				queryParams : {
					'uid' : rows[0].uid
				},
				onLoadSuccess : function(node, data) {
					$('#uaFrm #uid').val(rows[0].uid);
				},
				onBeforeCheck : function(node, checked) {
					if (uname != 'super') {
						if(node.text=='权限管理'){
							$('#uatree').tree('uncheck',node.target);
							$.messager.alert("用户授权", "只有super用户才能被授予[权限管理]菜单权限！", "warning");
							return false;
						}
					}
				},
				onCheck : function(node, checked){
					if(uname == 'super'){
						$('#uatree').tree('check',node.target);
					}
				}
			});
            $('#uaDialog').dialog({
                buttons : [ {
                    text : '授权',
                    handler : function() {
                        $.messager.progress();
                        $('#uaFrm').form('submit');
                    },
                    iconCls : 'icon-save'
                }, {
                    text : '取消',
                    handler : function() {
                        $('#uaDialog').dialog('close');
                    },
                    iconCls : 'icon-cancel'
                } ]
            });

            $('#uaFrm').form({
                url : '/cms/authorizeMenu',
                onSubmit : function() {
                    var nodes = $('#uatree').tree('getChecked');
                    if (!nodes.length) {
                        $.messager.alert("用户授权", "请选择要授权的菜单！", "warning");
                        return false;
                    } else {
                        var mids = new Array();
                        for (var i = 0; i < nodes.length; i++) {
                            mids.push(nodes[i].id);
                        }
                        $('#uaFrm #mids').val(mids.join());
                    }
                },
                success : function(data) {
                    $.messager.progress('close');
                    var rd = JSON.parse(data);
                    $.messager.alert("结果", rd.msg, "info");
                    if (rd.result != 1) {
                        $('#uaFrm').form('clear');
                        $('#uaDialog').dialog('close');
                    }
                }
            });
		}
	} else {
		$.messager.alert("用户授权", "请选择要授权的用户！", "warning");
	}
}

function uaSearch(value, name) {
	var obj = {};
	obj[name] = value;
	$("#uaGrid").datagrid('load', obj);
}

$(function() {

	$("#uaGrid").datagrid({
		url : rootPath+'/getUsers',// 加载的URL
		idField : "uid",
		pagination : true,// 显示分页
		pageSize : 5,// 分页大小
		pageList : [ 5, 10, 15, 20 ],// 每页的个数
		fit : true,// 自动补全
		fitColumns : true,
		iconCls : "icon-save",// 图标
		title : "用户授权",
		singleSelect : false,
		rownumbers : true,
		pageNumber : 1,
		striped : true,// 斑马线效果
		checkOnSelect : true,// 单击行不允许选择
		loadMsg : "正在加载信息请稍候...",
		columns : [ [ // 每个列具体内容
		{
			field : 'uid',
			checkbox : true,
			width : 10
		}, {
			field : 'loginname',
			title : '登录名',
			width : 100
		}, {
			field : 'upwd',
			hidden: true
		}, {
			field : 'name',
            title : '姓名',
            width : 100
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
        } ] ],
		toolbar : '#uaToolBar',
		queryParams : {}
	});

})