function deleteMenu() {
	var rows = $('#menuContGrid').datagrid('getChecked');
	if(rows.length){
		debugger;
		var mids = new Array();
		for(var i=0; i<rows.length; i++){
			mids.push(rows[i].mid);
		}
		$.messager.confirm('系统提示', '您确定要删除选择的菜单吗?', function(r) {
			if (r) {
				$.messager.progress();
				$.ajax({
					type:"post",
					url : "/cms/deleteMenu",
					cache: false,
					data : JSON.stringify(mids),
					dataType:"json",      
		            contentType:"application/json",
					success : function(data) {
						$.messager.progress('close');
						if(data.result==0){
							$.messager.alert("结果", data.msg, "info");
							var sn = $('#tt').tree('getSelected');
							$("#menuContGrid").datagrid("load",{
								mid:sn.id
							});
							$.each(mids, function(i, n){
								var node = $('#tt').tree('find', n);
								$('#tt').tree('remove', node.target);
							});
						}else{
							$.messager.alert("结果", data.msg, "warning");
						}
						$("#menuContGrid").datagrid("clearChecked");
					}
				});
			}
		});
	} else {
		$.messager.alert("删除用户", "请选择要删除的菜单！", "warning");
	}
}

function editMenu() {
	var rows = $('#menuContGrid').datagrid('getChecked');
	if(rows.length){
		if(rows.length!=1){
			$.messager.alert("编辑菜单", "编辑菜单时只能选择一行！", "warning");
			$("#menuContGrid").datagrid("clearChecked");
		}else{
			$('#editMenuFrm').form('clear');
			$('#editMenuFrm').form('load','/cms/getMenu?mid='+rows[0].mid)
			$('#editMenuDialog').dialog('open');
		}
	}else{
		$.messager.alert("编辑菜单", "请选择要编辑的菜单！", "warning");
	}
}

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
				$('#addMenuDialog').dialog('open');
				$('#addMenuFrm').form("clear");
				var dd = $('#tt').tree('getSelected');
				$('#apid').val(dd.id);
				var nle = dd.attributes.mlevel + 1;
				$('#newMl').val(nle);
				if(nle<2){
					$('#newUrl').validatebox({    
					    required: false,
					    readonly: true
					}); 
				}else{
					$('#newUrl').validatebox({    
					    required: true,
					    readonly: false
					}); 
				}
			}
		}, '-', {
			iconCls : 'icon-edit',
			handler : function() {
				editMenu();
			},
			text : "编辑"
		},'-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function(){
				deleteMenu();
			}
		} ],
		queryParams : {
			"mid": mid
		}
	});
	
	$("#menuContGrid").datagrid("clearChecked");
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
	
	$('#addMenuDialog').dialog({
		buttons:[{
			text:'保存',
			handler:function(){
				$.messager.progress();
				$('#addMenuFrm').form('submit');
			},
			iconCls:'icon-save'
		},{
			text:'取消',
			handler:function(){
				$('#addMenuDialog').dialog('close');
			},
			iconCls:'icon-cancel'
		}]
	});
	
	$('#editMenuDialog').dialog({
		buttons:[{
			text:'保存',
			handler:function(){
				$.messager.progress();
				$('#editMenuFrm').form('submit');
			},
			iconCls:'icon-save'
		},{
			text:'取消',
			handler:function(){
				$('#editMenuDialog').dialog('close');
			},
			iconCls:'icon-cancel'
		}]
	});
	
	$('#addMenuFrm').form({
		url:'/cms/insertMenu',
		onSubmit: function(){   
	        if(!$('#addMenuFrm').form('validate')){
	        	$.messager.progress('close');
	        	return false;
	        }
	    },
	    success:function(data){
	    	$.messager.progress('close');
	    	var rd = JSON.parse(data);
	    	$.messager.alert("结果", rd.msg, "info");
	    	if(rd.result!=1){
	    		var sn = $('#tt').tree('getSelected');
		    	$("#menuContGrid").datagrid("load", {
		    		'mid':sn.id
		    	});
		    	$('#addMenuDialog').dialog('close');
		    	
		    	if(rd.result==0){
		    		var obj = rd.obj;
		    		$('#tt').tree('append', {
			    		parent: sn.target,
			    		data: [{
			    			id: obj.id,
			    			text: obj.text,
			    			state: obj.state,
			    			iconCls: obj.iconCls,
			    			attributes: {
			    				mlevel:obj.attributes.mlevel
			    			}
			    		}]
			    	});
		    	}
	    	}
		}
	});
	
	$('#editMenuFrm').form({
		url:'/cms/updateMenu',
		onSubmit: function(){   
	        if(!$('#editMenuFrm').form('validate')){
	        	$.messager.progress('close');
	        	return false;
	        }
	    },
	    onLoadSuccess: function(data){
	    	if(data.mlevel<2){
				$('#edUrl').validatebox({    
				    required: false,
				    readonly: true
				}); 
			}else{
				$('#edUrl').validatebox({
				    required: true,
				    readonly: false
				}); 
			}
	    },
	    success:function(data){
	    	$.messager.progress('close');
	    	var rd = JSON.parse(data);
	    	$.messager.alert("结果", rd.msg, "info");
	    	if(rd.result!=1){
	    		$("#menuContGrid").datagrid("reload");
		    	$('#editMenuDialog').dialog('close');
	    	}
		}
	});
})