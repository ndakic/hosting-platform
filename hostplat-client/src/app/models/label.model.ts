export class Label {
    public id: string;
    public name: string;
    public userId: String;
    private statusId: number;


    constructor(id?: string, name?: string, userId?: string, status_id?: number) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.statusId = status_id;
    }


} 