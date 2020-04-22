"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function capitalize(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}
exports.capitalize = capitalize;
function camelCase(str) {
    str = str.charAt(0).toLowerCase() + str.slice(1);
    return str.replace(/^([A-Z])|[\s-_](\w)/g, function (match, p1, p2, offset) {
        if (p2)
            return p2.toUpperCase();
        return p1.toLowerCase();
    });
}
exports.camelCase = camelCase;
function toInstanceName(className) {
    return camelCase(className);
}
exports.toInstanceName = toInstanceName;
function toJpql(classname) {
    return capitalize(camelCase(classname));
}
exports.toJpql = toJpql;
//# sourceMappingURL=utils.js.map