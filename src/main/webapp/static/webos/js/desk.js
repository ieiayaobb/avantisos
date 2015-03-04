$(function(){
    var seq = 0;
    var moduleId = 0;
    var Menu = Backbone.Model.extend({
        defaults: function(){
            return {
                id: "",
                pId: "",
                name: "",
                href: "",
                icon: "static/webos/img/bd/icon-1.png",
                sequence:seq++,
                moduleId: moduleId++
            }
        }
    });

    var Menus = Backbone.Collection.extend({
        model : Menu
    });

    var menus = new Menus;

    var MenuView = Backbone.View.extend({
        tagName: "div",
        className: "desk-icon",

        attributes: {
            sequence: 0,
            moduleId: 0
        },

        template: _.template($('#deskIcon-template').html()),

        events: {
            "click":    "activeIcon",
            "dblclick":  "openFrame"
        },

        initialize: function() {
            this.attributes.sequence = this.model.get("sequence");
            this.href = this.model.get("href");

            //this.listenTo(this.model, 'change', this.render);
        },
        render: function(){
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        activeIcon: function(){

            $(".desk-icon,.fence-icon").removeClass("ue-state-active");
            this.$el.addClass("ue-state-active");
        },
        openFrame: function(){
            var href = this.href;
            var windowModel = new WindowModel();
            windowModel.set({
                href: window._contextPath + href
            });
            var windowView = new WindowView({model: windowModel});
            $(".logo_bottom").before(windowView.render().el);

            $(".frames").height($(window).height() - 20);
            $(".frames .contentBd").height($(window).height() - 55);
        }
    });

    var WindowModel = Backbone.Model.extend({
        defaults:function(){
            return {
                href : "",
                title:"title"
            }
        }
    })

    var WindowView = Backbone.View.extend({
        tagName: "div",
        className: "frames miniFrame",
        attributes: {
            frameId: 0,
            moduleId: 0
        },
        events: {
            "click .closeWindow": "closeWindow",
            "click .min": "minimalWindow"
        },
        template: _.template($('#window-template').html()),
        initialize: function() {
        },
        render: function(){
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        closeWindow: function(){
            this.$el.remove();
        },
        minimalWindow: function(){
            var $currentFrame = this.$el
            //··调用scale事件  先缩小到左侧导航栏的右边（眼睛处）
            $currentFrame.animate({
                //设置缩放倍数
                width : "5%",
                height : "5%",
                //设置焦点位置
                top : 10
            },500,
            function(){//缩放事件完成后
                //··被缩小页面标题栏高度减小
                $currentFrame.find(".contentBd").height($currentFrame.height() - 32);
                //滑动此frames至眼睛
                $currentFrame.animate({
                        left: '50px'
                    }, 200,
                    function(){//滑动事件完成
                        $currentFrame.hide();
                    });
            })
        }
    });

    var SignView = Backbone.View.extend({
        tagName : "div",
        className: "plugin ue-clear",
        template: _.template($('#sign-template').html()),
        events: {
            "mousedown .contentHd": "drag",
            "mouseup": "release"
        },
        initialize: function() {
        },
        render: function(){
            this.$el.html(this.template());
            return this;
        },
        drag: function(mde){
            mde.preventDefault();
            var $dragPlugin = this.$el
            $(window).bind("mousemove",function(mme){
                mme.preventDefault();
                $dragPlugin.offset({
                    left : mme.clientX - mde.offsetX,
                    top : mme.clientY - mde.offsetY
                });
            });
        },
        release: function(){
            $(window).unbind("mousemove");
        }
    })

    var AppView = Backbone.View.extend({
        el: $("#desk"),

        initialize: function() {
            //this.listenTo(menus, 'reset', this.addAll);
            //this.listenTo(menus, 'all', this.adjustPosition)
            document.oncontextmenu = function(){return false};

            if($("#container").height()<$(window).height()){
                $("#container").height($(window).height());
            }
            menus.fetch({
                url : window._contextPath + "/sys/menu/allFunctionalMenus",
                success: function(collection, response, options){
                    //collection.each(function(menu) {
                    //    console.log(menu)
                    //});
                    app.addAll();
                    app.adjustPosition()
                }
            });
            this.addSign()
        },

        render: function(){

        },

        addSign: function(){
            var signView = new SignView();
            $(".pluginGroup").append(signView.render().el)
        },

        addOne: function(menu) {
            var menuView = new MenuView({model: menu});
            this.$el.append(menuView.render().el);
        },

        addAll: function() {
            menus.each(this.addOne, this);
        },

        eachNum : 8,
        eachHeight: 116,
        eachWidth: 116,
        maxSequence: -1,

        adjustPosition: function(){
            var _this = this
            if($(".desk-icon").length > 0){//有图标时
                $(".desk-icon").each(function(i){//循环所有的桌面图标
                    //计算得到图标的top和left值
                    var topIndex = parseInt($(this).attr("sequence") % _this.eachNum, 10);
                    var leftIndex = parseInt($(this).attr("sequence") / _this.eachNum,10);
                    $(this).offset({//设置图标的top和left值（加上初始状态的左上角坐标）
                        top : $(this).parent().offset().top + topIndex * _this.eachHeight,
                        left : $(this).parent().offset().left + leftIndex * _this.eachWidth
                    });
                    //··如果此sequence为最大sequence（最后一个图标）
                    if($(this).attr("sequence") == _this.maxSequence){
                        var addTopIndex;
                        var addLeftIndex;
                        //··若此图标已在列的末尾，则添加按钮的坐标left要加【1】
                        if(topIndex + 1 >= _this.eachNum){
                            addTopIndex = 0;
                            addLeftIndex = leftIndex + 1;
                        }
                        //··正常情况下是获取最后一个图标的列值left，高度值加【1】
                        else{
                            addTopIndex = topIndex + 1;
                            addLeftIndex = leftIndex;
                        }
                        //设置添加按钮坐标
                        $(".addItem").offset({
                            top : $(this).parent().offset().top + addTopIndex * _this.eachHeight + 16,
                            left : $(this).parent().offset().left + addLeftIndex * _this.eachWidth + 26
                        })
                    }
                });
            }else{//不存在图标时
                $(".addItem").offset({
                    top : $(".shortcutTray").offset().top + 16,
                    left : $(".shortcutTray").offset().left + 26
                })
            }
        }
    })

    var app = new AppView
});