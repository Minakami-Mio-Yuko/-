
//管理员界面
layui.use('element', function(){
    var element = layui.element;

});


$(document).ready(function(){
    $("dd>a").click(function (e) {
        e.preventDefault();
        $("#iframeMain").attr("src",$(this).attr("href"));
    });
});