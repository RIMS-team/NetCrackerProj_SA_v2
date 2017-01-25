/**
 * Created by trvler135 on 25.01.2017.
 */
angular.module("AngularSpringApp").service('getAuthorizedUserService', getAuthorizedUserService);

function getAuthorizedUserService ($http) {
    var _this = this;

    _this.user = {};

    _this.loadAuthorizedUser = function() {
        return $http.get('userSetting/getAuthorizedUser').success(function (user) {
            _this.user = user;
        });
    };

    _this.getAuthorizedUser = function () {
        return _this.user;
    };
}