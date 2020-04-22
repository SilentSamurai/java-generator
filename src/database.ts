import {toJpql} from "./generates/utils";

const initOptions = {
    // global event notification;
    error(error, e) {
        if (e.cn) {
            // A connection-related error;
            //
            // Connections are reported back with the password hashed,
            // for safe errors logging, without exposing passwords.
            // tslint:disable-next-line:no-console
            console.log('CN:', e.cn);
            // tslint:disable-next-line:no-console
            console.log('EVENT:', error.message || error);
        }
    }
};


import pgp from 'pg-promise';

export class Column {
    private _tableSchema: string;
    private _tableName: string;
    private _position: bigint;
    private _columnName: string;
    private _dataType: string;
    private _maxLength: bigint;
    private _isNullable: boolean;
    private _defaultValue: string;
    private _isUnique: boolean;

    get dataType(): string {
        return this._dataType;
    }

    set dataType(value: string) {
        if (value.match("char|text")) {
            this._dataType = "String"
        } else if (value.match("timestamp")) {
            this._dataType = "Timestamp"
        } else
            this._dataType = toJpql(value);
    }


    get tableSchema(): string {
        return this._tableSchema;
    }

    set tableSchema(value: string) {
        this._tableSchema = value;
    }

    get tableName(): string {
        return this._tableName;
    }

    set tableName(value: string) {
        this._tableName = value;
    }

    get position(): bigint {
        return this._position;
    }

    set position(value: bigint) {
        this._position = value;
    }

    get columnName(): string {
        return this._columnName;
    }

    set columnName(value: string) {
        this._columnName = value;
    }

    get maxLength(): bigint {
        return this._maxLength;
    }

    set maxLength(value: bigint) {
        this._maxLength = value;
    }

    get isNullable(): boolean {
        return this._isNullable;
    }

    set isNullable(value: boolean) {
        this._isNullable = value;
    }

    get defaultValue(): string {
        return this._defaultValue;
    }

    set defaultValue(value: string) {
        this._defaultValue = value;
    }

    get isUnique(): boolean {
        return this._isUnique;
    }

    set isUnique(value: boolean) {
        this._isUnique = value;
    }
}

// tslint:disable-next-line:max-classes-per-file
export class Database {
    private connectionString: string;
    private db: any;

    private connect() {
        this.connectionString = 'postgres://postgres:root@localhost:5432/athena_db';
        this.db = pgp(initOptions);
        this.db.connect();
        return this;
    }

    public async getTableKeys(tableName: string) {
        const query = `
            select table_schema,
                   table_name,
                   ordinal_position as position,
                   ic.column_name,
                   uc.column_name is not null as is_unique,
                   data_type,
                   case when character_maximum_length is not null
                            then character_maximum_length
                        else numeric_precision end as max_length,
                   is_nullable,
                   column_default as default_value
            from information_schema.columns ic
            left outer join (SELECT column_name FROM information_schema.key_column_usage
                  WHERE position_in_unique_constraint is null AND
                          table_name = '${tableName}') uc
            on ic.column_name = uc.column_name
            where table_schema not in ('information_schema', 'pg_catalog') and table_name = '${tableName}'
            order by table_schema,
                     table_name,
                     ordinal_position;
        `
        const newVar = await this.db.many(query);
        return newVar.map(Database.toColumns);
    }

    private static toColumns(data: any): Column {
        const column = new Column();
        column.columnName = data.column_name
        column.dataType = data.data_type
        column.defaultValue = data.defaultValue
        column.isNullable = data.isNullable
        column.maxLength = data.maxLength
        column.position = data.position
        column.isUnique = data.is_unique
        return column;
    }

    private static dbInstance: Database = new Database().connect();

    public static database() {
        return Database.dbInstance;
    }

}