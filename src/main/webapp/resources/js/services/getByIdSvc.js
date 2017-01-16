angular.module("AngularSpringApp").service('getByIdService', getByIdService);

function getByIdService () {
    var _this = this;

    _this.getById = function(idValue, list) {
        for (var i = 0; i < list.length; i++) {
            if (list[i].id == idValue) {
                return list[i];
            }
        }
    }
};