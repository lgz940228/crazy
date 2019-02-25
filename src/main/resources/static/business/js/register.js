$(function(){
    $("#registerSubmit").on("click",function(){
        var mobile = $("#mobile").val();
        var email = $("#email").val();
        var passwd = $("#passwd").val();
        var passwordRepeat = $("#passwordRepeat").val();
        if (mobile == null || mobile == undefined || mobile == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '手机号不能为空！',
                onConfirm: function() {
                    return;
                }
            });
            return;
        }
        if (email == null || email == undefined || email == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '邮箱不能为空！',
                onConfirm: function() {
                    return;
                }
            });
            return;
        }
        if (passwd == null || passwd == undefined || passwd == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '密码不能为空！',
                onConfirm: function() {
                    return;
                }
            });
            return;
        }
        if (passwordRepeat == null || passwordRepeat == undefined || passwordRepeat == '') {
            AMUI.dialog.alert({
                title: '错误提示',
                content: '确认密码不能为空！',
                onConfirm: function() {
                    return;
                }
            });
            return;
        }
    });

});