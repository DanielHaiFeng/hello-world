function userAuthorize() {
	var rows = $('#uaGrid').datagrid('getChecked');
	if (rows.length) {
		if (rows.length != 1) {
			showInfo("授权时只能选择一个用户！");
			$("#uaGrid").datagrid("clearChecked");
		} else {
			var uname = rows[0].uname;
			$('#uatree').tree({
				checkbox : true,
				url : rootPath + '/getAuthorizeMenuTree',
				queryParams : {
					'uid' : rows[0].uid
				},
				onLoadSuccess : function(node, data) {
					$('#uaFrm #uid').val(rows[0].uid);
				},
				onBeforeCheck : function(node, checked) {
					if (uname != 'super') {
						if(node.text=='权限管理' && checked){
							$('#uatree').tree('uncheck',node.target);
							showInfo("只有super用户才能被授予[权限管理]菜单权限！");
							return false;
						}
					}
				}
			});
            $('#uaDialog').dialog({
                title: '菜单授权',
                width: 200,
                height: 300,
                closed: false,
                cache: false,
                modal: true,
                buttons : [ {
                    text : '授权',
                    handler : function() {
                        $.messager.progress();
                        $('#uaFrm').form('submit');
                    },
                }, {
                    text : '取消',
                    handler : function() {
                        $('#uaDialog').dialog('close');
                    },
                } ]
            });

            $('#uaFrm').form({
                url : rootPath + '/authorizeMenu',
                onSubmit : function() {
                    var nodes = $('#uatree').tree('getChecked');
                    if (!nodes.length) {
                        $.messager.progress('close');
                    	showInfo("请选择要授权的菜单！");
                        return false;
                    } else {
                        var mids = new Array();
                        for (var i = 0; i < nodes.length; i++) {
                        	if(uname!='super' && nodes[i].text=='权限管理'){
                                $.messager.progress('close');
                        		showInfo('只有super用户才能被授予[权限管理]菜单权限！');
                        		return false;
							}
                            mids.push(nodes[i].id);
                        }
                        $('#uaFrm #mids').val(mids.join());
                    }
                },
                success : function(data) {
                    $.messager.progress('close');
                    var rd = JSON.parse(data);
                    showInfo(rd.msg);
                    if (rd.result != 1) {
                        $('#uaFrm').form('clear');
                        $('#uaDialog').dialog('close');
                    }
                }
            });
		}
	} else {
		showInfo("请选择要授权的用户！")
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