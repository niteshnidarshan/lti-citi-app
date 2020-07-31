import { AccountModel } from './AccountModel';

export class UserModel{
        constructor(
        public userId: string,
        public firstName: string,
        public lastName: string,
        public address: string,
        public email: string,
        public mobile: string,
        public socialSecurityNumber: string, //using as a password - encrypted 
        public associatedAccounts: AccountModel,  //One user might have n number of accounts
        public userType: string,
        public userStatus: string, //To maintain delete(On delete - INACTIVE)
        public profileCreationTimeStamp: string,
        public profileLastModifiedTimeStamp: string
    ){}
}