function login() {
    if(!frm.userName.value){
        $.messager.show({
            title:'提示',
            msg:'用户名不能为空',
            timeout:1000,
            showType:'slide'
        });
        return false;
    }
    if(!frm.password.value){
        $.messager.show({
            title:'提示',
            msg:'密码不能为空',
            timeout:1000,
            showType:'slide'
        });
        return false;
    }
    var rootPath = getRootPath();
    frm.action= rootPath + "/login";
    frm.submit();
}

$(function () {
    $('#loginFrm').form({
        url: rootPath + '/login',
        onSubmit: function () {
            var uName = $('#loginFrm').find('#userName').val();
            var upwd = $('#loginFrm').find('#password').val();
            if (!uName) {
                $.messager.show({
                    title: '提示',
                    msg: '用户名不能为空',
                    timeout: 1000,
                    showType: 'slide'
                });
                return false;
            }
            if (!upwd) {
                $.messager.show({
                    title: '提示',
                    msg: '密码不能为空',
                    timeout: 1000,
                    showType: 'slide'
                });
                return false;
            }
        },
        success: function (data) {
            var result = JSON.parse(data);
            if(result.success) {
                window.location.href=rootPath+'/home';
            } else {
                $.messager.show({
                    title:'提示',
                    msg:data.message,
                    timeout:1000,
                    showType:'slide'
                });
            }
        }
    });

    $('#loginFrm').find('#loginBtn').on('click', function(){
        $('#loginFrm').form('submit');
    });
});