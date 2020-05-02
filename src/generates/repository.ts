import {capitalize, camelCase, toInstanceName, toJpql} from "./utils";
import {Database} from "../database";
import {as} from "pg-promise";

import pluralize from 'pluralize';


export async function processRepository(inputArgs: any) {
    const that: any = {};
    that.RESOURCE_NAME = camelCase(inputArgs.resource);
    that.PLURAL_RESOURCE_NAME = pluralize.plural(that.RESOURCE_NAME);
    that.USER_PACKAGE = inputArgs.package;
    that.PRIMARY_KEY_CLASS = inputArgs.primaryKeyType
    that.PRIMARY_KEY_NAME = inputArgs.primaryKey
    that.TABLE_NAME = inputArgs.tableName

    that.ENTITY_CLASS = capitalize(that.RESOURCE_NAME);
    that.ENTITY_CLASS_PLURAL = capitalize(that.PLURAL_RESOURCE_NAME);
    that.SERVICE_CLASS = that.ENTITY_CLASS + "Service";
    that.REPO_CLASS = that.ENTITY_CLASS + "Repository";
    that.NOT_FOUND_EXCEPTION = that.ENTITY_CLASS + "NotFoundException";

    that.instance = toInstanceName;
    that.jpql = toJpql;

    that.columns = await Database.database().getTableKeys(that.TABLE_NAME);


    return that;
}
