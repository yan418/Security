/*导航 初始化*/


header();  //导航 初始化


//导航 初始化
function header(){
	var header=[
		{'href':'lab',name:'知识库'},
		{'href':'tool',name:'工具库'},
		{'href':'message',name:'留言板'},
		{'href':'luftmensch',name:'梦想家'}
	];
	$("#header .home-nav ul").html("");
	//遍历导航数组   追加导航
	$(header).each(function(i,e){		
		var template='<li><a href="'+ e.href +'"onclick="stopBubble(event)">'+ e.name +'</a></li>';	
		$("#header .home-nav ul").append(template);		
	})	
	onVisChange(); //监听是否在当前窗口
}
//手机端点击 展开导航菜单
$('#header .mobile-btn').click(function() {		
	$('#header').SuiNav({}).toggle();
});
//禁止冒泡事件
function stopBubble(e){
	e.stopPropagation();
}
//导航过渡
$(window).scroll(function() {	
	if($(window).scrollTop() > 200) {		
		$('#header').addClass("header-active");
	} else {			
		$('#header').removeClass("header-active");
	}
});

//判断用户进入和离开当前页面
function onVisChange(){
	// VisibilityChange 事件；用于判断用户是否离开当前页面
	// 监听 visibility change 事件
    // 页面的 visibility 属性可能返回三种状态  (prerender，visible 和 hidden)
	document.addEventListener('visibilitychange',function(){ 
		var isHidden = document.hidden;	 
		if(isHidden){
			document.title = '出BUG,快回来！';	 
		}else{
			document.title = '梦想大师的个人博客';
		}		 
	});
}