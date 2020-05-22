layui.use(['flow','element'], function(){
    var flow = layui.flow,
        element=layui.element;

    //监听导航点击
    element.on('nav(demo)', function(elem){
        //console.log(elem)
        layer.msg(elem.text());
    });


    flow.load({
        elem: '#demo' //指定列表容器
        ,isauto:true
        ,mb:''
        ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
            var lis = [];
            //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
            $.get('/findguestpicture?page='+page, function(res){
                //假设你的列表返回在data集合中
                layui.each(res.data, function(index, item){
                    console.log(item.pictureSrc);
                    lis.push('<li><div style="width: 100%;margin-top: 50px" ><img width="100%" src="'+ item.pictureSrc +'"'+'/>'+'</div></li>')
                });

                //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                next(lis.join(''), page < res.page);
            });
        }
    });
});