//首页
var imgSrc=""; //图片地址
var str="";    //字符串


brief();  //个人简介 初始化
idea();   //开发理念 初始化
dream();  //梦想  初始化


//个人简介 
function brief(){
	str='梦想大师 90后一枚，活跃在各个技术社区，常以梦想大师作为昵称，这是我的个人博客。';
	$("#brief .brief-list").find("p").eq(0).text(str);
	str='本人喜欢看电影、听音乐、爱动漫、爱打球、爱美食，伪文艺青年，不感冒游戏，更讨厌小说。';
	$("#brief .brief-list").find("p").eq(1).text(str);
	str='非常热爱Code，折腾代码，不打代码会死星人哦，朝着全栈奔跑的小牛。';	
	$("#brief .brief-list").find("p").eq(2).text(str);
}
//开发理念 初始化
function idea(){
	str='响应式设计，就是网页设计的一种方法，根据不同设备分辨率调整布局。设计是我的一大爱好，做自己喜欢的事情，PS奇怪的东西，做一个优雅懂设计的全栈开发。';
	$("#idea .ban .ban-l").eq(0).find("p").text(str);
	str='我是从大学开始接触Web应用开发技术，一个全栈开发攻城师怎么能没有一个属于自己的网站呢！博主在WEB道路上的点点滴滴，愿与你们共同分享，一起进步！';
	$("#idea .ban .ban-l").eq(2).find("p").text(str);
}
//梦想 
function dream(){
	str='“ 20岁~30岁 写代码，朝着牛逼的方向努力。30岁~40岁 开一家衣柜商场，增加储蓄。40岁~50岁 出去游历，看到更大的世界。50岁~60岁 为自己写一本书，自己的故事。总之我现在所做的一切，都是为了追求更加完美。”';
	$("#dream .dream-info").text(str);
	str='一个梦想的追随者';	
	$("#dream .d-title").text(str);
}

