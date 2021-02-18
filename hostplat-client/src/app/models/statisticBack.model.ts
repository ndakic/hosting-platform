import { Task } from "./task.model";

export class StatisticsBack {
    public open: Task[];
    public close: Task[];

    constructor(open?: Task[], close?: Task[]) {
        this.open = open;
        this.close = close;
    }

}  