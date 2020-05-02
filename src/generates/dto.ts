import {capitalize, camelCase, toInstanceName, toJpql} from "./utils";
import {Database} from "../database";

import pluralize from 'pluralize';


export async function processDto(inputArgs: any) {
    const that: any = {};

    that.USER_PACKAGE = inputArgs.package;
    that.TABLE_NAME = inputArgs.tableName
    that.DTO_TYPE = inputArgs.type
    that.RESOURCE_NAME = camelCase(that.TABLE_NAME);
    that.PLURAL_RESOURCE_NAME = pluralize.plural(that.RESOURCE_NAME);

    that.DTO_CLASS_NAME = capitalize(that.RESOURCE_NAME) + capitalize(that.DTO_TYPE);
    that.VERSIONUID = Math.floor(Math.random() * 1000000000);

    that.instance = toInstanceName;
    that.jpql = toJpql;

    that.columns = await Database.database().getTableKeys(that.TABLE_NAME);


    return that;
}
