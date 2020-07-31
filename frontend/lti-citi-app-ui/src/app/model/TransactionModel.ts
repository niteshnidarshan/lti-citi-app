export class TransactionModel{
    constructor(
        public transactionId: string,
        public transactionType: string,
        public senderAccountId: string,
        public receiversAccountId: string,
        public amount: number, 
        public associatedUserId: string,
        public transactionStatus: string,
        public transactionTimeStamp: string
    ){}
}