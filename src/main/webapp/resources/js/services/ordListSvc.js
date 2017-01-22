/**
 * Created by Elina on 20.01.2017.
 */
angular
    .module('orders')
    .service('ordListService', ordListService);

function ordListService ($http) {
    var _this = this;

    _this.invList = [];
    _this.empList = [];
    _this.userList = [];

    _this.getInvList = function () {
        return _this.invList;
    };

    _this.loadInvList = function () {
        var res = $http.get('accesscards/accesscardlist.json');
        // var res = $http.get('accesscards/find/2'); // only cards with status_id = IN_STOCK (На складе)

        res.success(function(invList){
            _this.invList = invList;
            //console.log('OK loading ordUserList');
        }).error(function () {
            _this.invList = [];
            //console.log('error loading ordUserList');
        });
        return res;
    };

    _this.getEmpList = function () {
        return _this.empList;
    };

    _this.loadEmpList = function () {
        var res = $http.get('employees/empoyeelist.json');

        res.success(function(empList){
            _this.empList = empList;
            //console.log('OK loading ordUserList');
        }).error(function () {
            _this.empList = [];
            //console.log('error loading ordUserList');
        });
        return res;
    };

    _this.getUserList = function () {
        return _this.userList;
    };

    _this.loadUserList = function () {
        var res = $http.get('user/all');

        res.success(function(userList){
            _this.userList = userList;
            //console.log('OK loading ordUserList');
        }).error(function () {
            _this.userList = [];
            //console.log('error loading ordUserList');
        });
        return res;
    }
}