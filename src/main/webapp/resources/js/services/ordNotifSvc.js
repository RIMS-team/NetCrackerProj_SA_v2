/**
 * Created by trvler135 on 22.01.2017.
 */


angular
    .module('notifications')
    .service('ordNotifyService', ordNotifyService);

function ordNotifyService ($http) {

    var _this = this;

    _this.orders = [];

    _this.loadEmpList = function () {
        var res = $http.get('order/all');
        res.success(function(orderList){
            _this.orders = orderList;
        }).error(function () {
            console.log("Error geting orders");
        });
    };

    _this.loadUserList = function () {
        var res = $http.get('order/all');

        res.success(function(userList){
            _this.orders = userList;
            //console.log('OK loading ordUserList');
        }).error(function () {
            console.log("Error geting orders");
        });
    }

    _this.getEmployeeIdByOrderId = function (orderId) {
        console.log(_this.orders);
        var employeeId = 0;
        _this.orders.forEach(function (order) {
            if (order.id == orderId) {
                employeeId = order.employeeId;
            }
        });
        return employeeId;
    }

    _this.getUserIdByOrderId = function (orderId) {
        console.log(_this.orders);
        var userId = 0;
        _this.orders.forEach(function (order) {
            if (order.id == orderId) {
                userId = order.userId;
            }
        });
        return userId;
    }
}