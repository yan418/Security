/*知识库*/

//获取列表数据
getListDate();

//获取全部的二级分类数据
getLabClass();

//获取全部的分类数据
getLabTagName();

//获取列表数据
function getListDate(){
	var url="lab/doFindLabByNum";
	var paremt={"num":5};
	$.get(url,paremt,function(result){
		if(result.state==1){
			//展示处理数据
			doGetDate(result.data);
		}else{
			parent.layer.msg("暂时没有数据");
		}
	})	
}


//处理数据
function doGetDate(data){
	$("#repository .repository-l-box").empty();
	$(data).each(function(index,element){		
		var liList="";		
		$(element.list).each(function(li,le){
			var lin='<li uid="'+ le.lab_id +'" ><a uid="'+ le.lab_classify_two +'">'+ le.lab_name +'</a></li> ';
			liList+=lin;
		})
		var tempalte='<div class="list">'
			+' 			<h3 id="classify-'+ index +'">'+  element.className +'</h3>'
			+' 			<ul>'
			+                liList
			+' 			</ul>'
			+' 		</div>';	
		$("#repository .repository-l-box").append(tempalte);	
	})
	//激活点击事件
	$("#repository .repository-l-box ul li a").on("click",function(){	
		var paremt={
			"id":$(this).attr("uid"), //二级分类的id
			"url":"lab_info",
			"name":$(this).text()
		};
		localStorage.setItem("labState",JSON.stringify(paremt));
		//跳转详情页
		$(window).delay(400).attr('location',paremt.url+'?id='+paremt.id);		
	})
}

//获取全部的二级分类数据
function getLabClass(){
	var url="lab/doFindLabClass";
	$.get(url,function(result){
		if(result.state==1){
			//展示二级分类数据
			doGetLabClassDate(result.data);
		}else{
			parent.layer.msg("暂时没有数据");
		}
	})	
}

//展示二级分类数据
function doGetLabClassDate(data){
	$("#r-box .r-label ul").empty();
	$(data).each(function(index,element){
		var tempalte='<li  uid="'+ element.lab_class_id +'"><a>'+  element.lab_class_name +'</a></li>';	
		$("#r-box .r-label ul").append(tempalte);
	})
	//激活点击事件
	$("#r-box .r-label ul li").on("click",function(){	
		var paremt={
			"id":$(this).attr("uid"),
			"url":"lab_info"
		};
		localStorage.setItem("labState",JSON.stringify(paremt));
		//跳转详情页
		$(window).delay(400).attr('location',paremt.url+'?id='+paremt.id);		
	})
}

//获取全部的分类数据
function getLabTagName(){
	var url="lab/doFindLabTagName";
	$.get(url,function(result){
		if(result.state==1){
			//展示分类数据
			doGetTagName(result.data);
		}else{
			parent.layer.msg("暂时没有数据");
		}
	})	
}

//展示分类数据
function doGetTagName(data){
	//console.log(data)
	$("#r-box .r-tab-name ul").empty();
	$(data).each(function(index,element){
		var tempalte='<li  uid="'+ element.lab_classify_two +'"><a>'+  element.lab_tag +'</a></li>';	
		$("#r-box .r-tab-name ul").append(tempalte);
	})
	//激活点击事件
	$("#r-box .r-tab-name ul li").on("click",function(){	
		var paremt={
			"id":$(this).attr("uid"),
			"url":"lab_info"
		};
		localStorage.setItem("labState",JSON.stringify(paremt));
		//跳转详情页
		$(window).delay(400).attr('location',paremt.url+'?id='+paremt.id);		
	})
}
