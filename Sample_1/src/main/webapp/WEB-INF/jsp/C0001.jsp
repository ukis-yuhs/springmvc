<%--
  Created by IntelliJ IDEA.
  User: yuhaisheng
  Date: 2019/4/29
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- page style -->
<style>
    /* Content */
    .content {
        min-height: 250px;
        padding: 15px;
        margin-right: auto;
        margin-left: auto;
        padding-left: 15px;
        padding-right: 15px;
    }
</style>
<script language="JavaScript">
    var path = getContextPath();
    $(function () {
        //初始化数据
        $.ajax({
            url: '/book/page/1/5',
            data: null,
            type: 'GET',
            success: function (ret) {
                // 取得检索结果
                var resultList = ret.rows;
                // 销毁已有table
                $("#table_SearchResult").html("");
                $("#tbody_SearchResult").html(createTable(resultList));
            },
            error: function (err) {
                alert(err);
            }
        });

        //图书检索事件绑定
        $("#btn_contextSearch").click(function(){
            //console.log("$('#bookNumber').val() = " + $("#bookNumber").val())
            $.ajax({
                url: '/book/detail/' + $("#bookNumber").val(),
                data: null,
                type: 'GET',
                success: function (ret) {
                    // 取得检索结果
                    var result = ret.obj;
                    // 销毁已有table
                    $("#table_SearchResult").html("");
                    $("#tbody_SearchResult").html(
                            '<tr>'
                            + '<td>'+ result.bookNumber + '</td>'
                            + '<td>'+ result.bookName + '</td>'
                            + '<td>'+ result.author + '</td>'
                            + '<td>'+ result.publisher + '</td>'
                            + '<td>'+ result.publishDate + '</td>'
                            + '<td>'+ result.price + '</td>'
                            + '<td>'+ result.bookType + '</td>'
                            + '<td>'+ result.storeLocation + '</td>'
                            + '<td>'+ result.storeDate + '</td>'
                            + '<td>'+ result.borrowedNumber + '</td>'
                            + '<td>'+ result.number + '</td>'
                            + '<td><a href="javascript:void(0);" class="btn default btn-xs black" id="btn_edit" onClick="editData(\''+ result.bookNumber + '\')"><i class="fa fa-pencil"></i> 编辑 </a></td>'
                            + '<td><a href="javascript:void(0);" class="btn default btn-xs black" id="btn_delete" onClick="deleteData(\''+ result.bookNumber + '\')"><i class="fa fa-trash-o"></i> 删除 </a></td>'
                            + '</tr>'
                    );
                },
                error: function (err) {
                    alert(err);
                }
            });
        });
    });

    // 设置Table值
    var createTable = function(resultList) {
        var htmlTxt = '';
        for ( var index in resultList) {
            htmlTxt = htmlTxt
                    + '<tr>'
                    + '<td>'+ resultList[index].bookNumber + '</td>'
                    + '<td>'+ resultList[index].bookName + '</td>'
                    + '<td>'+ resultList[index].author + '</td>'
                    + '<td>'+ resultList[index].publisher + '</td>'
                    + '<td>'+ resultList[index].publishDate + '</td>'
                    + '<td>'+ resultList[index].price + '</td>'
                    + '<td>'+ resultList[index].bookType + '</td>'
                    + '<td>'+ resultList[index].storeLocation + '</td>'
                    + '<td>'+ resultList[index].storeDate + '</td>'
                    + '<td>'+ resultList[index].borrowedNumber + '</td>'
                    + '<td>'+ resultList[index].number + '</td>'
                    + '<td><a href="javascript:void(0);" class="btn default btn-xs black" id="btn_edit" onClick="editData(\''+ resultList[index].bookNumber + '\')"><i class="fa fa-pencil"></i> 编辑 </a></td>'
                    + '<td><a href="javascript:void(0);" class="btn default btn-xs black" id="btn_delete" onClick="deleteData(\''+ resultList[index].bookNumber + '\')"><i class="fa fa-trash-o"></i> 删除 </a></td>'
                    + '</tr>';
        }
        return htmlTxt;
    }

    /**
     * 详细信息编辑
     */
    function editData(id) {
        alert("详细信息编辑");
    }
    /**
     * 详细信息删除
     */
    function deleteData(id) {
        $.ajax({
            url: '/book/delete/' + id,
            data: null,
            type: 'POST',
            success: function (ret) {
                alert(JSON.stringify(ret));
                //alert("删除成功")
            },
            error: function (err) {
                alert(JSON.stringify(err));
            }
        });
    }
</script>
<div>
    <section class="content-header">
        <h1 class="page-header">
            图书管理
            <small>图书信息一览</small>
        </h1>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <i class="fa fa-home"></i>  <a href="/page/index">首页</a>
            </li>
            <li class="breadcrumb-item active">
                <i class="fa fa-gears"></i> 图书管理
            </li>
        </ol>
    </section>
    <section class="content">
        <div class="container" style="margin-top:30px">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-search"></i>  检索条件</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>图书ID</label>
                                        <input type="text" class="form-control" maxlength="10" id="bookNumber" name="bookNumber" placeholder="图书ID">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="text-right">
                                        <button type="button" class="btn btn-success" id="btn_contextSearch"><i class="icon-magnifier m-icon-white"></i>  图书检索</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row panel-body">
                <table class="table table-striped table-bordered table-hover table-full-width">
                    <thead>
                    <tr>
                        <th>图书ID</th>
                        <th>书名</th>
                        <th>作者</th>
                        <th>出版社</th>
                        <th>出版日期</th>
                        <th>单价</th>
                        <th>所属类型</th>
                        <th>存放位置</th>
                        <th>入库日期</th>
                        <th>借出数量</th>
                        <th>图书数量</th>
                        <th>编辑</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody id="tbody_SearchResult"></tbody>
                </table>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
