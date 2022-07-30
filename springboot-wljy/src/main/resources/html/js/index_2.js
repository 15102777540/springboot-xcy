var showdata = [];
var confi = confirm("是否需要自动填写答案");
$("body").append('<div id="myModal" style="display: none;position: fixed; z-index: 1; padding-top: 100px; left: 0;top: 0;width: 100%; height: 100%; overflow: auto; background-color: rgb(0,0,0);background-color: rgba(0,0,0,0.4);"><div id="modal" style="position: relative;        background-color: #fefefe;        margin: auto;        padding: 0;        border: 1px solid #888;        width: 20%;        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);        -webkit-animation-name: animatetop;        -webkit-animation-duration: 0.4s;        animation-name: animatetop;        animation-duration: 0.4s">做题中。。。进度0%</div></div>');
if (confi) {
    web.ajax(basePath + '/exam/paperInstance/showExerciseQuestionsNew.ajax', {
        userid: stuId, instNo: instNo, type: "1"
    }, true, function (r) {
        r.paperDetail.forEach(c => {
            c.questionList.forEach(x => {
                showdata.push(x.queNo);
            });
        });
        console.log(showdata);
        $("#myModal").show();
        showdata.forEach((a, index) => {
            (function (params) {
                setTimeout(() => {
                    web.ajax('http://140.238.154.141:8088/api/wljy/getInfo', {sno: a}, true, function (r) {
                        r.forEach(x => {
                            let arrc = x.questionAnswercontent.split(":");
                            var questionAnswercontent = "";
                            if (arrc.length == 1) {
                                questionAnswercontent = x.questionAnswercontent;
                            }
                            if (arrc.length >= 2) {
                                if (arrc[1].length <= 2) {
                                    if (arrc[1] == "正确" || arrc[1] == "错误") {
                                        questionAnswercontent = arrc[1];
                                    } else {
                                        questionAnswercontent = arrc[0];
                                    }
                                } else {
                                    if (x.questionCorrectanswer.indexOf(";") == -1) {
                                        questionAnswercontent = x.questionCorrectanswer;
                                    } else {
                                        questionAnswercontent = x.questionCorrectanswer.replaceAll(";", ",");
                                    }
                                }
                            }
                            if (questionAnswercontent.indexOf(",") >= 0) {
                                let strings = questionAnswercontent.split(",");
                                for (let i = 0; i < strings.length; i++) {
                                    let stuanswer = "";
                                    for (let j = 0; j <= i; j++) {
                                        stuanswer += strings[j] + ",";
                                    }
                                    stuanswer = stuanswer.substr(0, stuanswer.length - 1);
                                    setTimeout(() => {
                                        web.ajax(basePath + '/exam/paperInstance/saveStuAnswerOfMultipleSelect.ajax', {
                                            userid: stuId,
                                            queno: a,
                                            stuanswer: stuanswer,
                                            instno: instNo
                                        }, true, function (r) {
                                            
                                        });
                                    }, (i + 1) * 3000);
                                }
                            } else {
                                web.ajax(basePath + '/exam/paperInstance/saveStuAnswerOfSingleSelect.ajax', {
                                    userid: stuId,
                                    queno: a,
                                    stuanswer: questionAnswercontent,
                                    instno: instNo
                                }, true, function (r) {
                                    
                                });
                            }
                        });
                        $("#modal").html("做题中。。。进度"+ (index+1)/(showdata.length-1)*100 +"%");
                        if (index == showdata.length - 1) {
                            $("#myModal").hide();
                            alert("自动选填完成,请刷新页面后进行提交");
                            location.reload();
                        }
                    });

                }, (index + 1) * 3000);
            })(index)
        });
    });
}





