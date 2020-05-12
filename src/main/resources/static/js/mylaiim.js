layui.use('layim', function(){
    var layim = layui.layim;
    console.log(userid);
    var socket = new WebSocket("ws://localhost:8080/websocket/"+userid);



    //基础配置
    layim.config({
        //初始化接口
        init: {
            url: '/init'
            ,data: {"id":userid}
        }
        //查看群员接口
        ,members: {
            url: '/members'
            ,data: {}
        }

        ,uploadImage: {
            url: '/uploadimage'
            ,type: '' //默认post
        }
        ,uploadFile: {
            url: '/uploadfile'
            ,type: '' //默认post
        }

        ,isAudio: true //开启聊天工具栏音频
        ,isVideo: true //开启聊天工具栏视频
        ,title: '聊天' //自定义主面板最小化时的标题


        //,brief: true //是否简约模式（若开启则不显示主面板）


        //,right: '100px' //主面板相对浏览器右侧距离
        //,minRight: '90px' //聊天面板最小化时相对浏览器右侧距离
        ,initSkin: '3.jpg' //1-5 设置初始背景
        //,skin: ['aaa.jpg'] //新增皮肤
        //,isfriend: false //是否开启好友
        //,isgroup: false //是否开启群组
        //,min: true //是否始终最小化主面板，默认false
        //,notice: true //是否开启桌面消息提醒，默认false
        //,voice: false //声音提醒，默认开启，声音文件为：default.mp3


    });
    //监听在线状态的切换事件
    layim.on('online', function(status){
        layer.msg(status);
    });

    //监听签名修改
    layim.on('sign', function(value){
        layer.msg(value);
    });

    //监听layim建立就绪
    layim.on('ready', function(res){
        //console.log(res.mine);
        layim.msgbox(5); //模拟消息盒子有新消息，实际使用时，一般是动态获得
    });

    //监听发送消息
    layim.on('sendMessage', function(data){
     socket.send(JSON.stringify(data));
    });

    //监听收到的信息
    socket.onmessage = function(res) {
        if(res.data == "0"){
            layer.msg('该用户不在线', {
                time: 2000,
                icon: 2,
                offset: '150px'
            });
        }else if(res.data.substring(0,7)=="offline"){
            layim.setFriendStatus(res.data.substring(7), 'offline'); //设置指定好友在线，即头像置灰
        } else if(res.data.substring(0,6)=="online"){
            layim.setFriendStatus(res.data.substring(6), 'online'); //设置指定好友在线，即头像高亮
        } else {
            var jsonobject = JSON.parse(res.data)
            layim.getMessage(jsonobject); //交给layui处理接收到的信息
        }
    };


    //监听查看群员
    layim.on('members', function(data){
        //console.log(data);
    });


    //更新当前会话状态
    layim.on('chatChange', function(res){
        var type = res.data.type;
        if(type === 'friend'){
            //layim.setChatStatus('<span style="color:#FF5722;">对方正在输入。。。</span>');
            if(res.data.status==="offline"){
                layim.setChatStatus('<span style="color:#FF5722;">离线</span>'); //模拟标注好友在线状态
            }else if(res.data.status==="online"){
                layim.setChatStatus('<span style="color:#777777;">在线</span>'); //模拟标注好友在线状态
            }
        }
    });



    $('.site-demo-layim').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});