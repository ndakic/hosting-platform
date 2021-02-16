export class Milestone {
    public id: number;
    public title: string;
    public start_date: Date;
    public end_date: Date;
    public user_id: number;
    public close: boolean;
  

    constructor(id?: number, title?: string, start_date?: Date,
        end_date?: Date, user_id?: number, close?: boolean) {
        this.id = id;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.user_id = user_id;
        this.close = close;
    }


} 