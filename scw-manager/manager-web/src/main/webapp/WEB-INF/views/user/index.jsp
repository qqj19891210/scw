<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>尚筹网主页</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/carousel.css"/>"/>
</head>
<body>
<div class="navbar-wrapper">
    <div class="container">
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse" style="float:right;">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="<c:url value="/permission/user/login"/>">登录</a></li>
                        <li><a href="<c:url value="/permission/user/regist"/>">注册</a></li>
                    </ul>
                </div>
            </div>
        </nav>

    </div>
</div>


<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active" onclick="window.location.href='project.html'" style="cursor:pointer;">
            <img src="<c:url value="/images/carousel-1.jpg"/>" alt="First slide">
        </div>
        <div class="item" onclick="window.location.href='project.html'" style="cursor:pointer;">
            <img src="<c:url value="/images/carousel-2.jpg"/>" alt="Second slide">
        </div>
        <div class="item" onclick="window.location.href='project.html'" style="cursor:pointer;">
            <img src="<c:url value="/images/carousel-3.jpg"/>" alt=" Third slide"/>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
    </a>
</div><!-- /.carousel -->


<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="col-lg-4">
            <img class="img-circle" src="<c:url value="/images/p1.jpg"/>" alt="Generic placeholder image"
                 style="width: 140px; height: 140px;">
            <h2>智能高清监控机器人</h2>
            <p>可爱的造型，摄像安防远程互联的全能设计，让你随时随地守护您的家人，陪伴你的生活。</p>
            <p><a class="btn btn-default" href="project.html" role="button">项目详情 &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="img-circle" src="<c:url value="/images/p2.jpg"/>" alt="Generic placeholder image"
                 style="width: 140px; height: 140px;">
            <h2>NEOKA智能手环</h2>
            <p>要运动更要安全，这款、名为“蝶舞”的NEOKA-V9100智能运动手环为“安全运动而生”。</p>
            <p><a class="btn btn-default" href="project.html" role="button">项目详情 &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="img-circle" src="<c:url value="/images/p3.jpg"/>" alt="Generic placeholder image"
                 style="width: 140px; height: 140px;">
            <h2>驱蚊扣</h2>
            <p>随处使用的驱蚊纽扣，<br>解决夏季蚊虫问题。</p>
            <p><a class="btn btn-default" href="project.html" role="button">项目详情 &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="box ui-draggable" id="mainBox">
                    <div class="mHd" style="">
                        <div class="path">
                            <a href="projects.html">更多...</a>
                        </div>
                        <h3>
                            科技
                            <small style="color:#FFF;">开启智慧未来</small>
                        </h3>
                    </div>
                    <div class="mBd" style="padding-top:10px;">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="images/product-1.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">活性富氢净水直饮机</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-2.gif"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">酷驰触控龙头，智享厨房黑科技</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-3.png"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">小熊猫鱼眼全景安防摄像机</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-4.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">一款精致的机械表</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="box ui-draggable" id="mainBox">
                    <div class="mHd" style="">
                        <div class="path">
                            <a href="projects.html">更多...</a>
                        </div>
                        <h3>
                            设计
                            <small style="color:#FFF;">创意改变生活</small>
                        </h3>
                    </div>
                    <div class="mBd" style="padding-top:10px;">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-5.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">活性富氢净水直饮机</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-6.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">酷驰触控龙头，智享厨房黑科技</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-7.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">小熊猫鱼眼全景安防摄像机</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-8.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">一款精致的机械表</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="box ui-draggable" id="mainBox">
                    <div class="mHd" style="">
                        <div class="path">
                            <a href="projects.html">更多...</a>
                        </div>
                        <h3>
                            农业
                            <small style="color:#FFF;">网络天下肥美</small>
                        </h3>
                    </div>
                    <div class="mBd" style="padding-top:10px;">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-9.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">活性富氢净水直饮机</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-2.gif"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">酷驰触控龙头，智享厨房黑科技</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-3.png"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">小熊猫鱼眼全景安防摄像机</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-4.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">一款精致的机械表</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="box ui-draggable" id="mainBox">
                    <div class="mHd" style="">
                        <div class="path">
                            <a href="projects.html">更多...</a>
                        </div>
                        <h3>
                            其他
                            <small style="color:#FFF;">发现更多惊喜</small>
                        </h3>
                    </div>
                    <div class="mBd" style="padding-top:10px;">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-1.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">活性富氢净水直饮机</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-2.gif"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">酷驰触控龙头，智享厨房黑科技</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-3.png"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">小熊猫鱼眼全景安防摄像机</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="<c:url value="/images/product-4.jpg"/>"/>
                                    <div class="caption">
                                        <h3 class="break">
                                            <a href="project.html">一款精致的机械表</a>
                                        </h3>
                                        <p>
                                        <div style="float:left;"><i class="glyphicon glyphicon-screenshot"
                                                                    title="目标金额"></i> $20,000
                                        </div>
                                        <div style="float:right;"><i title="截至日期"
                                                                     class="glyphicon glyphicon-calendar"></i>
                                            2017-20-20
                                        </div>
                                        </p>
                                        <br>
                                        <div class="progress" style="margin-bottom: 4px;">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: 40%">
                                                <span>40% </span>
                                            </div>
                                        </div>
                                        <div><span style="float:right;"><i
                                                class="glyphicon glyphicon-star-empty"></i></span> <span><i
                                                class="glyphicon glyphicon-user" title="支持人数"></i> 12345</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- FOOTER -->
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div id="footer">
                    <div class="footerNav">
                        <a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow"
                                                                                      href="http://www.atguigu.com">服务条款</a>
                        | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow"
                                                                                        href="http://www.atguigu.com">网站地图</a>
                        | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                    </div>
                    <div class="copyRight">
                        Copyright ?2017-2017atguigu.com 版权所有
                    </div>
                </div>

            </div>
        </div>
    </div>

</div><!-- /.container -->
<script type="text/javascript" src="<c:url value="/jquery/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/docs.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/back-to-top.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/index.js"/>"></script>
</body>
</html>
