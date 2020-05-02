import {capitalize, camelCase} from "./utils";

import pluralize from 'pluralize';

// console.log(controller);

export function generateControllerVariables(inputArgs: any) {

    const RESOURCE_NAME = inputArgs.RESOURCE_NAME;
    const PLURAL_RESOURCE_NAME = pluralize.plural(RESOURCE_NAME);
    const PRE_FIX_URL = inputArgs.PRE_FIX_URL;
    const ENTITY_URL_FRAGMENT = inputArgs.ENTITY_URL_FRAGMENT;
    const USER_SPRING_PACKAGE = inputArgs.package;


    const API_NAME = ENTITY_URL_FRAGMENT !== null ? ENTITY_URL_FRAGMENT : RESOURCE_NAME;

    const ENTITY_INSTANCE_NAME = camelCase(RESOURCE_NAME);
    const ENTITY_INSTANCE_NAME_PLURAL = camelCase(PLURAL_RESOURCE_NAME);
    const ENTITY_NAME = capitalize(RESOURCE_NAME);
    const ENTITY_NAME_PLURAL = capitalize(PLURAL_RESOURCE_NAME);

    const ControllerName = capitalize(ENTITY_NAME) + "Controller";
    const SERVICE_CLASS = capitalize(ENTITY_NAME) + "Service";
    const SERVICE_INSTANCE = camelCase(SERVICE_CLASS);

    const JOB_REQUEST_CLASS = ENTITY_NAME + "Request";
    const JOB_REQUEST_INSTANCE = camelCase(JOB_REQUEST_CLASS);

    const LIGHT_RESPONSE_CLASS = "Light" + ENTITY_NAME + "Response";
    const LIGHT_RESPONSE_INSTANCE = camelCase(LIGHT_RESPONSE_CLASS);

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