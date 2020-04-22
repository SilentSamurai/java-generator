import {capitalize, camelCase, toInstanceName} from "./utils";

import pluralize from 'pluralize';


export function processService(inputArgs: any) {
    const that: any = {};
    that.RESOURCE_NAME = camelCase(inputArgs.resource);
    that.PLURAL_RESOURCE_NAME = pluralize.plural(that.RESOURCE_NAME);
    that.USER_PACKAGE = inputArgs.package;

    that.ENTITY_CLASS = capitalize(that.RESOURCE_NAME);
    that.ENTITY_CLASS_PLURAL = capitalize(that.PLURAL_RESOURCE_NAME);
    that.SERVICE_CLASS = that.ENTITY_CLASS + "Service";
    that.REPO_CLASS = that.ENTITY_CLASS + "Repository";
    that.NOT_FOUND_EXCEPTION = that.ENTITY_CLASS + "NotFoundException";

    that.instance = toInstanceName;


    return that;
}
