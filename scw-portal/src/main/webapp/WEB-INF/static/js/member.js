$(function () {

    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    $('#myTab1 a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })

    var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
    option = {
        title: {
            text: '七日年化收益率(%)'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['基金', '股票']
        },
        toolbox: {
            show: false,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['2017-05-16', '2017-05-17', '2017-05-18', '2017-05-19', '2017-05-20', '2017-05-21', '2017-05-22']
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} '
            }
        },
        series: [
            {
                name: '基金',
                type: 'line',
                data: [1, 1, 5, 3, 2, 3, 2],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name: '股票',
                type: 'line',
                data: [1, -2, 2, 5, 3, 2, 4],
                markPoint: {
                    data: [
                        {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'},
                        [{
                            symbol: 'none',
                            x: '90%',
                            yAxis: 'max'
                        }, {
                            symbol: 'circle',
                            label: {
                                normal: {
                                    position: 'start',
                                    formatter: '最大值'
                                }
                            },
                            type: 'max',
                            name: '最高点'
                        }]
                    ]
                }
            }
        ]
    };
    myChart.setOption(option);
    var myChart1 = echarts.init(document.getElementById('main1'));

// 指定图表的配置项和数据
    option1 = {
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ['基金', '票据', '定期理财', '变现贷'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '直接访问',
                type: 'bar',
                barWidth: '60%',
                data: [10, 52, 200, 334, 390, 330, 220]
            }
        ]
    };

// 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option1);

    var myChart2 = echarts.init(document.getElementById('main2'));

// 指定图表的配置项和数据
    option2 = {
        title: {
            text: '某站点用户访问来源',
            subtext: '纯属虚构',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: 335, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1548, name: '搜索引擎'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

// 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option2);

    var session = $.session.get("loginacct");
    showLoginacct(session);

});

function showLoginacct(session) {
    if (session == null) {
        window.location.href="/scw-portal/member/index";
    } else {
        var login = $("<li class='dropdown'></li>")
        login.append("<a href='' class='dropdown-toggle' data-toggle='dropdown'><i class='glyphicon glyphicon-user'></i>" + session + "<span class='caret'></span></a>\n" +
            "                            <ul class='dropdown-menu' role='menu'>\n" +
            "                                <li><a href='/scw-portal/member/main'><i class='glyphicon glyphicon-scale'></i> 会员中心</a></li>\n" +
            "                                <li><a href=''><i class='glyphicon glyphicon-comment'></i> 消息</a></li>\n" +
            "                                <li class='divider'></li>\n" +
            "                                <li><a href='index.html'><i class='glyphicon glyphicon-off'></i> 退出系统</a></li>\n" +
            "                            </ul>")
        $(".navbar").find("ul").append(login);
    }

    $("#memberDiv").find("h3").text(session);

}