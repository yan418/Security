/*知识库详情*/

var labState=JSON.parse(localStorage.getItem("labState"));
//滚动条初始化
goBackScroll();

//获取左部菜单列表
getMenus();


//滚动条初始化
function goBackScroll(){
	//滚动条大于200显示 返回顶部图标
	$(window).scroll(function() {	
		if($(window).scrollTop() > 200) {		
			$('#backtop').show();
		} else {			
			$('#backtop').hide();
		}
	});
	//返回顶部
	$("#backtop img").click(function(){
		document.body.scrollTop = 0;
	    document.documentElement.scrollTop = 0;
	})
}

//获取左部菜单列表
function getMenus(){
	var url="lab/doFindLabBrother";
	var paremt={"id":labState.id};
	$.get(url,paremt,function(result){
		if(result.state==1){
			//展示左部菜单数据
			doGetDate(result.data);
		}else{
			parent.layer.msg("暂时没有数据");
		}
	})	
}


//展示左部菜单数据
function doGetDate(data){
	
	$("#info-l ul").empty();
	$("#info-header .title").text(data.className);
	$(data.list).each(function(index,element){		
		var tempalte='<li uid="'+ element.lab_id +'">'+ element.lab_name +'</li>';
		$("#info-l ul").append(tempalte);	
	})
	
	var intVal=0;
	//遍历列表，判断初始化显示
	$("#info-l ul li").each(function(index,element){		
		if($(this).text()==labState.name){
			intVal=index;
		}else{
			intVal=0;
		}
	})		
	//初始化
	$("#info-l ul li").eq(intVal).addClass("selected").siblings().removeClass("selected");
	getLabInfo($("#info-l ul li").eq(intVal).attr("uid"));
	//激活点击事件
	$("#info-l li").click(function(){
		$("#info-l ul li").eq($(this).index()).addClass("selected").siblings().removeClass("selected");
		var name=$(this).text();
		var id=$(this).attr("uid");
		getLabInfo(id);
	})
}

//获取该条知识库详情数据
function getLabInfo(id){
	var url="lab/doFindLabInfo";
	var paremt={"id":id};
	$.get(url,paremt,function(result){
		if(result.state==1){
			//展示右边详情数据
			doGetInfo(result.data);
		}else{
			parent.layer.msg("暂时没有数据");
		}
	})	
}

//展示右边详情数据
function doGetInfo(data){
	$("#info_content .name").html(data.lab_name);
	$("#info_content .info").html(data.lab_info);
}
