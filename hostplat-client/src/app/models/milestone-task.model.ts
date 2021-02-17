import { Milestone } from "./milestone.model";

export class MilestoneTask {
    public task_id: number;
    public milestone: Milestone;

    constructor(task_id?: number, milestone?: Milestone) {
        this.task_id = task_id;
        this.milestone = milestone;
    }

}  