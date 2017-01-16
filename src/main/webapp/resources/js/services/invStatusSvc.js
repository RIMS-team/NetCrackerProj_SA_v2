angular
    .module('invstatuses')
    .service('invStatusService', invStatusService);



function invStatusService ($http) {
    var _this = this;

    _this.list = [];

    _this.getList = function () {
        return _this.list;
    };

    _this.loadList = function () {
        var res = $http.get('invstats/invstatlist.json');

        res.success(function(InvStatusList){
            _this.list = InvStatusList;
            //console.log('OK loading invStatusList');
        }).error(function () {
            _this.list = [];
            //console.log('error loading invStatusList');
        });
        return res;
    }
}
