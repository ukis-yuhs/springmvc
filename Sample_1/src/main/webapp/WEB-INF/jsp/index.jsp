<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%--网站图标--%>
        <link rel="shortcut icon" href="../assets/image/favicon.ico" type="image/x-icon" />
        <!-- Bootstrap CSS -->
        <link href="../assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="../assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
        <link href="../assets/css/index.css" rel="stylesheet"/>
        <title>首页</title>
    </head>
    <body>
        <div class="container-fluid fixed-top bg-color py-3">
            <div class="row collapse show no-gutters d-flex h-100 position-relative">
                <div class="col-3 px-0 w-sidebar navbar-collapse collapse d-none d-md-flex">
                    <!-- spacer col -->
                    <span style="color: #ffffff;"><b>Privilege management</b></span>
                </div>
                <div class="col px-2 px-md-0">
                    <!-- toggler -->
                    <a data-toggle="collapse" href="#" data-target=".collapse" role="button" class="p-1 a-color">
                        <i class="fa fa-bars fa-lg"></i>
                    </a>
                </div>
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-user-circle"></i>&nbsp&nbsp&nbsp<span id="loginUser">${activeUser.username}</span>&nbsp&nbsp&nbsp<b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a class="dropdown-item" href="#"><i class="fa fa-fw fa-info-circle"></i> 信息修改</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#"><i class="fa fa-fw fa-lock"></i> 密码修改</a>
                                </li>
                                <li class="dropdown-divider"></li>
                                <li>
                                    <a class="dropdown-item" href="/page/logout"><i class="fa fa-fw fa-sign-out"></i>退出登录</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="container-fluid px-0 h-100">
            <div class="row vh-100 collapse show no-gutters d-flex h-100 position-relative">
                <div class="col-3 p-0 vh-100 h-100 w-sidebar navbar-collapse collapse d-none d-md-flex sidebar">
                    <!-- fixed sidebar -->
                    <div class="position-fixed bg-color text-white h-100 w-sidebar pl-2">
                        <ul class="nav flex-column" id="page-sidebar-menu">
                            <li class="nav-item ul-li-sidebar" style="padding-top: 15px">
                                <span data-toggle="collapse"><strong>首页</strong></span>
                            </li>
                            <!-- shiro基于jsp标签的配置方式 -->
                            <shiro:hasPermission name="book:query">
                            <li class="nav-item ul-li-sidebar">
                                <a class="nav-link a-color" href="/page/book">测试菜单</a>
                            </li>
                            </shiro:hasPermission>
                            <c:if test="${activeUser.menus != null }">
                                <c:forEach items="${activeUser.menus}" var="menu" varStatus="vs">
                                    <li class="nav-item ul-li-sidebar">
                                        <%--<span data-toggle="collapse" data-target="#demo1"><strong>${menu.name}</strong></span>--%>
                                            <a class="nav-link a-color" href="${menu.url}">${menu.name}</a>
                                    </li>
                                </c:forEach>
                            </c:if>
                            <%--<li class="nav-item ul-li-sidebar">--%>
                                <%--<span data-toggle="collapse" data-target="#demo1"><strong>权限管理</strong></span>--%>
                                <%--<ul id="demo1" class="collapse">--%>
                                    <%--<li class="li-sidebar">--%>
                                        <%--<a class="nav-link a-color" href="#">用户管理</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="li-sidebar">--%>
                                        <%--<a class="nav-link a-color" href="#">角色管理</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="li-sidebar">--%>
                                        <%--<a class="nav-link a-color" href="#">权限管理</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="li-sidebar">--%>
                                        <%--<a class="nav-link a-color" href="#">资源管理</a>--%>
                                    <%--</li>--%>
                                <%--</ul>--%>
                            <%--</li>--%>
                            <%--<li class="nav-item ul-li-sidebar">--%>
                                <%--<span data-toggle="collapse" data-target="#demo2"><strong>商品管理</strong></span>--%>
                                <%--<ul id="demo2" class="collapse">--%>
                                    <%--<li class="li-sidebar">--%>
                                        <%--<a class="nav-link a-color" href="#">商品录入</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="li-sidebar">--%>
                                        <%--<a class="nav-link a-color" href="#">商品更新</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="li-sidebar">--%>
                                        <%--<a class="nav-link a-color" href="#">商品删除</a>--%>
                                    <%--</li>--%>
                                <%--</ul>--%>
                            <%--</li>--%>
                        </ul>
                    </div>
                </div>
                <div class="col">
                    <%--<div class="p-3 content-wrapper">--%>
                        <%--<!-- Content -->--%>
                    <%--</div>--%>
                    <!-- Content Wrapper. Contains page content -->
                    <div id="main-content" class="content-wrapper"></div>

                    <!-- Main Footer -->
                    <footer class="main-footer">
                        <!-- To the right -->
                        <div class="pull-right hidden-xs">权限管理</div>
                        <!-- Default to the left -->
                        <strong>Copyright &copy; 2019 <a href="#">ukis-yuhs</a>.
                        </strong> All rights reserved.
                    </footer>
                </div>
            </div>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="../assets/js/jquery-3.4.0.min.js"></script>
        <script src="../assets/js/popper.min.js"></script>
        <script src="../assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="../assets/js/common.js"></script>
        <script src="../assets/js/index.js"></script>
    </body>
</html>