$(function () {
    $('#loginFrm').form({
        url: rootPath + '/login',
        onSubmit: function () {
            var uName = $('#loginFrm').find('#userName').val();
            var upwd = $('#loginFrm').find('#password').val();
            if (!uName) {
                showInfo('用户名不能为空');
                return false;
            }
            if (!upwd) {
                showInfo('密码不能为空');
                return false;
            }
            var base64 = new Base64();
            $('#loginFrm').find('#password').val(base64.encode(upwd));
        },
        success: function (data) {
            var result = JSON.parse(data);
            if(result.success) {
                window.localStorage.setItem('loginUser',$("[name='userName']").val());
                window.location.href=rootPath+'/home';
            } else {
                showInfo(result.message);
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

    window.localStorage.removeItem('loginUser');
});