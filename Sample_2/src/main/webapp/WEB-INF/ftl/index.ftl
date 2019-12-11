<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../assets/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../assets/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../assets/common/css/common.css">
    <title>首页</title>
</head>
<body>
    <body class="easyui-layout">
        <div data-options="region:'west',title:'菜单',split:true,minWidth:200" style="width:100px;">
            <ul id="tt" class="easyui-tree">
                <li>
                    <span>权限管理</span>
                    <ul>
                        <li data-options="attributes:{'url':'userManger/init'}"><span>用户管理</span></li>
                        <li data-options="attributes:{'url':'roleManger/init'}"><span>角色管理</span></li>
                        <li data-options="attributes:{'url':'permManger/init'}"><span>权限管理</span></li>
                        <li data-options="attributes:{'url':'userRole/init'}"><span>用户角色</span></li>
                        <li data-options="attributes:{'url':'rolePerm/init'}"><span>角色资源</span></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
            <div id="tabs" class="easyui-tabs" style="width: 100%">
                <div title="首页" style="padding:20px;display:none;">
                    首页
                </div>
            </div>
        </div>
        <#--加载蒙版(防治二次提交)开始-->
        <div class="modal" id="modalLoading">
            <div class="modal-loading-content">
                <div class="loadEffect">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
        </div>
        <#--加载蒙版(防治二次提交)结束-->
    </body>
    <script type="text/javascript" src="../assets/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../assets/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../assets/common/js/common.js"></script>
    <script language="JavaScript">
        $('#tt').tree({
            onClick:function(node){
//                startLoading();
                //alert(node.text);
                var tabs = $("#tabs");
                var tab = tabs.tabs("getTab",node.text);
                if (tab) {
                    tabs.tabs("select",node.text)
                } else {
                    // add a new tab panel
                    tabs.tabs('add',{
                        title:node.text,
                        content:'Tab Body',
                        href:node.attributes.url,
                        closable:true,
                        tools:[{
                            iconCls:'icon-mini-refresh',
                            handler:function(){
                                alert('refresh');
                            }
                        }]
                    });
                }
//                setTimeout("endLoading()","2000");
            }
        })
    </script>
</body>
</html>