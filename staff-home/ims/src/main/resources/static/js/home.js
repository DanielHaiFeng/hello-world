var menus;

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
                menulist += '<li><div><a ref="'+o.menuid+'" href="javascript:void();" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
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

$(function(){
    $.extend($.fn.validatebox.defaults.rules, {
        equals: {
            validator: function(value,param){
                return value == $(param[0]).val();
            },
            message: '密码不一致.'
        }
    });

    var userName = window.localStorage.getItem('loginUser');
    var loginPwd = null;
    var isPwdChange = false;
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

    $('#uname').click(function() {
        $('#uiFrm').form('clear');
        $('#userInfo').dialog({
            title: '个人信息',
            width: 240,
            height: 170,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    $.messager.progress();
                    $('#uiFrm').form('submit');
                }
            },{
                text: '取消',
                handler: function () {
                    $('#userInfo').dialog('close');
                }
            }],
            onOpen: function () {
                $.ajax({
                    type: "POST",
                    dataType: "JSON",
                    contentType:'application/json;charset=UTF-8',//关键是要加上这行
                    traditional:true,//这使json格式的字符不会被转码
                    url : rootPath+'/getPersonInfo',
                    cache: false,
                    data: userName,
                    success : function(data) {
                        $.messager.progress('close');
                        if (data.success) {
                            var perInfo = JSON.parse(data.message);
                            var base64 = new Base64();
                            var oriPwd = base64.decode(perInfo.upwd);
                            $('#userInfo #uid').val(perInfo.uid);
                            $('#userInfo #name').val(perInfo.name);
                            $('#userInfo #cellphone').val(perInfo.cellphone);
                            $('#userInfo #address').val(perInfo.address);
                            $('#userInfo #pwd').val(oriPwd);
                            $('#userInfo #confirmPwd').val(oriPwd);
                            loginPwd = oriPwd;
                        } else {
                            $('#userInfo').dialog('close');
                            showInfo(data.message);
                        }
                    }
                });
            }
        });
        $('#uiFrm').form({
            url: rootPath + '/savePersonInfo',
            onSubmit: function () {
                if(!$('#uiFrm').form('validate')){
                    $.messager.progress('close');
                    return false;
                }
                var base64 = new Base64();
                var opwd = $('#userInfo #pwd').val();
                if (opwd != loginPwd) {
                    isPwdChange = true;
                }
                var epwd = base64.encode(opwd);
                $('#userInfo #pwd').val(epwd);
                $('#userInfo #oriPwd').val(epwd);
            },
            success: function (data) {
                $.messager.progress('close');
                var result = JSON.parse(data);
                $('#userInfo').dialog('close');
                if (result.success && isPwdChange) {
                    $.messager.confirm('系统提示', '密码已修改，需要重新登录', function(r) {
                        isPwdChange = false;
                        loginPwd = null;
                        window.localStorage.removeItem('loginUser');
                        window.location.href=rootPath+'/logout';
                    })
                } else {
                    showInfo(result.message);
                }
            }
        });
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