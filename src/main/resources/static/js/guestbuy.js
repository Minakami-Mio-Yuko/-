layui.use(['form', 'laydate'], function(){
    var form = layui.form
        ,layer = layui.layer

        ,laydate = layui.laydate;

    //日期
    laydate.render({
        elem: '#date'
    });


    //动态地区下拉框
    form.on('select(areafilter)',function (data) {
        if(data.value==""){
            $('#city').html('<option value="">请选择城市</option>');
        }else if(data.value=="国内"){
            $('#city').html('<option value="">请选择城市</option>');
            $('#city').append(new Option("三亚", "三亚"));
            $('#city').append(new Option("丽江", "丽江"));
            $('#city').append(new Option("上海", "上海"));
            $('#city').append(new Option("大连", "大连"));
            $('#city').append(new Option("海南", "海南"));
            $('#city').append(new Option("青岛", "青岛"));
            $('#city').append(new Option("杭州", "杭州"));
            $('#city').append(new Option("温州", "温州"));
        }else if(data.value=="东南亚"){
            $('#city').html('<option value="">请选择城市</option>');
            $('#city').append(new Option("巴厘岛", "巴厘岛"));
            $('#city').append(new Option("普吉岛", "普吉岛"));
            $('#city').append(new Option("日本", "日本"));
            $('#city').append(new Option("马尔代夫", "马尔代夫"));
        }else if(data.value=="欧洲海外"){
            $('#city').html('<option value="">请选择城市</option>');
            $('#city').append(new Option("巴黎", "巴黎"));
            $('#city').append(new Option("威尼斯", "威尼斯"));
            $('#city').append(new Option("布拉格", "布拉格"));
            $('#city').append(new Option("瑞士", "瑞士"));
            $('#city').append(new Option("罗马", "罗马"));
            $('#city').append(new Option("普罗旺斯", "普罗旺斯"));
        }
        layui.form.render('select');
    })



    //监听提交
    form.on('submit(demo2)', function(data){
        if(data.field.orderArea==""||data.field.orderCity==""){//判断是否选择了城市
            layer.alert("拍摄地点不可为空",{
                title: '提示'
            });
            return false;
        }else if(data.field.orderDress==""){
            layer.alert("婚纱样式不可为空",{
                title: '提示'
            });
            return false;
        } else {
            $.ajax({
                type:'post',
                url:'/order',
                dataType:'json',//返回json
                data:JSON.stringify(data.field),//将JavaScript对象转换为json字符串
                contentType:'application/json',
                success:function (data) {

                    if(data.code=='0'){
                        layer.alert(data.msg);
                    }else {
                        window.location.href="/main?userid="+data.userid+"&userrole="+data.userrole;
                    }

                },
                error:function () {
                    layer.close(loginLoadIndex);
                    $('#loginBtn').val("登录");
                }
            });
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            });
        }

    });







});