<#--用户管理-->
<div>
    <table class="easyui-datagrid" style="height:250px;width:100%" id="dg" striped="true" pagination="true" singleSelect="true"
           data-options="url:'datagrid_data.json',fitColumns:true,singleSelect:true">
        <thead>
        <tr>
            <th data-options="field:'id',width:100">用户ID</th>
            <th data-options="field:'code',width:100">用户Code</th>
            <th data-options="field:'name',width:100">用户名</th>
            <th data-options="field:'locked',width:100">用户状态</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>1000</td>
                <td>0001</td>
                <td>于海胜</td>
                <td>锁定</td>
            </tr>
            <tr>
                <td>1000</td>
                <td>0001</td>
                <td>于海胜</td>
                <td>锁定</td>
            </tr>
            <tr>
                <td>1000</td>
                <td>0001</td>
                <td>于海胜</td>
                <td>锁定</td>
            </tr>
        </tbody>
    </table>
</div>
<script>
    $(function () {
        $('#dg').datagrid({
            toolbar: [{
                iconCls: 'icon-edit',
                handler: function(){alert('edit')}
            },'-',{
                iconCls: 'icon-help',
                handler: function(){alert('help')}
            }]
        });
    });
</script>