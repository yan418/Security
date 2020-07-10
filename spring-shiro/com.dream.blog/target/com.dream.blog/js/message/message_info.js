/*留言库*/

//获取列表数据
getListDate();

//激活 留言点击事件
doSubmit();

//获取列表数据
function getListDate(){
	var url="chat/doFindChat";
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
	
	$("#message .message-on").empty();
	$(data).each(function(index,element){
		var tempalte='<div class="message-list" uid="'+ element.chat_id +'" sid="'+ element.chat_sonid +'">'
				+' 			<div class="list-in">'
				+' 				<div class="title">'
				+' 					<img src="'+  element.luftmensch_filePath +'" />'
				+' 					<span>'+ element.luftmensch_pet +'</span>'
				+' 				</div>'
				+' 				<div class="info">'
				+' 					<p>'+  element.chat_content +'</p>'
				+' 				</div>'
				+' 			</div>'
				+' 		</div>'
		$("#message .message-on").append(tempalte);	
	})
	
	//激活 无限瀑布流
	waterFlow();
	//激活鼠标移动效果
	$("#message .message-box .list-in").xs999(1);
	
		
}


//无限瀑布流
function waterFlow(){
	
	//计算out的宽度	
	var outDiv = document.querySelector('.message-box');
	var img = document.querySelectorAll('.message-list');
	var imgWidth=img[0].offsetWidth;
	var num=Math.round(outDiv.offsetWidth/imgWidth);
	
	// outDiv的高度等于 该记录最后一条的高度+ top值     250是浏览器顶部 焦点图位置的高度
	var lastHeight=(img[(img.length)-1].offsetHeight + img[(img.length)-1].offsetTop) - 250;
	outDiv.style.height=lastHeight+"px";

	//处理图片瀑布流样式
	var arrHeight=[];
	for(var i=0;i<img.length;i++){
		if(i<num){
			var minHeight = img[i].offsetHeight;
			arrHeight.push(minHeight);
		}else{
			var min=Math.min.apply(window,arrHeight);
			img[i].style.position="absolute";
			var index=arrHeight.indexOf(min);
			
			img[i].style.left=img[index].offsetLeft+"px";
			
			img[i].style.top=arrHeight[index]+"px";
			arrHeight[index]=arrHeight[index]+img[i].offsetHeight;			
		}	
	}
}


//激活 留言点击事件
function doSubmit(){
	
	/* 留言表单 */
	$("#leave-from #leave-save").click(function(){
		//获取表单上的数据
		var paremt=getFromData();
		if(paremt==null || paremt==undefined){
			return;
		}
		var url="chat/doSaveChat";
		$.post(url,paremt,function(result){
			if(result.state==1){
				
				//关闭模态框
       			$('#leave-from').modal('hide');
			    $(".modal-backdrop").remove();
		        $("body").removeClass('modal-open');
		        //清空数据
		        $('#leave-from input[name="name"]').val("");
				$('#leave-from input[name="postbox"]').val("");
				$('#leave-from textarea[name="info"]').val("");
		        parent.layer.msg('上传成功,等待审核');
			}else{
				parent.layer.msg('主人在修改bug,无法上墙');
			}
		})	

	})	
}

//获取表单上的数据
function getFromData(){	
	var pet=$('#leave-from input[name="name"]').val();
	var postbox=$('#leave-from input[name="postbox"]').val();
	var info=$('#leave-from textarea[name="info"]').val();
	if(pet==""){
		parent.layer.msg("昵称不能为空");
		return;
	}
	if(postbox==""){
		parent.layer.msg("邮箱不能为空");
		return;
	}
	if(info==""){
		parent.layer.msg("留言不能为空");
		return;
	}
	if(info.length>100){
		parent.layer.msg("字数不能超过100个字符");
		return;
	}
	var paremt={
		"pet":pet,
		"postbox":postbox,
		"info":info
	};	
	return paremt;
}
