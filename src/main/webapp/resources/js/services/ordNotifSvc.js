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
            _this.orders = [];
        });
    };

    _this.getEmployeeIdByOrderId = function (orderId) {
        if(typeof _this.orders != "undefined" && _this.orders != null && _this.orders.length > 0){
            _this.loadEmpList();
        }
        var employeeId = 0;
        _this.orders.forEach(function (order) {
            if (order.id == orderId) {
                employeeId = order.employeeId;
            }
        });
        return employeeId;
    }
}