'use strict';

/* Filters */

var AppFilters = angular.module('AngularSpringApp.filters', []);

AppFilters.filter('interpolate', ['version', function (version) {
    return function (text) {
        return String(text).replace(/\%VERSION\%/mg, version);
    }
}]);

AppFilters.filter('dateRangeOpen', function() {
    return function( items, fromDate, toDate ) {
        var filtered = [];
        var from_date = Date.parse(fromDate);
        var to_date = Date.parse(toDate);
        var openDate;
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if (item.openDate !== null) {
                    openDate = new Date(item.openDate);
                    openDate.setHours(0, 0, 0, 0);
                    if (Date.parse(openDate) >= from_date && Date.parse(openDate) <= to_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(from_date)) {
            angular.forEach(items, function(item) {
                if (item.openDate !== null) {
                    openDate = new Date(item.openDate);
                    openDate.setHours(0, 0, 0, 0);
                    if (Date.parse(openDate) >= from_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(to_date)) {
            angular.forEach(items, function(item) {
                if (item.openDate !== null) {
                    openDate = new Date(item.openDate);
                    openDate.setHours(0, 0, 0, 0);
                    if(Date.parse(openDate) <= to_date) {
                        filtered.push(item);
                    }
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
        var dueDate;
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if (item.dueDate !== null) {
                    dueDate = new Date(item.dueDate);
                    dueDate.setHours(0, 0, 0, 0);
                    if(Date.parse(dueDate) >= from_date && Date.parse(dueDate) <= to_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(from_date)) {
            angular.forEach(items, function(item) {
                if (item.dueDate !== null) {
                    dueDate = new Date(item.dueDate);
                    dueDate.setHours(0, 0, 0, 0);
                    if(Date.parse(dueDate) >= from_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(to_date)) {
            angular.forEach(items, function(item) {
                if (item.dueDate !== null) {
                    dueDate = new Date(item.dueDate);
                    dueDate.setHours(0, 0, 0, 0);
                    if(Date.parse(dueDate) <= to_date) {
                        filtered.push(item);
                    }
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
        var date;
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if (item.date !== null) {
                    date = new Date(item.date);
                    date.setHours(0, 0, 0, 0);
                    if(Date.parse(date) >= from_date && Date.parse(date) <= to_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(from_date)) {
            angular.forEach(items, function(item) {
                if (item.date !== null) {
                    date = new Date(item.date);
                    date.setHours(0, 0, 0, 0);
                    if(Date.parse(date) >= from_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(to_date)) {
            angular.forEach(items, function(item) {
                if (item.date !== null) {
                    date = new Date(item.date);
                    date.setHours(0, 0, 0, 0);
                    if(Date.parse(date) <= to_date) {
                        filtered.push(item);
                    }
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
        var first;
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if (item.first !== null) {
                    first = new Date(item.first);
                    first.setHours(0, 0, 0, 0);
                    if(Date.parse(first) >= from_date && Date.parse(first) <= to_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(from_date)) {
            angular.forEach(items, function(item) {
                if (item.first !== null) {
                    first = new Date(item.first);
                    first.setHours(0, 0, 0, 0);
                    if(Date.parse(first) >= from_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(to_date)) {
            angular.forEach(items, function(item) {
                if (item.first !== null) {
                    first = new Date(item.first);
                    first.setHours(0, 0, 0, 0);
                    if(Date.parse(first) <= to_date) {
                        filtered.push(item);
                    }
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
        var second;
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if (item.second !== null) {
                    second = new Date(item.second);
                    second.setHours(0, 0, 0, 0);
                    if(Date.parse(second) >= from_date && Date.parse(second) <= to_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(from_date)) {
            angular.forEach(items, function(item) {
                if (item.second !== null) {
                    second = new Date(item.second);
                    second.setHours(0, 0, 0, 0);
                    if(Date.parse(second) >= from_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(to_date)) {
            angular.forEach(items, function(item) {
                if (item.second !== null) {
                    second = new Date(item.second);
                    second.setHours(0, 0, 0, 0);
                    if(Date.parse(second) <= to_date) {
                        filtered.push(item);
                    }
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
        var third;
        if ((!isNaN(from_date)) && (!isNaN(to_date))) {
            angular.forEach(items, function(item) {
                if (item.third !== null) {
                    third = new Date(item.third);
                    third.setHours(0, 0, 0, 0);
                    if (Date.parse(third) >= from_date && Date.parse(third) <= to_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(from_date)) {
            angular.forEach(items, function(item) {
                if (item.third !== null) {
                    third = new Date(item.third);
                    third.setHours(0, 0, 0, 0);
                    if(Date.parse(third) >= from_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        } else if (!isNaN(to_date)) {
            angular.forEach(items, function(item) {
                if (item.third !== null) {
                    third = new Date(item.third);
                    third.setHours(0, 0, 0, 0);
                    if(Date.parse(third) <= to_date) {
                        filtered.push(item);
                    }
                }
            });
            return filtered;
        }
        return items;
    };
});