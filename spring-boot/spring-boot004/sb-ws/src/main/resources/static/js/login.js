var websocket = null;

var slfChatId = null;

function openChatWindow(chatId) {
    var dialogDiv = $('<div id="'+chatId+'" style="display: none;">'+$('#chatDialog').html()+'</div>');
    dialogDiv.appendTo('body');
    $('#'+chatId).dialog({
        title: '与'+chatId+'聊天',
        width: 400,
        height: 275,
        closed: false,
        cache: false,
        modal: false,
        onOpen: function () {
            $('#'+chatId).find('#sendMsg').linkbutton({
                iconCls: 'icon-ok',
                onClick: function () {
                    var msg = $('#'+chatId).find('#msg').val();
                    if (msg) {
                        websocket.send('#PC#' + chatId + '#' + msg);
                        $('#'+chatId).find('#msg').val('');
                        $('#'+chatId).find('#allMsg').val($('#'+chatId).find('#allMsg').val() + '\r\n' + slfChatId + '<发送>' + msg);
                    } else {
                        showInfo('发送的消息不能为空');
                    }
                }
            });
        },
        onBeforeClose: function () {
            $('#'+chatId).find('#cdFrm').form('clear');
        }
    });
}

$(function () {
    $.messager.prompt('提示', '请输入聊天ID:', function (r) {
        if (r) {
            //判断当前浏览器是否支持WebSocket
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://192.168.5.27:8086/chat/websocket");
            } else {
                alert('Not support websocket')
            }

            //连接成功建立的回调方法
            websocket.onopen = function (event) {
                slfChatId = r;
                send('#REG#' + slfChatId);
                $("#utGrid").datagrid({
                    url: rootPath + '/getChaters',// 加载的URL
                    idField: "chatId",
                    fit: true,// 自动补全
                    fitColumns: true,
                    iconCls: "icon-save",// 图标
                    title: slfChatId+"的在线好友",
                    singleSelect: false,
                    rownumbers: true,
                    pageNumber: 1,
                    striped: true,//斑马线效果
                    checkOnSelect: true,//单击行不允许选择
                    loadMsg: "正在加载信息，请稍候...",
                    columns: [[ // 每个列具体内容
                        {
                            field: 'chatId',
                            title: '聊天ID',
                            width: 50
                        }, {
                            field: 'chat',
                            title: '聊天',
                            width: 50,
                            formatter: function (value, row, index) {
                                return '<a href="javascript:void(0);" onclick="openChatWindow(\'' + row.chatId + '\')">聊天</a>';
                            }
                        }]],
                    queryParams: {
                        slfChatId: slfChatId,
                    }
                });
            }

            //接收到消息的回调方法
            websocket.onmessage = function (event) {
                var rd = event.data;
                console.log(rd);
                if (rd == 'newComer') {
                    $("#utGrid").datagrid('reload');
                } else {
                    //#FRM#chatId#message
                    if (rd.substring(0, 5) == '#FRM#') {
                        var lsa = rd.indexOf('#', 5);
                        var chatId = rd.substring(5, lsa);
                        var message = rd.substring(lsa + 1, rd.length);
                        if ($('#'+chatId).is(':visible')) {
                            var allMsgTxtArea = $('#'+chatId).find('#allMsg');
                            allMsgTxtArea.val(allMsgTxtArea.val() + '\r\n' + '<收到>' + chatId + '的消息:' + message);
                        } else {
                            openChatWindow(chatId);
                            var allMsgTxtArea = $('#'+chatId).find('#allMsg');
                            allMsgTxtArea.val(allMsgTxtArea.val() + '\r\n' + '<收到>' + chatId + '的消息:' + message);
                        }
                    }
                }
            }

            //连接关闭的回调方法
            websocket.onclose = function () {
                console.log('websocket要关闭了');
            }

            //连接发生错误的回调方法
            websocket.onerror = function () {
                console.log('连接发生错误');
            }

            //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
            window.onbeforeunload = function () {
                websocket.close();
            }

            //发送消息
            function send(msg) {
                websocket.send(msg);
            }
        }
    });
})