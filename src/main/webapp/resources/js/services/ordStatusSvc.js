/**
 * Created by barmin on 17.01.2017.
 */
angular
    .module('ordstatuses')
    .service('ordStatusService', ordStatusService);



function ordStatusService ($http) {
    var _this = this;

    _this.list = [];

    _this.getList = function () {
        return _this.list;
    };

    _this.loadList = function () {
        var res = $http.get('ordstats/ordstatlist.json');

        res.success(function(OrdStatusList){
            _this.list = OrdStatusList;
            //console.log('OK loading invStatusList');
        }).error(function () {
            _this.list = [];
            //console.log('error loading invStatusList');
        });
        return res;
    }
}
