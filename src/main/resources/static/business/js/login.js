$(function(){
    $("#loginSubmit").on("click",function(){
        var loginName = $("#loginName").val();
        var pwd = $("#password").val();
        if (loginName == null || loginName == undefined || loginName == '') {
            alert("用户名不能为空!");
            return;
        }
        if (pwd == null || pwd == undefined || pwd == '') {
            alert("密码不能为空!");
            return;
        }
        $.post("/api/user/login.do", { loginName: loginName, passwd: pwd },
            function(data){
                if(data.status==1){
                    window.location.href = "/api/index/index.html";
                }else{
                    alert(data.msg);
                }
            });
    });
});