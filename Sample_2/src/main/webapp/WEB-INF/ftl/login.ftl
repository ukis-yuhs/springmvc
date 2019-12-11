<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<h1>登录</h1>
<form id="ff" method="post" action="/user/login">
    <div>
        <label for="name">UserName:</label>
        <input class="easyui-validatebox" type="text" name="username" data-options="required:true" />
    </div>
    <div>
        <label for="password">Password:</label>
        <input class="easyui-validatebox" type="text" name="password" data-options="required:true" />
    </div>
    <div>
        <button type="submit" id="btn_login">登录</button>
    </div>
</form>
</body>
</html>