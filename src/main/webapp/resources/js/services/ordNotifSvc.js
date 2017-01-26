/**
 * Created by trvler135 on 22.01.2017.
 */


angular
    .module('notifications')
    .service('ordNotifyService', ordNotifyService);

function ordNotifyService ($http) {

    var _this = this;

    _this.orders = [];
    _this.notificationTemps = [];
    _this.notificationTemp;

    _this.loadEmpList = function () {
        var res = $http.get('order/all');
        res.success(function(orderList){
            _this.orders = orderList;
        }).error(function () {
            console.log("Error geting orders");
        });
    };
    _this.loadNotifiTempList = function () {
        var res = $http.get('notification/allDefTemp');
        res.success(function(notifiTempList){
            _this.notificationTemps = notifiTempList;
        }).error(function () {
            console.log("Error geting orders");
        });
    };

    _this.getNotifiTempById = function (Id) {
        var temp = '';
        _this.notificationTemps.forEach(function (notifi) {
            console.log(23);
            if (notifi.notif_num == Id) {
                temp = notifi.template;
                console.log(23);
            }
        });
        return temp;
    }

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

    _this.findNotifiTemp1 = function(id) {
        return $http.get('notification/findNotifiById/'+id).success(function(notificationList){
            _this.notificationTemp = notificationList;
        });
    };

    _this.getNotifi = function () {
        return _this.notificationTemp.template;
    };

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