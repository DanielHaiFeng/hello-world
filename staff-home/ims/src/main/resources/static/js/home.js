$(function () {
    console.log(getRootPath());

    $('#btn').linkbutton({
        iconCls: 'icon-search'
    });

    $('#cc').calendar({
        current: new Date()
    });

    $.messager.alert('警告', '警告消息');

    $('#win').window({
        width: 600,
        height: 400,
        modal: true
    });

    $('#nn').numberbox({
        min: 10,
        precision: 2
    });
})