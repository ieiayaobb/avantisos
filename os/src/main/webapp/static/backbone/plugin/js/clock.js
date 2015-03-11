$(function(){
   var ClockView = Backbone.View.extend({
       el: $("#clock"),
       tagName: "div",
       template:  _.template($('#clock-template').html()),
       initialize: function() {
           this.render()
           this.run()
       },
       render: function(){
           this.$el.html(this.template());
           return this;
       },
       setValue: function(left_front, left_back, right_front, right_back){
           var upLeftFrontNumClass = "up-" + left_front
           var upLeftBackNumClass = "up-" + left_back
           var upRightFrontNumClass = "up-" + right_front
           var upRightBackNumClass = "up-" + right_back
           var downLeftFrontNumClass = "down-" + left_front
           var downLeftBackNumClass = "down-" + left_back
           var downRightFrontNumClass = "down-" + right_front
           var downRightBackNumClass = "down-" + right_back
           $(".up-back>.up-left").removeClass().addClass("up-left").addClass(upLeftBackNumClass)
           $(".up-back>.up-right").removeClass().addClass("up-right").addClass(upRightBackNumClass)
           $(".up-front>.up-left").removeClass().addClass("up-left").addClass(upLeftFrontNumClass)
           $(".up-front>.up-right").removeClass().addClass("up-right").addClass(upRightFrontNumClass)
           $(".down-back>.down-left").removeClass().addClass("down-left").addClass(downLeftBackNumClass)
           $(".down-back>.down-right").removeClass().addClass("down-right").addClass(downRightBackNumClass)
           $(".down-front>.down-left").removeClass().addClass("down-left").addClass(downLeftFrontNumClass)
           $(".down-front>.down-right").removeClass().addClass("down-right").addClass(downRightFrontNumClass)


           $(".down-back,.down-back>.down-left,.down-back>.down-right").height(0)
           $(".up-front").height(86).css({
               top : 0
           })
           $(".up-front>.up-left,.up-front>.up-right").height(55)

           $(".up-front,.up-front>.up-left,.up-front>.up-right").animate({
               height: 0,
               top : 86
           }, 200, function(){
               $(".down-back,.down-back>.down-left,.down-back>.down-right").animate({
                   height: 51,
               }, 200)
           });
       },
       run: function(){
           var _this = this;
           setInterval(function(){
               var currentSecond = new Date().getSeconds()
               var currentSecond_up = Math.floor(currentSecond / 10)
               var currentSecond_down = currentSecond % 10
               // var currentMinute = new Date().getMinutes()
               // var currentMinute_up = Math.floor(currentMinute / 10)
               // var currentMinute_down = currentMinute % 10
               // console.log(currentMinute_up)
               // console.log(currentMinute_down)
               if(currentSecond_down != 9){
                   _this.setValue(currentSecond_up,currentSecond_up,currentSecond_down,currentSecond_down + 1)
               }else{
                   if(currentSecond_up == 5){
                       _this.setValue(currentSecond_up,0,currentSecond_down,0)
                   }else{
                       _this.setValue(currentSecond_up,currentSecond_up + 1,currentSecond_down,0)
                   }
               }
               // setValue(3,4,5,6)

           },1000)
       }
   })
    var clockView = new ClockView()
});