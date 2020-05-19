layui.use(['element','carousel'], function(){
    var element = layui.element //导航的hover效果、二级菜单等功能，需要依赖element模块
    ,   carousel = layui.carousel;//轮播效果

    //监听导航点击
    element.on('nav(demo)', function(elem){
        //console.log(elem)
        layer.msg(elem.text());
    });

});