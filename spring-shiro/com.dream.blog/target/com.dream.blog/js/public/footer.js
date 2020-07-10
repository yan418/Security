/*底部信息*/


copyright();  //版权信息 初始化

//版权信息 初始化
function copyright(){
	var copyright='© 2019 梦想大师  design & 备案号: ';
	$("#footer .copyright").find("span").text(copyright);
}

//显示二维玛
$('#footer .wxinfo').click(function(){
	layer.open({
	  type: 1,
	  shade: true,
	  title: false, //不显示标题
	  content: $('#footer .layer_notice'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
	  cancel: function(){    
	  }
	});
})

$('#clo').click(function(){
	layer.closeBtn({
	 
	});
})
