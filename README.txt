EC Side介绍：
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
作者： fins （ name: Wei Zijun  ，  email:fins@163.com  ， blog:http://fins.javaeye.com  ）

EC Side是一个开源的列表组件。
他源自著名开源列表组件 eXtremeComponents （http://www.extremecomponents.org），
但现在已经脱离eXtremeComponents,独立发展（仍有部分代码来自 eXtremeComponents）。
“做最实用易用的列表组件”是ECSide的目标。

ECSide发布地址：
2.0:
ECSide圈子：  
http://ecside.group.javaeye.com/

作者blog：
http://fins.javaeye.com/



~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
主要特性：

以下是1.X系列的特性,2.0又有了重大的突破,全新特性列表稍后奉上.

1 可设置工具条位置（上 下 或 不显示）
2 可设置工具条内容（显示哪些 不显示哪些）
3 可设置工具条上各个功能按钮的相对位置
4 增加了调整页大小的选择框，并可自定义选择框内的内容
5 增加了带有邻近页面的导航条
6 增加了可跳转到指定页面的跳转框
7 增加了当前选中行高亮
8 为ec:table 增加了 excludeParameters 和 includeParameters 属性，可以实现更快捷简便的“参数保留/不保留”功能
9 可添加自定义的html代码到工具条内 或其他位置
10 可手动调整列宽，可通过js调整列表高度
11 增加了“列表内部滚动条”(实现列表头固定，列表体滚动的功能)
12 为ec:row和ec:column 添加更多的html事件支持，现支持：onmouserover onmouserout onclick ondbclick
13 为ec:table ec:row ec:column增加了自定义扩展属性功能
14 增加 ec:extendrow 标签，实现列表扩展行的功能
15 增加shadowRow(影子行)功能：每行下面可以再加一个子行 这个行里显示什么可以由大家自己定义
16 增加页面变量 ${TOTALROWCOUNT} 用来标示当前纪录在全部记录中的行数
17 增加了打印功能（尚不完善）
18 ec:column属性增加 ellipsis ,实现单元格内数据过长的时候 自动截短并加"..."的功能(ie only)
19 实现了跨列的列表头
20 统计栏的标题格可跨列
21 增加了若干种cell 和 headerCell,例如checkbox radiobox
22 取消了imagePath属性,样式相关的图片信息全部提入css内
23 重（第4声）用了js 和css ，很多功能用js来实现
24 支持了ajax翻页 (可设置 客户端 还是 服务端进行 html代码剪裁 )
25 支持预查询功能，在察看第n页的时候，把n+1页的数据也查询出来（隐藏着）备用，加快查看下一页的速度
26 将导出excel所使用的组件由poi切换成了 jxl
27 xls导出方式修改 原始的导出是导出的vo/map里的原始数值 现在是导出页面实际显示的内容
28 增加简捷导出方式 (通过ec:table的 xlsFileName pdfFileName csvFileName属性)
29 支持pdf简体中文导出。
30 代码进行了大规模的重构
31 增加了很多ajax相关特性
32 实现了可编辑列表功能
33 实现了cell的映射功能
34 easyList、easyDataAccess、easyDataExport特性：可通过简单的配制，快速实现列表数据的展现、查询、修改、删除、导出。
35 支持复杂表头的自定义与导出。
36 支持横向统计。
37 可以对列表内容进行灵活多样的escape。
... ...



~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
更新日志：

（详见 CHANGELOG.txt）




