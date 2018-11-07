$(function () {
    console.log(getRootPath());

    $('#btn').linkbutton({
        iconCls: 'icon-search'
    });

    $('#cc').calendar({
        current: new Date()
    });

    $('#combox').combobox({
        url: 'data/combobox_data.json',
        valueField: 'id',
        textField: 'text',
        required: true
    });

    $('#dg').datagrid({
        url: 'data/datagrid_data.json',
        columns: [[{
            field: 'code',
            title: 'Code'
        }, {
            field: 'name',
            title: 'Name'
        }, {
            field: 'price',
            title: 'Price'
        }]],
        pagination: true,
        singleSelect: true
    });

    $.messager.alert('警告', '警告消息');

    $('#dd').dialog({
        title: 'My Dialog',
        width: 400,
        height: 200,
        closed: false,
        cache: false,
        href: 'get_content.php',
        modal: true
    });

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