layui.use(['table','element'], function(){
    var table = layui.table
        ,element=layui.element;


    //监听导航点击
    element.on('nav(demo)', function(elem){
        //console.log(elem)
        layer.msg(elem.text());
    });





    //渲染表格
    table.render({
        elem: '#test'
        ,url:'/myorder'
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,cols: [[
            {field:'orderManname', title: '男方姓名'}
            ,{field:'orderWomanname', title: '女方姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
            ,{field:'orderAdress', title: '家庭地址'}
            ,{field:'orderDate', title: '拍摄日期',sort:true,align: 'center'}
            ,{field:'orderDress', title: '婚纱样式',align: 'center'}
            ,{field:'orderArea', title: '拍摄地区',align: 'center'}
            ,{field:'orderCity', title: '拍摄城市',align: 'center'}
            ,{field:'orderPhone', title: '联系电话'}
            ,{field:'orderWechat', title: '微信'}
            ,{field:'orderDesc', title: '订单备注'}
            ,{field:'orderCameraman', title: '摄影师',align: 'center'}
            ,{field:'orderStatus', title: '订单状态',align: 'center', unresize: true}
        ]]
    });
});