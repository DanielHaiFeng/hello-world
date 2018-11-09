function onLogin(frm) {
    debugger;
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
}