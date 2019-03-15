$(function(){
    $("#mobileSubmit").on("click",function(){
        var mobile = $("#mobile").val();
        var passwd = $("#passwdMobile").val();
        var passwordRepeat = $("#passwordRepeatMobile").val();
        if(!checkMobileParam(mobile,passwd,passwordRepeat)){
            return;
        }
        $.post("/api/shiro/register.do",
            {
                userName:mobile,
                passwd:passwd,
                rePasswd:passwordRepeat
            },
            function(data){
                if(data.status==1){
                    AMUI.dialog.alert({
                        //title: '错误提示',
                        content: '注册成功',
                        onConfirm: function() {
                            window.location.href="/api/shiro/toLogin.html";
                            //return;
                        }
                    });
                }else {
                    AMUI.dialog.alert({
                        title: '错误提示',
                        content: data.msg,
                        onConfirm: function() {
                            //return;
                        }
                    });
                }
            }
        );
    });

    $("#emailSubmit").on("click",function(){
        var email = $("#email").val();
        var passwd = $("#passwordEmail").val();
        var passwordRepeat = $("#passwordRepeatEmail").val();
        if(!checkEmailParam(email,passwd,passwordRepeat)){
            return;
        }
        $.post("/api/shiro/register.do",
            {
                userName:email,
                passwd:passwd,
                rePasswd:passwordRepeat
            },
            function(data){
                if(data.status==1){
                    AMUI.dialog.alert({
                        //title: '错误提示',
                        content: '注册成功',
                        onConfirm: function() {
                            window.location.href="/api/shiro/toLogin.html";
                            //return;
                        }
                    });
                }else {
                    AMUI.dialog.alert({
                        title: '错误提示',
                        content: data.msg,
                        onConfirm: function() {
                            //return;
                        }
                    });
                }
            }
        );
    });

    function checkMobileParam(mobile,passwd,passwordRepeat) {
        if (mobile == null || mobile == undefined || mobile == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '手机号不能为空！',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if (passwd == null || passwd == undefined || passwd == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '密码不能为空！',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if (passwordRepeat == null || passwordRepeat == undefined || passwordRepeat == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '确认密码不能为空！',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if(! (passwd === passwordRepeat)){
            AMUI.dialog.alert({
                title: '错误提示',
                content: '两次密码不一致',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if(!validatorMobile(mobile)){
            AMUI.dialog.alert({
                title: '错误提示',
                content: '手机格式不正确',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if(!($('#reader-me-mobile').is(':checked'))) {
            AMUI.dialog.alert({
                //title: '错误提示',
                content: '请选择同意服务协议',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        return true;
    }
    function checkEmailParam(email,passwd,passwordRepeat) {
        if (email == null || email == undefined || email == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '邮箱不能为空！',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if (passwd == null || passwd == undefined || passwd == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '密码不能为空！',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if (passwordRepeat == null || passwordRepeat == undefined || passwordRepeat == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '确认密码不能为空！',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if(! (passwd === passwordRepeat)){
            AMUI.dialog.alert({
                title: '错误提示',
                content: '两次密码不一致',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if(!validatorEmail(email)){
            AMUI.dialog.alert({
                title: '错误提示',
                content: '邮箱格式不正确',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        if(!($('#reader-me-email').is(':checked'))) {
            AMUI.dialog.alert({
                //title: '错误提示',
                content: '请选择同意服务协议',
                onConfirm: function() {
                    //return;
                }
            });
            return false;
        }
        return true;
    }

    /*
     * 验证邮箱格式是否正确
     */
    function validatorEmail(mail) {
        // 正则验证格式
        eval("var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;");
        return RegExp(reg).test(mail);
    }
    /*
     * 验证手机格式是否正确
     */
    function validatorMobile(mobile) {
        // 正则验证格式
        var myreg = /^1\d{10}$/;
        return myreg.test(mobile);
    }
});