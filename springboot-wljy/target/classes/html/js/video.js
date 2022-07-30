var timer=setTimeout(function(){},10);  
var hidding = false;
var firstTime = 0;
var finaTime;
var videoTime;
var flag=true;
$(document).ready(function(){
	//鼠标移动事件
    $(document).mousemove(function () {  
        if(hidding){  
          hidding = false;  
          return;  
        }  
        if (timer) {  
            clearTimeout(timer);  
            timer = 0;  
        }  
        if(flag){
        	clearTimeout(timer);
        }

    });
    
    var time = setInterval(sendLearnTime,10000) ;
});


//发送学习时间
function sendLearnTime(){
	var video = CKobject.getObjectById('ckplayer_a1')||"";
	if(video!=""){
		//当前视频时长
		var learnTime = video.getStatus().time;
		//视频总时长
		videoTime = video.getStatus().totalTime;
		//学习时间大于0
		finalearnTime = learnTime - firstTime;
		if(learnTime - firstTime >0){
			// if(finalearnTime>=30){//防止拖拽视频大于30秒只记30秒
			// 	finalearnTime = 30;
			// }
            finalearnTime = Math.round(Math.random()*100000)/1000;
			web.ajax(basePath+"edu/eduStuCourse/saveCourseLearnTime.ajax",{userid:userId,courlibNo:courlibNo,learnTime:finalearnTime},true,function(r){
				if(r.state == 0){
					//console.log("学习时间："+learnTime +r.msgInfo);
				} else {
					//console.log("学习时间："+learnTime +r.msgInfo);
					return;
				}
			});
			web.ajax(basePath+'edu/course/savePlayHistory.ajax',{userId:userId,courseNo:courseNo,courlibNo:courlibNo,kPointNo:id,process:cookieTime,type:"1"},true,function(r){
				
			});
		}
		//firstTime = learnTime;
	}
}