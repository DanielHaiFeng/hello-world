function getChildMenu(mid, text){
	
	$("#menuContGrid").datagrid({
		url : "/cms/getMenuList",// 加载的URL
		idField : "mid",
		pagination : true,// 显示分页
		pageSize : 5,// 分页大小
		pageList : [ 5, 10, 15, 20 ],// 每页的个数
		fit : true,// 自动补全
		fitColumns : true,
		iconCls : "icon-save",// 图标
		title : text,
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
			field : 'mname',
			title : '菜单名',
			width : 100,
		},{
			field : 'micon',
			title : '菜单图标',
			width : 100,
		}, {
			field : 'mlevel',
			title : '菜单级别',
			width : 100
		}, {
			field : 'url',
			title : '菜单URL',
			width : 100
		} ] ],
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
			}
		}, '-', {
			iconCls : 'icon-edit',
			handler : function() {
			},
			text : "编辑"
		},'-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function(){
			}
		} ],
		queryParams : {
			"mid": mid
		}
	});
}

$(function() {
	var bfSn = undefined;
	$('#tt').tree({
		checkbox : false,
		url : '/cms/getMenuTree?id=',
		onBeforeSelect: function(node){
			bfSn = $('#tt').tree('getSelected');
		},
		onClick: function(node) {
			if(node.attributes.mlevel<=1){
				getChildMenu(node.id, node.text);
			} else {
				$.messager.alert("温馨提示", "只有级别为<=1的菜单才有子菜单！", "info");
				$('#tt').tree('select',bfSn.target);
			}
		}
	});
})