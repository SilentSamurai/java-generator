"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const utils_1 = require("./utils");
const pluralize = require('pluralize');
// console.log(controller);
function generateControllerVariables(inputArgs) {
    const RESOURCE_NAME = inputArgs['RESOURCE_NAME'];
    const PLURAL_RESOURCE_NAME = pluralize.plural(RESOURCE_NAME);
    const PRE_FIX_URL = inputArgs['PRE_FIX_URL'];
    const ENTITY_URL_FRAGMENT = inputArgs['ENTITY_URL_FRAGMENT'];
    const USER_SPRING_PACKAGE = inputArgs['package'];
    const API_NAME = ENTITY_URL_FRAGMENT !== null ? ENTITY_URL_FRAGMENT : RESOURCE_NAME;
    const ENTITY_INSTANCE_NAME = utils_1.camelCase(RESOURCE_NAME);
    const ENTITY_INSTANCE_NAME_PLURAL = utils_1.camelCase(PLURAL_RESOURCE_NAME);
    const ENTITY_NAME = utils_1.capitalize(RESOURCE_NAME);
    const ENTITY_NAME_PLURAL = utils_1.capitalize(PLURAL_RESOURCE_NAME);
    const ControllerName = utils_1.capitalize(ENTITY_NAME) + "Controller";
    const SERVICE_CLASS = utils_1.capitalize(ENTITY_NAME) + "Service";
    const SERVICE_INSTANCE = utils_1.camelCase(SERVICE_CLASS);
    const JOB_REQUEST_CLASS = ENTITY_NAME + "Request";
    const JOB_REQUEST_INSTANCE = utils_1.camelCase(JOB_REQUEST_CLASS);
    const LIGHT_RESPONSE_CLASS = "Light" + ENTITY_NAME + "Response";
    const LIGHT_RESPONSE_INSTANCE = utils_1.camelCase(LIGHT_RESPONSE_CLASS);
    return {
        RESOURCE_NAME,
        PLURAL_RESOURCE_NAME,
        PRE_FIX_URL,
        ENTITY_URL_FRAGMENT,
        USER_SPRING_PACKAGE,
        API_NAME,
        ENTITY_INSTANCE_NAME,
        ENTITY_INSTANCE_NAME_PLURAL,
        ENTITY_NAME,
        ENTITY_NAME_PLURAL,
        ControllerName,
        SERVICE_CLASS,
        SERVICE_INSTANCE,
        JOB_REQUEST_CLASS,
        JOB_REQUEST_INSTANCE,
        LIGHT_RESPONSE_CLASS,
        LIGHT_RESPONSE_INSTANCE
    };
}
exports.generateControllerVariables = generateControllerVariables;
//# sourceMappingURL=GenerateRestController.js.map