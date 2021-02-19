import { Label } from "./label.model";

export class LabelTask {
    public task_id: number;
    public labels: Label[];

    constructor(task_id?: number, labels?: Label[]) {
        this.task_id = task_id;
        this.labels = labels;
    }

}  