web.ajax(basePath + '/exam/paperInstance/showExerciseQuestions.ajax', {
    userid: stuId, instNo: instNo
}, true, function (r) {
    /*web.ajax('http://wljyxu.vaiwan.com/api/wljy/addQues', { data: r }, true, function (r) {
            console.log(r);
    });*/
    $.ajax({
        method:"post",
        url:"http://127.0.0.1:8088/api/wljy/addQuesapi",
        contentType: 'application/json;charset=UTF-8',
        async:true,
        data:JSON.stringify(r),
        dataType:"json",
        success:function(r){
            console.log(r);
        }
    })
});
