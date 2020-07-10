/*梦想家*/

//获取列表数据
getListDate();

//获取地图信息
getMapInfo();

//获取工具库列表数据
function getListDate(){
	var url="luft/doFindLuftmensch";
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
	$("#luftmensch .blogroll .friendly").empty();
	$(data).each(function(index,element){
		var tempalte='<li class="friendly-list">'
				+'			<a href="'+  element.luftmensch_website  +'" target="_blank" >'
				+'				<div class="friendly-l"><img src="'+  element.luftmensch_filePath +'" /></div>'
				+'				<div class="friendly-r">'
				+'					<span>'+  element.luftmensch_pet +'</span>'
				+'					<font>'+  element.luftmensch_profile +'</font>'
				+'				</div>'
				+'			</a>'
				+'		</li>'
		$("#luftmensch .blogroll .friendly").append(tempalte);
	})
	var goIn='<div class="go-in-we" ><span data-toggle="modal" data-target="#register-luft">加入我们</span></div>';
	$("#luftmensch .blogroll").append(goIn);
	
	//激活 加入我们点击事件
	doSubmit();
}


//我在这， 获取地图信息
function getMapInfo(){
	//五棵松的坐标
	var map_x=39.914;
	var map_y=116.278;
	//百度地图API功能
	var map = new BMap.Map("map-in");
	var point = new BMap.Point(map_y, map_x);
	map.centerAndZoom(point,16);
	map.enableScrollWheelZoom(true);   
	//绘制中心坐标
	//var myIcon = new BMap.Icon("../images/home/nav/1.png", new BMap.Size(300,157));
	var myIcon = new BMap.Icon("images/home/nav/ball.png", new BMap.Size(30,30));
	var marker2 = new BMap.Marker(point,{icon:myIcon});  // 创建标注
	map.addOverlay(marker2);                     // 将标注添加到地图中
	marker2.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
}

//激活 加入我们点击事件
function doSubmit(){
	
	$("#register-luft #save").on("click",function(){
		//获取表单上的数据
		var paremt=getFromData();
		if(paremt==null || paremt==undefined){
			return;
		}
		var url="luft/doSaveLuft";
		$.post(url,paremt,function(result){
			if(result.state==1){

				//清空数据
		        $('#register-luft input[name="pet"]').val("");
				$('#register-luft input[name="web"]').val("");
				$('#register-luft input[name="imgSrc"]').val("");
				$('#register-luft textarea[name="info"]').val("");
				
				if(result.data==1){			
			        parent.layer.msg('昵称已存在');
				}else{
					//关闭模态框
	       			$('#register-luft').modal('hide');
				    $(".modal-backdrop").remove();
			        $("body").removeClass('modal-open');
			        parent.layer.msg('上传成功，等待审核！');
				}
				
		       
			}else{
				parent.layer.msg('主人在修改bug,无法上墙');
			}
		})	
	});
}

//获取表单上的数据
function getFromData(){	
	var pet=$('#register-luft input[name="pet"]').val();
	var web=$('#register-luft input[name="web"]').val();
	var imgSrc=$('#register-luft input[name="imgSrc"]').val();
	var info=$('#register-luft textarea[name="info"]').val();

	if(pet==""){
		parent.layer.msg("昵称不能为空");
		return;
	}
	if(web==""){
		parent.layer.msg("博客地址不能为空");
		return;
	}
	if(imgSrc==""){
		parent.layer.msg("头像地址不能为空");
		return;
	}
	if(info==""){
		parent.layer.msg("个人介绍不能为空");
		return;
	}
	if(info.length>40){
		parent.layer.msg("字数不能超过40个字符");
		return;
	}
	
	var paremt={
		"pet":pet,
		"web":web,
		"imgSrc":imgSrc,
		"info":info
	};	
	return paremt;
}
