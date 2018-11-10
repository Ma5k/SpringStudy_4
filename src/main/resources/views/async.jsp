<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>servlet async</title>

</head>
<body>
<h1>mask</h1>
	<script src="assets/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">

    deferred();//1、页面打开就向后台发送请求。

    function deferred(){
        $.get('defer',function(data){
            console.log(data); //2、在控制台输出服务端推送的数据
            deferred(); //3、一次请求完成后再向后台发送请求
        });
    }


</script>
</body>
</html>