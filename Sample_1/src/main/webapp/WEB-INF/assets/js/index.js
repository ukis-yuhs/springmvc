/**
 * Created by yuhaisheng on 2019/4/29.
 */
$(function() {
    var Index = (function() {
        var me = {};
        // 处理子菜单点击
        me.handleSubMenuClick = function() {
            $('#page-sidebar-menu li a').click(function(e) {
                e.preventDefault();
                var url = this.href;
                if (url != null && url != 'javascript:;') {
                    $.get(url, function(data) {
                        $('#main-content').html(data);
                    });
                }
            });
        };

        me.init = function() {
            me.handleSubMenuClick();
        };

        return me;
    })();
    Index.init();
});

