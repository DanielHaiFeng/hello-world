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
            var base64 = new Base64();
            $('#loginFrm').find('#password').val(base64.encode(upwd));
        },
        success: function (data) {
            var result = JSON.parse(data);
            if(result.success) {
                window.location.href=rootPath+'/home';
            } else {
                $.messager.show({
                    title:'提示',
                    msg:result.message,
                    timeout:1000,
                    showType:'slide'
                });
                $('#loginFrm').find('#userName').val("");
                $('#loginFrm').find('#password').val("");
            }
        }
    });

    $('#loginFrm').find('#loginBtn').on('click', function () {
        $('#loginFrm').form('submit');
    });

    $('#loginFrm').find('#password').on('keyup', function (event) {
        if (event.keyCode == 13) {
            $('#loginFrm').form('submit');
        }
    })
});