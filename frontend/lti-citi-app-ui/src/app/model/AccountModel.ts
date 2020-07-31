export class AccountModel{
    constructor(
        public accountId: string,
        public accountType: string,
        public amount: number,
        public associatedUserId: string,
        public accountCreationTimeStamp: string,
        public accountLastModifiedTimeStamp: string
    ){}
}