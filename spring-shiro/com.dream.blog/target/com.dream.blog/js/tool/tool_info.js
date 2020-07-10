/*工具库*/

//获取工具库列表数据
getToolListDate();

//获取工具库列表数据
function getToolListDate(){
	var url="tool/doFindTools";
	$.get(url,function(result){
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
	$("#tool .tool-box").empty();
	$(data).each(function(index,element){
		
		var liList="";	
		$(element.list).each(function(li,le){
			var lin=' <li><a target="_blank" href="'+ le.tool_url +'">'+ le.tool_name +'</a></li> ';
			liList+=lin;
		})
		var tempalte='<div class="list">'
			+' 			<h3>'+  element.className +'</h3>'
			+' 			<ul>'
			+                liList
			+' 			</ul>'
			+' 		</div>';	
		
		$("#tool .tool-box").append(tempalte);

	})
}


