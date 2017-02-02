'use strict';

/* Filters */

var AppFilters = angular.module('AngularSpringApp.filters', []);

AppFilters.filter('interpolate', ['version', function (version) {
    return function (text) {
        return String(text).replace(/\%VERSION\%/mg, version);
    }
}]);

AppFilters.filter('dateRangeCreate', function() {
    return function( items, fromDate, toDate ) {
        var filtered = [];
        var from_date = Date.parse(fromDate);
        var to_date = Date.parse(toDate);
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if(Date.parse(item.createDate) >= from_date && Date.parse(item.createDate) <= to_date) {
                    filtered.push(item);
                }
            });
            return filtered;
        }
        return items;
    };
});

AppFilters.filter('dateRangeOpen', function() {
    return function( items, fromDate, toDate ) {
        var filtered = [];
        var from_date = Date.parse(fromDate);
        var to_date = Date.parse(toDate);
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if(Date.parse(item.openDate) >= from_date && Date.parse(item.openDate) <= to_date) {
                    filtered.push(item);
                }
            });
            return filtered;
        }
        return items;
    };
});

AppFilters.filter('dateRangeDue', function() {
    return function( items, fromDate, toDate ) {
        var filtered = [];
        var from_date = Date.parse(fromDate);
        var to_date = Date.parse(toDate);
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if(Date.parse(item.dueDate) >= from_date && Date.parse(item.dueDate) <= to_date) {
                    filtered.push(item);
                }
            });
            return filtered;
        }
        return items;
    };
});

AppFilters.filter('dateRange', function() {
    return function( items, fromDate, toDate ) {
        var filtered = [];
        var from_date = Date.parse(fromDate);
        var to_date = Date.parse(toDate);
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if(Date.parse(item.date) >= from_date && Date.parse(item.date) <= to_date) {
                    filtered.push(item);
                }
            });
            return filtered;
        }
        return items;
    };
});

AppFilters.filter('dateRangeNotifFirst', function() {
    return function( items, fromDate, toDate ) {
        var filtered = [];
        var from_date = Date.parse(fromDate);
        var to_date = Date.parse(toDate);
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if(Date.parse(item.first) >= from_date && Date.parse(item.first) <= to_date) {
                    filtered.push(item);
                }
            });
            return filtered;
        }
        return items;
    };
});

AppFilters.filter('dateRangeNotifSecond', function() {
    return function( items, fromDate, toDate ) {
        var filtered = [];
        var from_date = Date.parse(fromDate);
        var to_date = Date.parse(toDate);
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if(Date.parse(item.second) >= from_date && Date.parse(item.second) <= to_date) {
                    filtered.push(item);
                }
            });
            return filtered;
        }
        return items;
    };
});

AppFilters.filter('dateRangeNotifThird', function() {
    return function( items, fromDate, toDate ) {
        var filtered = [];
        var from_date = Date.parse(fromDate);
        var to_date = Date.parse(toDate);
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if(Date.parse(item.third) >= from_date && Date.parse(item.third) <= to_date) {
                    filtered.push(item);
                }
            });
            return filtered;
        }
        return items;
    };
});