/**
 * Created by yuhaisheng on 2019/4/29.
 */
/*
 * 共同函数js
 */
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    return pathName.substr(0, index + 1);
}
