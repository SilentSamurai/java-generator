"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const utils_1 = require("./utils");
const pluralize = require('pluralize');
function processService(inputArgs) {
    let that = {};
    that.RESOURCE_NAME = utils_1.camelCase(inputArgs['resource']);
    that.PLURAL_RESOURCE_NAME = pluralize.plural(that.RESOURCE_NAME);
    that.USER_PACKAGE = inputArgs['package'];
    that.ENTITY_CLASS = utils_1.capitalize(that.RESOURCE_NAME);
    that.ENTITY_CLASS_PLURAL = utils_1.capitalize(that.PLURAL_RESOURCE_NAME);
    that.SERVICE_CLASS = that.ENTITY_CLASS + "Service";
    that.REPO_CLASS = that.ENTITY_CLASS + "Repository";
    that.NOT_FOUND_EXCEPTION = that.ENTITY_CLASS + "NotFoundException";
    that.instance = utils_1.toInstanceName;
    return that;
}
exports.processService = processService;
//# sourceMappingURL=service.js.map