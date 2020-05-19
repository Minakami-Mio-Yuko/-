layui.use(['element','carousel'], function(){
    var element = layui.element//导航的hover效果、二级菜单等功能，需要依赖element模块
    ,   carousel = layui.carousel;//轮播效果

    //监听导航点击
    element.on('nav(demo)', function(elem){
        //console.log(elem)
        layer.msg(elem.text());
    });

    carousel.render({//设置轮播图参数
        elem: '#test2'
        , interval: 3000
        , anim: 'fade'
        , height: '700px'
        ,width:'100%'
    });

});

const dp = new DPlayer({
        container: document.getElementById('dplayer'),
        video: {
            url: 'video/main.mp4',
            autoplay: false,
            hotkey: true,
            type: 'auto',
            preload: 'auto'
        }

    }
);