export function capitalize(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}

export function camelCase(str) {
    str = str.charAt(0).toLowerCase() + str.slice(1);
    return str.replace(/^([A-Z])|[\s-_](\w)/g, (match, p1, p2, offset) => {
        if (p2) return p2.toUpperCase();
        return p1.toLowerCase();
    });
}

export function toInstanceName(className: string) {
    return camelCase(className);
}

export function toJpql(classname: string) {
    return capitalize(camelCase(classname));
}
