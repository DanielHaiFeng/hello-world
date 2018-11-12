var menus;

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
    $.messager.alert(title, msgString, msgType);
}

//获取左侧导航的图标
function getIcon(menuid){
    var icon = 'icon ';
    $.each(menus, function(i, n) {
        $.each(n.menus, function(j, o) {
            if(o.menuid==menuid){
                icon += o.icon;
            }
        })
    })
    return icon;
}

function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + rootPath + url +'" style="width:100%;height:99.5%;"></iframe>';
    return s;
}

function tabClose() {
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function(){
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close',subtitle);
    })
}

function addTab(subtitle,url,icon){
    if(!$('#tabs').tabs('exists',subtitle)){
        $('#tabs').tabs('add',{
            title:subtitle,
            content:createFrame(url),
            closable:true,
            icon:icon
        });
    }else{
        $('#tabs').tabs('select',subtitle);
    }
    tabClose();
}

function initAccordion(menus) {
    $("#ngm").accordion({animate:true});
    $.each(menus, function(i, n) {
        var menulist ='';
        menulist +='<ul>';
        $.each(n.menus, function(j, o) {
            if(o.visible){
                menulist += '<li><div><a ref="'+o.menuid+'" href="#" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
            }
        })
        if(menulist.length>4){
            menulist += '</ul>';
            $('#ngm').accordion('add', {
                title: n.menuname,
                content: menulist,
                iconCls: n.icon
            });
        }
    });

    $('.easyui-accordion li a').click(function(){
        var tabTitle = $(this).children('.nav').text();
        var url = $(this).attr("rel");
        var menuid = $(this).attr("ref");
        var icon = getIcon(menuid,icon);
        addTab(tabTitle,url,icon);
        $('.easyui-accordion li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function(){
        $(this).parent().addClass("hover");
    },function(){
        $(this).parent().removeClass("hover");
    });

    //选中第一个
    var panels = $('#ngm').accordion('panels');
    var t = panels[0].panel('options').title;
    $('#ngm').accordion('select', t);
}

//修改密码
function serverLogin() {
    var $newpass = $('#txtNewPass');
    var $rePass = $('#txtRePass');

    if ($newpass.val() == '') {
        msgShow('系统提示', '请输入密码！', 'warning');
        return false;
    }
    if ($rePass.val() == '') {
        msgShow('系统提示', '请在一次输入密码！', 'warning');
        return false;
    }

    if ($newpass.val() != $rePass.val()) {
        msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
        return false;
    }

    $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
        msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
        $newpass.val('');
        $rePass.val('');
    })
}

function closePwd() {
    $('#udpww').window('close');
}

$(function(){
    var userName = window.localStorage.getItem('loginUser');
    $('#uname').text(userName);
    $.ajax({
        type: "POST",
        dataType: "JSON",
        contentType:'application/json;charset=UTF-8',//关键是要加上这行
        traditional:true,//这使json格式的字符不会被转码
        url : rootPath+'/getAccordion',
        cache: false,
        data: JSON.stringify({userName : userName}),
        success : function(data) {
            menus = data;
            initAccordion(data);
        }
    });

    $('#udpw').click(function() {
        var $newpass = $('#txtNewPass').val("");
        var $rePass = $('#txtRePass').val("");
        $('#udpww').window('open');
    })

    $('#btnEp').click(function() {
        serverLogin();
    })

    $('#btnCancel').click(function(){
        closePwd();
    })

    $('#qt').click(function() {
        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
            if (r) {
                window.localStorage.removeItem('loginUser');
                window.location.href=rootPath+'/logout';
            }
        });
    })
});