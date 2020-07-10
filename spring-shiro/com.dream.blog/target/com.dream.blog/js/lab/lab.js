/* 知识库 */


var str="";    //字符串

banner();  //banner 初始化



//banner图
function banner(){
	str='点点滴滴的知识终将汇聚成浩瀚银河';
	$("#lab-banner .content").find("span").text(str);
}


//知识库  标签过渡
$(window).scroll(function() {	
	if($(window).scrollTop() > 200) {		
		$('#repository #r-box').addClass("rep-r-active");
	} else {			
		$('#repository #r-box').removeClass("rep-r-active");
	}
});